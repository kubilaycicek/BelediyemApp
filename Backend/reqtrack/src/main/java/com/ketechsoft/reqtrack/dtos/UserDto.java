package com.ketechsoft.reqtrack.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User Data Transfer Object")
public class UserDto extends BaseDto {
    @ApiModelProperty(value = "NAME")
    private String name;
    @ApiModelProperty(value = "SURNAME")
    private String surname;
    @ApiModelProperty(value = "PHONE")
    private String phone;
    @ApiModelProperty(value = "TC NUMBER")
    private String tcNumber;
    @ApiModelProperty(value = "EMAIL")
    private String email;
    @ApiModelProperty(value = "PASSWORD")
    private String password;
    @ApiModelProperty(value = "ADDRESS")
    private String address;
    @ApiModelProperty(value = "USER_TYPE")
    private UserTypeDto userTypeDto;
}
