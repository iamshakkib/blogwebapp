package com.shakkib.bloggingwebapp.repositories;

import com.shakkib.bloggingwebapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
