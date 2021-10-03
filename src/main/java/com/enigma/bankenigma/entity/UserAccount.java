package com.enigma.bankenigma.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.file.Paths;

@Entity
@Table(name = "mst_account")
public class UserAccount {

    @Id
    @GeneratedValue(generator = "account_uuid")
    @GenericGenerator(name = "account_uuid", strategy = "uuid")
    private String id;
    private String username;
    private String password;
    private String accountNumber;
    private String email;
    private Integer saving;
    private String profilePicture;

    public UserAccount() {
    }

    public UserAccount(BankUser bankUser) {
        this.username = bankUser.getUsername();
        this.password = bankUser.getPassword();
        this.email = bankUser.getEmail();
        this.accountNumber = bankUser.getAccountNumber();
        this.saving = 100000;
        this.profilePicture = Paths.get("").toAbsolutePath() + "/upload/default.png";
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getSaving() {
        return saving;
    }

    public void setSaving(Integer saving) {
        this.saving = saving;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
