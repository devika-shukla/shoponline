package com.devika.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.devika.shop.jpaEntity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

	List<Item> findByCategoryId(long categoryId);

}
