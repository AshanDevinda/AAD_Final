package lk.ijse.aad.helloshoe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {
    private int orderNo;
    private String itemCode;
    private String itemName;
    private int size;
    private double unitPrice;
    private int qty;
}
