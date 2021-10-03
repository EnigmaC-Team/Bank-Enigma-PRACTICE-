package com.enigma.bankenigma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mst_user")
public class BankUser {

    @Id
    @GeneratedValue(generator = "user-uuid")
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String email;
    @Column(length = 15)
    private String phoneNumber;
    private String address;
    private String motherName;
    private String accountNumber;
    private String username;
    private String password;
    @JsonIgnore
    private String verifiedStatus;
    @JsonIgnore
    private String imgProfile;

    public BankUser() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }

}
