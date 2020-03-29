package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table
public class UserType extends BaseEntity implements Serializable {

    @Column
    private String name;
    @Column
    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class,mappedBy = "userType")
    private List<User> userList;
}
