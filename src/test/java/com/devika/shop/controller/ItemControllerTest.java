package com.devika.shop.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.devika.shop.jpaEntity.Item;
import com.devika.shop.restcontroller.ItemController;
import com.devika.shop.service.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	ItemService service;

	String uri = "/categories/1/items";
	Item item1 = new Item(1, "Item 1", "Item text 1", new BigDecimal(10));
	Item item2 = new Item(1, "Item 2", "Item text 2", new BigDecimal(20));

	@Test
	public void testGetItemsOfCategory() throws Exception {

		List<Item> itemList = Arrays.asList(item1, item2);

		Mockito.when(service.getItemsOfCategory(Mockito.any(Long.class))).thenReturn(itemList);

		RequestBuilder builder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(builder).andReturn();

		String actual = result.getResponse().getContentAsString();

		assertTrue(actual.contains("Item 1"));
		assertTrue(actual.contains("Item text 1"));
		assertTrue(actual.contains("Item 2"));
		assertTrue(actual.contains("Item text 2"));

	}

	@Test
	public void testAddItemsToCategory() throws Exception {

		String itemJson = "{\"id\": 1,\"categoryId\": 1,\"title\":\"Item 1\", \"text\":\"Item text 1\",\"price\": 10}";

		Mockito.when(service.addItemToCategory(Mockito.any(Item.class))).thenReturn(item1);

		RequestBuilder builder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(itemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(builder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
