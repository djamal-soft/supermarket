package com.supermarket.clients.models;

import com.supermarket.clients.enums.ClientSex;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "clients")
public class Client {

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

    @Min(value = 18, message = "age doit etre +18")
    private int age;

//    @NotBlank(message = "sexe est rquis")
    @Enumerated(EnumType.ORDINAL)
    private ClientSex sex = ClientSex.MALE;

    public Client() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClientSex getSex() {
        return sex;
    }

    public void setSex(ClientSex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
