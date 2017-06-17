package com.devika.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devika.shop.exception.CategoryAlreadyExist;
import com.devika.shop.jpaEntity.Category;
import com.devika.shop.repository.CategoryRepository;

@Service
public class ShopService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Category addCategory(Category category) {
		if (categoryRepository.findByTitleIgnoreCase(category.getTitle()) != null) {
			throw new CategoryAlreadyExist();
		}
		try {
			return categoryRepository.save(category);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
