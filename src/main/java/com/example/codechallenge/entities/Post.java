package com.example.codechallenge.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Post {

    @NotBlank(message = "message is mandatory")
    @Size(max = 140, message = "message must be shorter that 140 characters")
    @ApiModelProperty("Post message. Must be shorter than 140 characters")
    @Getter @Setter private String message;

    @ApiModelProperty("Date of post's creation. If not provided the current server time will be set as default")
    @Getter @Setter private Date date = Calendar.getInstance().getTime();

}
