package lk.ijse.aad.helloshoe.repo;

import lk.ijse.aad.helloshoe.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity,String> {
}
