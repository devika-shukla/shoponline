package com.idealo.shop.restcontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idealo.shop.jpaEntity.Category;
import com.idealo.shop.service.ShopService;

@RestController
public class ShopController {

	@Autowired
	ShopService service;

	@GetMapping(path = "/categories")
	public List<Category> getAllCategories() {
		return service.getAllCategories();
	}

	@PostMapping(path = "/categories")
	public ResponseEntity<Void> addCategory(@Validated @RequestBody Category category) {

		Category addedCategory = service.addCategory(category);
		if (addedCategory == null) {
			ResponseEntity.noContent().build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(category.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

}
