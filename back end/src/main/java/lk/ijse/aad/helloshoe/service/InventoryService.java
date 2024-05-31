package lk.ijse.aad.helloshoe.service;

import lk.ijse.aad.helloshoe.dto.ItemDTO;

import java.util.List;

public interface InventoryService {
    ItemDTO saveItem(ItemDTO itemDTO);

    ItemDTO updateItem(ItemDTO itemDTO, String itemCode);

    void removeItem(String itemCode);
    List<ItemDTO> getAllItems();
    ItemDTO resupplyItem(String itemCode, double itemCount);

    ItemDTO changeSupplier(String itemCode, String newSupplierCode, String newSupplierName);

    ItemDTO reduceItemCount(String itemCode, double itemCount);
}
