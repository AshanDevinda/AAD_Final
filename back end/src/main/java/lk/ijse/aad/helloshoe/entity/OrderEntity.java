package lk.ijse.aad.helloshoe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order")
@Entity
public class OrderEntity {
    @Id
    private int orderNo;
    private String customerName;
    private double totalPrice;
    private Timestamp purchaseDate;
    private String paymentMethod;
    private double points;
    private String cashierName;
}
