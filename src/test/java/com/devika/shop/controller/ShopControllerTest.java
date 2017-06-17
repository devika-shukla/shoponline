package com.devika.shop.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.devika.shop.jpaEntity.Category;
import com.devika.shop.restcontroller.ShopController;
import com.devika.shop.service.ShopService;
import com.devika.shop.util.messages.error.ErrorCodes;
import com.devika.shop.util.messages.error.WarningCodes;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShopController.class)
public class ShopControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	ShopService shopService;

	/**
	 * Unit test to check if ShopController is getting all the categories back
	 * with url "/categories"
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAllCategories() throws Exception {

		List<Category> mockCategories = Arrays.asList(new Category("MOCK 1"), new Category("Mock 2"));
		Mockito.when(shopService.getAllCategories()).thenReturn(mockCategories);
		RequestBuilder builder = MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMVC.perform(builder).andReturn();
		String actual = result.getResponse().getContentAsString();
		assertTrue(actual.contains("MOCK 1") && actual.contains("MOCK 1"));

	}

	@Test
	public void testAddCategorySuccess() throws Exception {
		Category mockCategory = new Category("MOCK 1");
		String categoryJson = "{\"title\":\"Shoes\"}";
		Mockito.when(shopService.addCategory(Mockito.any(Category.class))).thenReturn(mockCategory);
		RequestBuilder builder = MockMvcRequestBuilders.post("/categories").accept(MediaType.APPLICATION_JSON)
				.content(categoryJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMVC.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void testAddCategoryWithNoCategoryName() throws Exception {
		Category mockCategory = new Category("MOCK 1");
		String categoryJson = "{\"title\":\"\"}";
		Mockito.when(shopService.addCategory(Mockito.any(Category.class))).thenReturn(mockCategory);
		RequestBuilder builder = MockMvcRequestBuilders.post("/categories").accept(MediaType.APPLICATION_JSON)
				.content(categoryJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMVC.perform(builder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String errorResponse = response.getContentAsString();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
		assertEquals(WarningCodes.ENTER_CATEGORY_TITLE, errorResponse);
	}

}
