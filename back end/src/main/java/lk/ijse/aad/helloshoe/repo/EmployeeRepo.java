package lk.ijse.aad.helloshoe.repo;

import lk.ijse.aad.helloshoe.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity,String> {
}
