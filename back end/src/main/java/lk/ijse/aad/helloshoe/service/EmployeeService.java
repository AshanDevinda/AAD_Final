package lk.ijse.aad.helloshoe.service;

import lk.ijse.aad.helloshoe.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String employeeCode);

    void removeEmployee(String employeeCode);

    List<EmployeeDTO> getAllEmployees();
}
