package com.shakkib.bloggingwebapp.services;

import com.shakkib.bloggingwebapp.helpers.DTOs.PostDTO;
import com.shakkib.bloggingwebapp.helpers.DTOs.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId);

    PostDTO updatePost(PostDTO postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Integer postId);

    List<PostDTO> getPostsByCategory(Integer categoryId);

    List<PostDTO> getPostsByUser(Integer userId);

    List<PostDTO> searchPosts(String keyword);
}
