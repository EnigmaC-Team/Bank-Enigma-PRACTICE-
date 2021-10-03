package com.enigma.bankenigma.entity;

import com.enigma.bankenigma.string.properties.LinkUrl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    @Transient
    private Integer eWallet;

    @OneToMany(mappedBy = "userAccount", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<EWallet> eWalletList = new ArrayList<>();

    public UserAccount() {
    }

    public UserAccount(BankUser bankUser) {
        this.username = bankUser.getUsername();
        this.password = bankUser.getPassword();
        this.email = bankUser.getEmail();
        this.accountNumber = bankUser.getAccountNumber();
        this.saving = 100000;
        this.profilePicture = Paths.get("").toAbsolutePath() + LinkUrl.DEFAULT_PROFILE_PATH;
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

    public List<EWallet> geteWalletList() {
        return eWalletList;
    }

    public void addEWalletList(EWallet eWallet) {
        eWallet.setUserAccountId(eWallet.getUserAccount().getId());
        this.eWalletList.add(eWallet);
    }

    public Integer geteWallet() {
        return eWalletList.size();
    }
}
