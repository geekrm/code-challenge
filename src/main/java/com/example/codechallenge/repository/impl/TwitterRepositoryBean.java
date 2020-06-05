package com.example.codechallenge.repository.impl;

import com.example.codechallenge.entities.Post;
import com.example.codechallenge.repository.TwitterRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Stream;

@Repository
public class TwitterRepositoryBean implements TwitterRepository {

    private static final Map<String, List<Post>> userPosts = new HashMap<>();
    private static final Map<String, Set<String>> followings = new HashMap<>();

    @Override
    public void save(String userName, Post post) {
        if(!userPosts.containsKey(userName)){
            userPosts.put(userName, new LinkedList<>());
        }
        userPosts.get(userName).add(post);
    }

    @Override
    public List<Post> wall(String userName) {
        if(userPosts.containsKey(userName)){
            List<Post> posts = userPosts.get(userName);
            posts.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
            return posts;
        }
        return new LinkedList<>();
    }

    @Override
    public void follow(String follower, String user) {
        if(!followings.containsKey(follower)){
            followings.put(follower, new HashSet<>());
        }
        followings.get(follower).add(user);
    }

    @Override
    public List<Post> timeline(String userName) {
        List<Post> timelinePosts = new LinkedList<>();
        if(followings.containsKey(userName)){
            Set<String> followedUsers = followings.get(userName);
            try(Stream<String> followedUsersStream = followedUsers.stream()){
                followedUsersStream.map(u -> userPosts.get(u))
                        .filter(list -> list != null && !list.isEmpty())
                        .forEach(list -> timelinePosts.addAll(list));
            }
            timelinePosts.sort((p1, p2) -> p2.getDate().compareTo(p1.getDate()));
        }

        return timelinePosts;
    }
}
