package com.shakkib.bloggingwebapp.services;

import com.shakkib.bloggingwebapp.helpers.DTOs.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    void deleteComment(Integer commentId);

}
