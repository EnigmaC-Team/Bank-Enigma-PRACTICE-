package com.enigma.bankenigma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Table(name = "mst_ewallet")
public class EWallet {

    @Id
    @GeneratedValue(generator = "wallet_uuid")
    @GenericGenerator(name = "wallet_uuid", strategy = "uuid")
    private String id;
    private String name;
    private String password;
    private Integer saving;
    private String profilePicture;

    @Transient
    private String userAccountId;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "userAccountId")
    private UserAccount userAccount;

    public EWallet() {
    }

    public EWallet(String name, String password, String userAccountId) {
        this.name = name;
        this.password = password;
        this.userAccountId = userAccountId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }
}
