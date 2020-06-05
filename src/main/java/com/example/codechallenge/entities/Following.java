package com.example.codechallenge.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Following {

    @NotBlank(message = "follower user is mandatory")
    @ApiModelProperty("The follower user")
    @Getter @Setter private String follower;

    @NotBlank(message = "user to be followed is a mandatory field")
    @ApiModelProperty("User to be followed by provided follower")
    @Getter @Setter private String followedUser;
}
