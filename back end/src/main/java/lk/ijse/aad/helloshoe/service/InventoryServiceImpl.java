package lk.ijse.aad.helloshoe.service;

import jakarta.transaction.Transactional;
import lk.ijse.aad.helloshoe.dto.ItemDTO;
import lk.ijse.aad.helloshoe.entity.ItemEntity;
import lk.ijse.aad.helloshoe.exception.DataEffectException;
import lk.ijse.aad.helloshoe.exception.DataReadException;
import lk.ijse.aad.helloshoe.exception.NotFountException;
import lk.ijse.aad.helloshoe.repo.InventoryRepo;
import lk.ijse.aad.helloshoe.utill.Mapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {
    private final Mapping mapper;
    private final InventoryRepo inventoryRepo;

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        try {
//            ItemEntity lastItemEntity = inventoryRepo.findTopByOrderByItemCodeDesc();
//            System.out.println(lastItemEntity.getItemCode().substring(3));
//            itemDTO.setItemCode("swe" + (Integer.parseInt(lastItemEntity.getItemCode().substring(3)) + 1));
            itemDTO.setItemCode("swe1");
            ItemEntity itemEntity = mapper.toItemEntity(itemDTO);
            return mapper.toItemDTO(inventoryRepo.save(itemEntity));
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO, String itemCode) {
        ItemEntity itemEntity = inventoryRepo.findById(itemCode).orElseThrow(() -> new NotFountException());
        try {
            itemEntity.setItemName(itemDTO.getItemName());
            itemEntity.setItemPicture(itemDTO.getItemPicture());
            itemEntity.setItemCategory(itemDTO.getItemCategory());
//            itemEntity.setItemSize(itemDTO.getItemSize());
            itemEntity.setSupplierCode(itemDTO.getSupplierCode());
            itemEntity.setSupplierName(itemDTO.getSupplierName());
            itemEntity.setUnitPriceSale(itemDTO.getUnitPriceSale());
            itemEntity.setUnitPriceBuy(itemDTO.getUnitPriceBuy());
            itemEntity.setExpectedProfit(itemDTO.getExpectedProfit());
            itemEntity.setProfitMargin(itemDTO.getProfitMargin());
            itemEntity.setOriginalQty(itemDTO.getOriginalQty());
            itemEntity.setRemainQty(itemDTO.getOriginalQty());
            itemEntity.setStatus(itemDTO.getStatus());

            ItemDTO updatedItemDTO = mapper.toItemDTO(itemEntity);
            return updatedItemDTO;
        } catch (Exception e) {
            System.out.println(".........."+e);
            throw new DataEffectException();
        }
    }

    @Override
    public void removeItem(String itemCode) {
        if (!inventoryRepo.existsById(itemCode)) {
            throw new NotFountException();
        }
        try {
            inventoryRepo.deleteById(itemCode);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        try {
            return  mapper.toItemDTOList(inventoryRepo.findAll());
//            List<ItemSizeEntity> itemSizeEntityList = itemSizeRepo.findAll();
//
//            List<ItemDTO> itemDTOList = new ArrayList<>();
//
//            for (ItemEntity itemEntity : itemEntityList) {
//                ItemDTO itemDTO = new ItemDTO(itemEntity.getItemCode(),
//                        itemEntity.getItemName(),
//                        itemEntity.getItemPicture(),
//                        itemEntity.getItemCategory(),
//                        null,
//                        itemEntity.getSupplierCode(),
//                        itemEntity.getSupplierName(),
//                        itemEntity.getUnitPriceSale(),
//                        itemEntity.getUnitPriceBuy(),
//                        itemEntity.getExpectedProfit(),
//                        itemEntity.getProfitMargin(),
//                        itemEntity.getOriginalQty(),
//                        itemEntity.getRemainQty(),
//                        itemEntity.getStatus()
//                );
//                itemDTOList.add(itemDTO);
//            }

//            for (ItemDTO itemDTO : itemDTOList) {
//                for (ItemSizeEntity itemSizeEntity : itemSizeEntityList) {
//                    if (itemDTO.getItemCode().equals(itemSizeEntity.getItemCode()));
//                        itemDTO.setItemSize(itemSizeEntity);
//                    System.out.println(itemSizeEntity);
//                }
//            }
//            return mapper.toItemDTOListBySelf(itemDTOList);

        } catch (Exception e) {
            System.out.println(e);
            throw new DataReadException();
        }
    }

    @Override
    public ItemDTO resupplyItem(String itemCode, double itemCount) {
        ItemEntity itemEntity = inventoryRepo.findById(itemCode).orElseThrow(() -> new NotFountException());
        try {
            itemEntity.setOriginalQty(itemEntity.getOriginalQty() + itemCount);
            return mapper.toItemDTO(itemEntity);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public ItemDTO changeSupplier(String itemCode, String newSupplierCode, String newSupplierName) {
        ItemEntity itemEntity = inventoryRepo.findById(itemCode).orElseThrow(() -> new NotFountException());
        try {
            itemEntity.setSupplierCode(newSupplierCode);
            itemEntity.setSupplierName(newSupplierName);
            return mapper.toItemDTO(itemEntity);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }

    @Override
    public ItemDTO reduceItemCount(String itemCode, double itemCount) {
        ItemEntity itemEntity = inventoryRepo.findById(itemCode).orElseThrow(() -> new NotFountException());
        try {
            itemEntity.setRemainQty(itemEntity.getRemainQty() - itemCount);
            return mapper.toItemDTO(itemEntity);
        } catch (Exception e) {
            throw new DataEffectException();
        }
    }
}
