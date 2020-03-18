package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Department extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            targetEntity = Category.class,
            cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<Category> categoryList;
}
