package com.shakkib.bloggingwebapp.repositories;

import java.util.List;

import com.shakkib.bloggingwebapp.entities.Category;
import com.shakkib.bloggingwebapp.entities.Post;
import com.shakkib.bloggingwebapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
	

}
