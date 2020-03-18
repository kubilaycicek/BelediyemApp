package com.ketechsoft.reqtrack.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@JsonIgnoreProperties(value = {"creationDate", "lastModifiedDate"}, allowSetters = false, allowGetters = false)
public class BaseDto implements Serializable {
    private long id;
    private Date creationDate;
    private Date lastModifiedDate;
}
