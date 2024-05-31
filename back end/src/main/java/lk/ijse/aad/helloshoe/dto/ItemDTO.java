package lk.ijse.aad.helloshoe.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lk.ijse.aad.helloshoe.enums.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements  SuperDTO{
    @Null(message = "customerCode auto generate")
    private String itemCode;
    @NotNull(message = "item name require")
    private String itemName;
    @NotNull(message = "item picture require")
    private String itemPicture;
    @NotNull(message = "item category require")
    private String itemCategory;
//    @NotNull(message = "item size require")
    private Map<String,Integer> itemSize;
    @NotNull(message = "supplier code require")
    private String supplierCode;
    @NotNull(message = "supplier name require")
    private String supplierName;
    @NotNull(message = "unit price of sale require")
    private Double unitPriceSale;
    @NotNull(message = "unit price of buy require")
    private Double unitPriceBuy;
    @NotNull(message = "expected profit require")
    private Double expectedProfit;
    @NotNull(message = "profit margin require")
    private Double profitMargin;
    @NotNull(message = "require item original qty")
    private Double originalQty;
    private Double remainQty;
//    @NotNull(message = "item status require")
    private String status;
}
