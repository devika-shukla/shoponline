package com.idealo.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.idealo.shop.jpaEntity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();

	Category findByTitleIgnoreCase(String title);
}
