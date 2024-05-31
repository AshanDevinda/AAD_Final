package lk.ijse.aad.helloshoe.entity;

import jakarta.persistence.*;
import lk.ijse.aad.helloshoe.enums.SupplierCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supplier")
public class SupplierEntity implements SuperEntity{
    @Id
    private String supplierCode;
    private String supplierName;
    private SupplierCategory category;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String address6;
    private String contact1;
    private String contact2;
    private String email;
}
