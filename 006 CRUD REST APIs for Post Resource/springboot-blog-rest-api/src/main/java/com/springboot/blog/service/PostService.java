package com.springboot.blog.service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import java.util.List;


public interface PostService {
    PostDto createPost(PostDto postDto);
    List<Post> getAllPosts();
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void deletePostById(long id);
}
