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

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ComplaintStatus complaintStatus;

    @OneToMany(targetEntity = ComplaintGallery.class,fetch = FetchType.LAZY)
    @JoinColumn
    private List<ComplaintGallery> complaintGalleries;
}
