package com.example.codechallenge.repository;

import com.example.codechallenge.entities.Post;

import java.util.List;

public interface TwitterRepository {

    void save(String userName, Post post);

    List<Post> wall(String userName);

    void follow(String follower, String user);

    List<Post> timeline(String userName);
}
