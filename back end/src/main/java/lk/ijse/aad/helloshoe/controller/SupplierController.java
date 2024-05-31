package lk.ijse.aad.helloshoe.controller;

import jakarta.validation.Valid;
import lk.ijse.aad.helloshoe.dto.SupplierDTO;
import lk.ijse.aad.helloshoe.entity.SupplierEntity;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.DataReadException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.repo.SupplierRepo;
import lk.ijse.aad.helloshoe.service.SupplierService;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/supplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("healthCheck")
    public String healthCheck() {
        return "supplier";
    }

    @PostMapping("/save")
    public ResponseEntity<SupplierDTO> saveSupplier(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(supplierService.saveSupplier(supplierDTO));
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/updateById/{supplierCode}")
    public ResponseEntity<SupplierDTO> updateSupplier(@Valid @RequestBody SupplierDTO supplierDTO, @PathVariable("supplierCode") String supplierCode, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(supplierService.updateSupplier(supplierDTO,supplierCode));
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeById/{supplierCode}")
    public ResponseEntity<SupplierEntity> removeSupplier(@PathVariable("supplierCode") String supplierCode) {
        try {
            supplierService.removeSupplier(supplierCode);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SupplierDTO>> getAllCustomer() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(supplierService.getAllSuppliers());
        }catch (DataReadException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
