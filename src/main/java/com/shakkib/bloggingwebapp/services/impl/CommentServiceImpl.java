package com.shakkib.bloggingwebapp.services.impl;

import com.shakkib.bloggingwebapp.entities.Comment;
import com.shakkib.bloggingwebapp.entities.Post;
import com.shakkib.bloggingwebapp.exceptions.ResourceNotFoundException;
import com.shakkib.bloggingwebapp.helpers.DTOs.CommentDTO;
import com.shakkib.bloggingwebapp.repositories.CommentRepository;
import com.shakkib.bloggingwebapp.repositories.PostRepository;
import com.shakkib.bloggingwebapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        Comment comment = this.modelMapper.map(commentDTO,Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        CommentDTO savedCommentDTO = this.modelMapper.map(savedComment,CommentDTO.class);
        return savedCommentDTO;
    }

    @Override
    public void deleteComment(Integer commentId) {
        /*scope of improvement*/
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(()->new ResourceNotFoundException("Comment","Id",commentId));
        this.commentRepository.delete(comment);
    }
}
