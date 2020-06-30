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
public class Department extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,
            targetEntity = Category.class,
            mappedBy = "department")
    private List<Category> categoryList;
}
