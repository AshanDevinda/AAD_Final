package lk.ijse.aad.helloshoe.controller;

import jakarta.validation.Valid;
import lk.ijse.aad.helloshoe.dto.CustomerDTO;
import lk.ijse.aad.helloshoe.dto.ItemDTO;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inv")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/healthCheck")
    public String healthCheckCustomer() {
        return "inventory";
    }

    @PostMapping("/save")
    public ResponseEntity<ItemDTO> saveItem( @RequestBody ItemDTO itemDTO, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            System.out.println(itemDTO.getItemSize());
            return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.saveItem(itemDTO));
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/updateById/{itemCode}")
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable("itemCode") String itemCode, Errors errors) {
        if (errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryService.updateItem(itemDTO, itemCode));
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeById/{itemCode}")
    public ResponseEntity deleteItem(@Valid @PathVariable("itemCode") String itemCode) {
        try {
            inventoryService.removeItem(itemCode);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryService.getAllItems());
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/resupply/{itemCode}/{itemCount}")
    public ResponseEntity<ItemDTO> resupplyItem(@PathVariable String itemCode, @PathVariable double itemCount) {
        if (itemCode.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryService.resupplyItem(itemCode, itemCount));
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/changeSupplier/{itemCode}/{newSupplierCode}/{newSupplierName}")
    public ResponseEntity changeSupplier(@PathVariable String itemCode, @PathVariable String newSupplierCode, @PathVariable String newSupplierName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryService.changeSupplier(itemCode, newSupplierCode, newSupplierName));
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/reduceItem/{itemCode}/{itemCount}")
    public ResponseEntity reduceItemCount(@PathVariable String itemCode, @PathVariable double itemCount) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(inventoryService.reduceItemCount(itemCode, itemCount));
        }catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
