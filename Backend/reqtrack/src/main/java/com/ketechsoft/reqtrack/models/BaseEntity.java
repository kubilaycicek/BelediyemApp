package com.ketechsoft.reqtrack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode()
@Data
@MappedSuperclass
@JsonIgnoreProperties({"creationDate", "lastModifiedDate"})
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private LocalDateTime creationDate = LocalDateTime.now();
    @Column
    private LocalDateTime lastModifiedDate = LocalDateTime.now();
}
