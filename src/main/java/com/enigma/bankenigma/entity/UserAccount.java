package com.enigma.bankenigma.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    private Integer saving;

    public UserAccount() {
    }

    public UserAccount(BankUser bankUser) {
        this.username = bankUser.getUsername();
        this.password = bankUser.getPassword();
        this.accountNumber = bankUser.getAccountNumber();
        this.saving = 100000;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getSaving() {
        return saving;
    }

    public void setSaving(Integer saving) {
        this.saving = saving;
    }
}
