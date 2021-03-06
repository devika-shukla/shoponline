package com.devika.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devika.shop.exception.CategoryIdDoesNotExist;
import com.devika.shop.jpaEntity.Item;
import com.devika.shop.repository.CategoryRepository;
import com.devika.shop.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public List<Item> getItemsOfCategory(long categoryId) {
		if (categoryRepository.exists(categoryId)) {
			return itemRepository.findByCategoryId(categoryId);
		}
		throw new CategoryIdDoesNotExist();
	}

	public Item addItemToCategory(Item item) {
		if (categoryRepository.exists(item.getCategoryId())) {
			return itemRepository.save(item);
		}
		throw new CategoryIdDoesNotExist();
	}

}
