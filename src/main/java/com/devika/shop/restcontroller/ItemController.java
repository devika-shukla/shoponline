package com.devika.shop.restcontroller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devika.shop.jpaEntity.Item;
import com.devika.shop.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	ItemService itemService;

	@GetMapping(path = "/categories/{categoryId}/items")
	public List<Item> getItemsOfCategory(@PathVariable long categoryId) {
		return itemService.getItemsOfCategory(categoryId);
	}

	@PostMapping(path = "/categories/{categoryId}/items")
	public ResponseEntity<Void> addItemsToCategory(@Validated @PathVariable long categoryId,
			@Validated @RequestBody Item item) {
		item.setCategoryId(categoryId);
		Item itemAdded = itemService.addItemToCategory(item);

		if (itemAdded == null) {
			ResponseEntity.noContent().build();
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
