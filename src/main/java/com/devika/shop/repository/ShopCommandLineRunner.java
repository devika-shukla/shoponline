package com.devika.shop.repository;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.devika.shop.jpaEntity.Category;
import com.devika.shop.jpaEntity.Item;

@Component
public class ShopCommandLineRunner implements CommandLineRunner {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub

		Category category1 = new Category("Shoes");
		Category category2 = new Category("Furniture");

		categoryRepository.save(category1);
		categoryRepository.save(category2);

		Item item1 = new Item(1, "Adidas", "Sports Shoes", new BigDecimal(100.75));
		Item item2 = new Item(1, "Nike", "Running Shoes", new BigDecimal(180.75));
		Item item3 = new Item(2, "IKEA", "Room Table", new BigDecimal(60.25));
		Item item4 = new Item(2, "IKEA", "Sofa Set", new BigDecimal(680.75));

		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);
		itemRepository.save(item4);

	}

}
