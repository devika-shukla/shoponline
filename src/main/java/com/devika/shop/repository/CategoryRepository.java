package com.devika.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.devika.shop.jpaEntity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();

	Category findByTitleIgnoreCase(String title);
}
