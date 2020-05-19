package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Complaint complaint;
}
