package com.ketechsoft.reqtrack.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)

@Data
@Entity
@Table
public class ComplaintGallery extends BaseEntity {

    @Lob
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Complaint complaint;
}
