package lk.ijse.aad.helloshoe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "inventory")
@Entity
public class ItemEntity implements SuperEntity {
    @Id
    private String itemCode;
    private String itemName;
    @Column(columnDefinition = "LONGTEXT")
    private String itemPicture;
    private String itemCategory;
    private int size_5;
    private int size_6;
    private int size_7;
    private int size_8;
    private int size_9;
    private int size_10;
    private int size_11;
    private String supplierCode;
    private String supplierName;
    private double unitPriceSale;
    private double unitPriceBuy;
    private double expectedProfit;
    private double profitMargin;
    private double originalQty;
    private double remainQty;
    private String status;
}
