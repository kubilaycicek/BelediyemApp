package com.ketechsoft.reqtrack.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class ComplaintGallery extends BaseEntity {

    @Lob
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER
            //,cascade = CascadeType.PERSIST
    )
    @JoinColumn
    private Complaint complaint;
}
