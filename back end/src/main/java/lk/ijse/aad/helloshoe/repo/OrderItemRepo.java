package lk.ijse.aad.helloshoe.repo;

import lk.ijse.aad.helloshoe.dto.OrderItemDTO;
import lk.ijse.aad.helloshoe.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItemEntity,String> {
}
