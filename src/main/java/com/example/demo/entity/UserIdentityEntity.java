package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userIdentity")
public class UserIdentityEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identity;

    @ManyToMany(targetEntity = UserIdentityEntity.class)
    @JoinTable(
            name="UserIdentityInfo",
            joinColumns=@JoinColumn(name="identityId"),
            inverseJoinColumns=@JoinColumn(name="userId"))
    @JsonBackReference
    private Set<UserEntity> userEntity = new HashSet<UserEntity>();

    public UserIdentityEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Set<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }
}
