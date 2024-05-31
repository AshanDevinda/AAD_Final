package lk.ijse.aad.helloshoe.repo;

import lk.ijse.aad.helloshoe.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerEntity,String> {
}
