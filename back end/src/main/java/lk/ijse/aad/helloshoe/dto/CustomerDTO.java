package lk.ijse.aad.helloshoe.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lk.ijse.aad.helloshoe.enums.Gender;
import lk.ijse.aad.helloshoe.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements SuperDTO{
    @Null(message = "customer code auto generated")
    private String customerCode;
    @NotNull(message = "require customer name")
    private String customerName;
    @NotNull(message = "gender must be specified and not null")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joinDateAsALoyaltyCustomer;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Min(value = 0,message = "Total points must be greater than or equal to 0")
    private int totalPoints;
    @NotNull(message = "require customer birthday")
    private String dob;
    @NotNull(message = "require building number or name of customer")
    private String address1;
    private String address2;
    @NotNull(message = "require main city of customer")
    private String address3;
    @NotNull(message = "require main state of customer")
    private String address4;
    @NotNull(message = "require postal code of customer")
    private String address5;
    @NotNull
    @Pattern(regexp = "^(?:\\+?\\d{1,3})?[\\s.-]?(?:\\(\\d{1,4}\\))?[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})$",
            message = "Invalid mobile number1")
    private String contact;
    @Email
    private String email;
    private Timestamp recentPurchaseDate;
}
