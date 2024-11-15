package com.shakkib.bloggingwebapp.helpers.DTOs;

import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDTO {

    private Integer postId;

    private String title;

    private String content;

    private String imageName;

    @CurrentTimestamp
    private Date creationTime;

    private CategoryDTO categoryDTO;

    private UserDTO userDTO;

    private Set<CommentDTO> commentDTOSet = new HashSet<>();

    public PostDTO() {
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Set<CommentDTO> getCommentDTOSet() {
        return commentDTOSet;
    }

    public void setCommentDTOSet(Set<CommentDTO> commentDTOSet) {
        this.commentDTOSet = commentDTOSet;
    }
}
