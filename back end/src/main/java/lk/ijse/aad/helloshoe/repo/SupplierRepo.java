package lk.ijse.aad.helloshoe.repo;

import lk.ijse.aad.helloshoe.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<SupplierEntity,String> {
}
