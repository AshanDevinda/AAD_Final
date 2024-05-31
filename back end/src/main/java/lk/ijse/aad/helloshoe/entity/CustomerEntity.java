package lk.ijse.aad.helloshoe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String customerCode;
    private String customerName;
    private String gender;
    private LocalDate joinDateAsALoyaltyCustomer;
    private String level;
    private int totalPoints;
    private LocalDate dob;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String contact;
    private String email;
    private Timestamp recentPurchaseDate;
}
