package com.ketechsoft.reqtrack.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Complaint extends BaseEntity {
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

    @OneToMany(targetEntity = ComplaintGallery.class,mappedBy = "complaint",
           fetch = FetchType.EAGER)
    private List<ComplaintGallery> complaintGalleries = new ArrayList<>();
}