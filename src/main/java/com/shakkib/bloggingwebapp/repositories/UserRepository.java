package com.shakkib.bloggingwebapp.repositories;

import java.util.Optional;

import com.shakkib.bloggingwebapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
		
	
	Optional<User> findByEmail(String email);
}
