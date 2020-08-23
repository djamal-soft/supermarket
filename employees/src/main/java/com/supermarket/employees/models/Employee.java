package com.supermarket.employees.models;


import com.supermarket.employees.enums.EmployeeSex;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "nom et prénom est rquis")
    @Column(name="full_name")
    private String fullName;

    @NotBlank(message = "adresse est rquis")
    private String address;

    @NotBlank(message = "téléphone est rquis")
    @Column(name="phone", length=20, unique=true)
    private String phone;

    @NotEmpty(message = "email est rquis")
    @Email(message = "entrez un valide email")
    @Column(name="email", length=50, unique=true)
    private String email;

    @NotBlank(message = "mot de pass est rquis")
    private String password;

    @NotBlank(message = "role est rquis")
    private String role;

    @Min(value = 18, message = "age doit etre +18")
    private int age;

//    @NotBlank(message = "sexe est rquis")
    @Enumerated(EnumType.ORDINAL)
    private EmployeeSex sex = EmployeeSex.MALE;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmployeeSex getSex() {
        return sex;
    }

    public void setSex(EmployeeSex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
