package com.example.codechallenge.controllers;

import com.example.codechallenge.entities.Following;
import com.example.codechallenge.entities.Post;
import com.example.codechallenge.repository.TwitterRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/twitter-api")
@Api(value="twitter-api", description="API for simple twitter-like application")
public class TwitterRestController {

    @Autowired
    private TwitterRepository repository;

    @PostMapping("/posting/{user}")
    @ApiOperation("Post new message as particular user")
    public void posting(@PathVariable String user, @Valid @RequestBody Post post){
        repository.save(user, post);
    }

    @GetMapping("/wall/{user}")
    @ApiOperation("List of all user's posts sorted in reverse chronological order")
    public List<Post> wall(@PathVariable String user){
        return repository.wall(user);
    }

    @PostMapping("/follow")
    @ApiOperation("Action for following particular user")
    public void follow(@Valid @RequestBody Following following){
        repository.follow(following.getFollower(), following.getFollowedUser());
    }

    @GetMapping("/timeline/{user}")
    @ApiOperation("List of all posts created by users the provided user is following in reverse chronological order")
    public List<Post> timeline(@PathVariable String user){
        return repository.timeline(user);
    }

}
