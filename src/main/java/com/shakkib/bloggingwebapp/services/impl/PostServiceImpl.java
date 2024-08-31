package com.shakkib.bloggingwebapp.services.impl;

import com.shakkib.bloggingwebapp.entities.Category;
import com.shakkib.bloggingwebapp.entities.Post;
import com.shakkib.bloggingwebapp.entities.User;
import com.shakkib.bloggingwebapp.exceptions.ResourceNotFoundException;
import com.shakkib.bloggingwebapp.helpers.DTOs.PostDTO;
import com.shakkib.bloggingwebapp.helpers.DTOs.PostResponse;
import com.shakkib.bloggingwebapp.repositories.CategoryRepository;
import com.shakkib.bloggingwebapp.repositories.PostRepository;
import com.shakkib.bloggingwebapp.repositories.UserRepository;
import com.shakkib.bloggingwebapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setCreationTime(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = this.postRepository.save(post);
        PostDTO postDTO = this.modelMapper.map(savedPost,PostDTO.class);
        return postDTO;
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        Category category=null;
        if(post.getCategory().getCategoryId() != null) {
            category = this.categoryRepository.findById(postDto.getCategoryDTO().getCategoryId()).get();
        }
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setCategory(category);
        Post updatedPost = this.postRepository.save(post);
        PostDTO postDTO = this.modelMapper.map(updatedPost,PostDTO.class);
        return postDTO;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepository.findAll(page);
        List<Post> allPosts = pagePost.getContent();
        List<PostDTO> postDTOList = allPosts.stream().map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Id",postId));
        PostDTO postDTO = this.modelMapper.map(post,PostDTO.class);
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        //* scope of improvement*//
        Category cat = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepository.findByCategory(cat);
        List<PostDTO> postDTOList = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        List<Post> posts=null;
        if((user!=null)&&Integer.valueOf(user.getId())!=null){
            posts = this.postRepository.findByUser(user);
        }
        List<PostDTO> postDTOList = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        List<Post> posts = this.postRepository.searchByTitle("%"+keyword+"%");
        List<PostDTO> postDTOList = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOList;
    }
}
