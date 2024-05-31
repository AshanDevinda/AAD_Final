package lk.ijse.aad.helloshoe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "employee")
@Entity
public class EmployeeEntity implements SuperEntity {
    @Id
    private String employeeCode;
    private String employeeName;
    private String profilePic;
    private String gender;
    private String status;
    private String designation;
    private String accessRole;
    private LocalDate dob;
    private LocalDate dateOfJoin;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String contact;
    private String email;
    private String guardianName;
    private String guardianContact;
}
