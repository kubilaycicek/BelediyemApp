package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)

@Data
@Table
@Entity
public class ComplaintStatus extends BaseEntity  {
    @Column
    private String name;
    @Column
    private String description;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Complaint.class,  mappedBy = "complaintStatus")
    private List<Complaint> complaintList;
}
