package lk.ijse.aad.helloshoe.controller;

import jakarta.validation.Valid;
import lk.ijse.aad.helloshoe.dto.CustomerDTO;
import lk.ijse.aad.helloshoe.entity.CustomerEntity;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor

public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/healthCheck")
    public String healthCheckCustomer() {
        return "customer";
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customerDTO));
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/updateById/{customerCode}")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable("customerCode") String customerCode, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(customerDTO, customerCode));
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeById/{customerCode}")
    public ResponseEntity deleteCustomer(@Valid @PathVariable("customerCode") String customerCode) {
        try {
            customerService.removeCustomer(customerCode);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomers());
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/loyalty")
    public ResponseEntity becomeLoyaltyCustomer(@RequestParam String customerCode, @RequestParam LocalDate joinLoyaltyDate) {
        if (customerCode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            customerService.becomeLoyaltyCustomer(customerCode, joinLoyaltyDate);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/purchase")
    public ResponseEntity addPointAndMarkRecentPurchaseDateAndLevelForCustomer(@RequestParam String customerCode, @RequestParam int newPoints, @RequestParam String recentPurchaseDate) {
        try {
            customerService.addPointAndMarkRecentPurchaseDateAndLevelForCustomer(customerCode, newPoints, Timestamp.valueOf(recentPurchaseDate));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


