package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class User extends BaseEntity {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String username;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String address;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private UserType userType;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            targetEntity = Complaint.class, mappedBy = "user")
    private List<Complaint> complaintList;
}
