package lk.ijse.aad.helloshoe.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lk.ijse.aad.helloshoe.enums.EmpRole;
import lk.ijse.aad.helloshoe.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO implements SuperDTO{
    @Null(message = "employee code auto generated")
    private String employeeCode;
    @NotNull(message = "require employee name")
    private String employeeName;
//    @NotNull(message = "require employee proPic")
    private String profilePic;
    @NotNull(message = "require employee gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "require employee status")
    private String status;
    @NotNull(message = "require employee designation")
    private String designation;
    @NotNull(message = "require employee role")
    @Enumerated(EnumType.STRING)
    private EmpRole accessRole;
    @NotNull(message = "require employee dob")
    private String dob;
    @NotNull(message = "require employee join date")
    private String dateOfJoin;
    @NotNull(message = "require employee address 1")
    private String address1;
    private String address2;
    @NotNull(message = "require main city of employee")
    private String address3;
    @NotNull(message = "require main state of employee")
    private String address4;
    @NotNull(message = "require postal code of employee")
    private String address5;
    @NotNull(message = "require employee contact")
    @Pattern(regexp = "^(?:\\+?\\d{1,3})?[\\s.-]?(?:\\(\\d{1,4}\\))?[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})$",
            message = "Invalid employee mobile number1")
    private String contact;
    @NotNull(message = "require employee email")
    @Email(message = "email type invalid")
    private String email;
    @NotNull(message = "require guardian name")
    private String guardianName;
    @NotNull(message = "require guardian contact")
    @Pattern(regexp = "^(?:\\+?\\d{1,3})?[\\s.-]?(?:\\(\\d{1,4}\\))?[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})[\\s.-]?(?:\\d{1,5})$",
            message = "Invalid employee guardian mobile number1")
    private String guardianContact;
}
