package lk.ijse.aad.helloshoe.repo;

import lk.ijse.aad.helloshoe.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepo extends JpaRepository<ItemEntity, String> {
    @Query(value = "SELECT i FROM ItemEntity i ORDER BY i.itemCode DESC LIMIT 1")
    ItemEntity findTopByOrderByItemCodeDesc();
}
