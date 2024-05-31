package lk.ijse.aad.helloshoe.service;

import lk.ijse.aad.helloshoe.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    SupplierDTO saveSupplier(SupplierDTO supplierDTO);
    SupplierDTO updateSupplier(SupplierDTO supplierDTO, String supplierCode);
    void removeSupplier(String supplierCode);
    List<SupplierDTO> getAllSuppliers();
}
