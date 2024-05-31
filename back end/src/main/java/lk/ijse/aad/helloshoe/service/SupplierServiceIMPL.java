package lk.ijse.aad.helloshoe.service;

import jakarta.transaction.Transactional;
import lk.ijse.aad.helloshoe.dto.SupplierDTO;
import lk.ijse.aad.helloshoe.entity.SupplierEntity;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.DataReadException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.repo.SupplierRepo;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceIMPL implements SupplierService {
    private final SupplierRepo supplierRepo;
    private final Mapping mapper;

    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        try {
            supplierDTO.setSupplierCode("SUP" + UUID.randomUUID().toString().substring(0,8));
            return mapper.toSupplierDTO(supplierRepo.save(mapper.toSupplierEntity(supplierDTO)));
        } catch (DataEffectException e) {
            throw new DataEffectException();
        }
    }

    @Override
    public SupplierDTO updateSupplier(SupplierDTO supplierDTO, String supplierCode) {
        SupplierEntity supplierEntity = supplierRepo.findById(supplierCode).orElseThrow(() -> new NotFountException());
        try {
            supplierEntity.setSupplierName(supplierDTO.getSupplierName());
            supplierEntity.setCategory(supplierDTO.getCategory());
            supplierEntity.setAddress1(supplierDTO.getAddress1());
            supplierEntity.setAddress2(supplierDTO.getAddress2());
            supplierEntity.setAddress3(supplierDTO.getAddress3());
            supplierEntity.setAddress4(supplierDTO.getAddress4());
            supplierEntity.setAddress5(supplierDTO.getAddress5());
            supplierEntity.setAddress6(supplierDTO.getAddress6());
            supplierEntity.setContact1(supplierDTO.getContact1());
            supplierEntity.setContact2(supplierDTO.getContact2());
            supplierEntity.setEmail(supplierDTO.getEmail());

            SupplierDTO updatedSupplier = mapper.toSupplierDTO(supplierEntity);
            return updatedSupplier;
        } catch (DataEffectException e) {
            throw new DataEffectException();
        }
    }

    @Override
    public void removeSupplier(String supplierCode) {
        if (!supplierRepo.existsById(supplierCode)) {
            throw new NotFountException();
        }
        try {
            supplierRepo.deleteById(supplierCode);
        } catch (DataEffectException e) {
            throw new DataEffectException();
        }
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        try {
            return mapper.toSupplierDTOList(supplierRepo.findAll());
        } catch (DataReadException e) {
            throw new DataReadException();
        }
    }
}
