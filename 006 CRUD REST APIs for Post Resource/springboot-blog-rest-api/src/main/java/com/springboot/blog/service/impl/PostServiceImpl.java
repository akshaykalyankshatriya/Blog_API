package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    private  static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    @Override
    public PostDto createPost(PostDto postDto) {
        logger.info("PostServiceImpl.createPost() : : converting DTO to Entity ={}", postDto.getId());
        Post post = mapToEntity(postDto);
        logger.info("PostServiceImpl.createPost() : : save request into database ={}", postDto.getId());
        Post newPost  = postRepository.save(post);
        logger.info("PostServiceImpl.createPost() : : converting Entity to DTO ={}", newPost.getId());
        PostDto postResponse = mapToDto(post);
        logger.info("PostServiceImpl.createPost() : : sending response to controller ={}",postResponse.getId());
        return postResponse;
    }

    @Override
    public List<Post> getAllPosts() {
        logger.info("PostServiceImpl.getAllPost(), : : sending response to controller ");
        return postRepository.findAll();
    }

    @Override
    public PostDto getPostById(long id) {
        logger.info("PostServiceImpl.getPostById() : : retrieving post by id from database ={}", id);
        Post post = postRepository.findById(id)
                                                         .orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        logger.info("PostServiceImpl.getPostById() : : retrieving post by id from  database  ={}",post.getId());
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        logger.info("PostServiceImpl.updatePost() : : retrieving post by id from  database ={} ",id);
        Post post = postRepository.findById(id)
                                                          .orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        logger.info("PostServiceImpl.updatePost() : : setting request  ={}",post.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        logger.info("PostServiceImpl.updatePost() : : saving request into database ={}",post.getId());
        Post updatePost = postRepository.save(post);
        logger.info("PostServiceImpl.updatePost() : : converting entity to Map ={}",updatePost.getId());
        return mapToDto(updatePost);
    }

    @Override
    public void deletePostById(long id) {
        logger.info("PostServiceImpl.deletePostById()  : : retrieving post by id from  database  ={} ",id);
        postRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Post","id",id));
        logger.info("PostServiceImpl.deletePostById() : : deleting post by id  ={} ", id);
        postRepository.deleteById(id);

    }

    private static PostDto mapToDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        return postDto;
    }
    private static Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }
}
