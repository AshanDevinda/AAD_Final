package lk.ijse.aad.helloshoe.service;

import jakarta.transaction.Transactional;
import lk.ijse.aad.helloshoe.dto.CustomerDTO;
import lk.ijse.aad.helloshoe.dto.EmployeeDTO;
import lk.ijse.aad.helloshoe.entity.CustomerEntity;
import lk.ijse.aad.helloshoe.entity.EmployeeEntity;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.DataReadException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.repo.CustomerRepo;
import lk.ijse.aad.helloshoe.repo.EmployeeRepo;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService{
    private final Mapping mapper;
    private final EmployeeRepo employeeRepo;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        try {
            employeeDTO.setEmployeeCode("E" + UUID.randomUUID().toString().substring(0, 8));
            EmployeeEntity employeeEntity = mapper.toEmployeeEntity(employeeDTO);
            employeeEntity.setDob(mapper.StringDateToLocalDate(employeeDTO.getDob()));
            employeeEntity.setDateOfJoin(mapper.StringDateToLocalDate(employeeDTO.getDateOfJoin()));
            return mapper.toEmployeeDTO(employeeRepo.save(employeeEntity));
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String employeeCode) {
        EmployeeEntity employeeEntity = employeeRepo.findById(employeeCode).orElseThrow(() -> new NotFountException());
        try {
            employeeEntity.setEmployeeName(employeeDTO.getEmployeeName());
            employeeEntity.setProfilePic(employeeDTO.getProfilePic());
            employeeEntity.setGender(String.valueOf(employeeDTO.getGender()));
            employeeEntity.setStatus(employeeDTO.getStatus());
            employeeEntity.setDesignation(employeeDTO.getDesignation());
            employeeEntity.setAccessRole(String.valueOf(employeeDTO.getAccessRole()));
            employeeEntity.setDob(LocalDate.parse(employeeDTO.getDob()));
            employeeEntity.setDateOfJoin(LocalDate.parse(employeeDTO.getDateOfJoin()));
            employeeEntity.setAddress1(employeeDTO.getAddress1());
            employeeEntity.setAddress2(employeeDTO.getAddress2());
            employeeEntity.setAddress3(employeeDTO.getAddress3());
            employeeEntity.setAddress4(employeeDTO.getAddress4());
            employeeEntity.setAddress5(employeeDTO.getAddress5());
            employeeEntity.setContact(employeeDTO.getContact());
            employeeEntity.setEmail(employeeDTO.getEmail());
            employeeEntity.setGuardianName(employeeDTO.getGuardianName());
            employeeEntity.setGuardianContact(employeeDTO.getGuardianContact());

            EmployeeDTO updatedEmployeeDTO = mapper.toEmployeeDTO(employeeEntity);
            return updatedEmployeeDTO;
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public void removeEmployee(String employeeCode) {
        if (!employeeRepo.existsById(employeeCode)) {
            throw new NotFountException();
        }
        try {
            employeeRepo.deleteById(employeeCode);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            List<EmployeeEntity> employeeEntityList = employeeRepo.findAll();
            return mapper.toEmployeeDTOList(employeeEntityList);
        } catch (Exception e) {
            throw new DataReadException();
        }
    }
}
