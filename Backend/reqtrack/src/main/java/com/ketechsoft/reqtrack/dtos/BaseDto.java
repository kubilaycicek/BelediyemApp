package com.ketechsoft.reqtrack.dtos;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseDto implements Serializable {
    private long id;
}
