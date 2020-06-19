package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class User extends BaseEntity {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phone;
    @Column
    private String tcNumber;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private UserType userType;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            targetEntity = Complaint.class, mappedBy = "user")
    private List<Complaint> complaintList;
}