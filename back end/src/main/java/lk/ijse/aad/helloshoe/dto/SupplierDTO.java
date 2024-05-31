package lk.ijse.aad.helloshoe.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lk.ijse.aad.helloshoe.enums.SupplierCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO implements SuperDTO{
    @Null(message = "supplier code auto generated")
    private String supplierCode;
    @NotNull(message = "require supplier name")
    private String supplierName;
    @NotNull(message = "supplier category must be specified and not null")
    @Enumerated(EnumType.STRING)
    private SupplierCategory category;
    @NotNull(message = "require building number or name of supplier")
    private String address1;
    private String address2;
    @NotNull(message = "require main city of supplier")
    private String address3;
    @NotNull(message = "require main state of supplier")
    private String address4;
    @NotNull(message = "require postal code of supplier")
    private String address5;
    @NotNull(message = "require country of supplier")
    private String address6;
    @Pattern(regexp = "^(?:\\+?\\d{1,3})?[\\s.-]?(?:\\(\\d{1,4}\\))?[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})$",
            message = "Invalid mobile number1")
    private String contact1;
    @Pattern(regexp = "^(?:\\+?\\d{1,3})?[\\s.-]?(?:\\(\\d{1,4}\\))?[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})$",
            message = "Invalid mobile number2")
    private String contact2;
    @NotNull(message = "require email")
    @Email(message = "email type invalid")
    private String email;
}
