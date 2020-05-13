package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table
public class Complaint extends BaseEntity{

    @Column
    private String description;

    @Column
    private String location;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private ComplaintStatus complaintStatus;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,targetEntity = ComplaintGallery.class,fetch = FetchType.LAZY)
    @JoinColumn
    private List<ComplaintGallery> complaintGalleries;
}
