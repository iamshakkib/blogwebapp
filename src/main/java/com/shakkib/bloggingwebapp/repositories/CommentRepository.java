package com.shakkib.bloggingwebapp.repositories;

import com.shakkib.bloggingwebapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
