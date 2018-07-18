package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"userEntity"})
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userInfoId")
    private UserInfoEntity userInfoEntity;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.EAGER)
    private Set<UserLabelEntity> userLabelEntity = new HashSet<UserLabelEntity>();

    @ManyToMany(mappedBy="userEntity",fetch = FetchType.EAGER)
    private Set<UserIdentityEntity> userIdentityEntity = new HashSet<UserIdentityEntity>();


    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public Set<UserLabelEntity> getUserLabelEntity() {
        return userLabelEntity;
    }

    public void setUserLabelEntity(Set<UserLabelEntity> userLabelEntity) {
        this.userLabelEntity = userLabelEntity;
    }

    public Set<UserIdentityEntity> getUserIdentityEntity() {
        return userIdentityEntity;
    }

    public void setUserIdentityEntity(Set<UserIdentityEntity> userIdentityEntity) {
        this.userIdentityEntity = userIdentityEntity;
    }
}
