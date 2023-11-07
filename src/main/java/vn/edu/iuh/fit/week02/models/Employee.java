package vn.edu.iuh.fit.week02.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import vn.edu.iuh.fit.week02.status.EmployeeStatus;

import java.time.LocalDate;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column(name = "emp_id", length = 20)
    private long id;
    @Column(name = "full_name", nullable = false,columnDefinition = "nvarchar(50)")
    private String fullName;
    @Column(name = "dob", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    @Column(nullable = false,columnDefinition = "varchar(255)")
    private String email;
    @Column(length = 255, nullable = false)
    private String phone;
    @Column(nullable = false,columnDefinition = "nvarchar(255)")
    private String address;
    private EmployeeStatus status;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }

    public Employee() {
    }

    public Employee(String fullName, LocalDate dob, String email, String phone, String address, EmployeeStatus status) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
}
