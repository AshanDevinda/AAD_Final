package lk.ijse.aad.helloshoe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int orderNo;
    private String customerName;
    private double totalPrice;
    private Timestamp purchaseDate;
    private String paymentMethod;
    private double points;
    private String cashierName;
}
