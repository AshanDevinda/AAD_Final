package lk.ijse.aad.helloshoe.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import lk.ijse.aad.helloshoe.dto.CustomerDTO;
import lk.ijse.aad.helloshoe.dto.EmployeeDTO;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.service.EmployeeService;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("api/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor

public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/healthCheck")
    public String healthCheckCustomer() {
        return "employee";
    }

    @PostMapping("/save")
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO ,Errors errors) {
        if (errors.hasFieldErrors()) {
            System.out.println(errors.getFieldErrors());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDTO));
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/updateById/{employeeCode}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable("employeeCode") String employeeCode, Errors errors) {
        if (errors.hasFieldErrors()) {
            System.out.println(errors.getFieldErrors().get(0));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployee(employeeDTO, employeeCode));
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeById/{employeeCode}")
    public ResponseEntity deleteEmployee(@Valid @PathVariable("employeeCode") String employeeCode) {
        try {
            employeeService.removeEmployee(employeeCode);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFountException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
        } catch (DataEffectException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
