package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Category extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, targetEntity = Department.class)
    private Department department;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            targetEntity = Complaint.class,
            mappedBy = "category")
    private List<Complaint> complaintList;

}
