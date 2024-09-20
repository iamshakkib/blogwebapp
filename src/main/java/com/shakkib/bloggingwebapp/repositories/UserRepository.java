package com.shakkib.bloggingwebapp.repositories;

import java.util.Optional;

import com.shakkib.bloggingwebapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);

	@Query("select exists (select email from users as u where u.email=?1)")
	Boolean existsByEmail(String email);
}
