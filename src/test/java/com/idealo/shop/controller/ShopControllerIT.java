package com.idealo.shop.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealo.shop.Application;
import com.idealo.shop.jpaEntity.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShopControllerIT {

	@LocalServerPort
	private int port;
	private TestRestTemplate restTemplate = new TestRestTemplate();
	private HttpHeaders header = new HttpHeaders();

	@Test
	public void testGetAllCategories() {
		HttpEntity entity = new HttpEntity(null, header);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categories"), HttpMethod.GET,
				entity, String.class);
		String expected = "{\"id\":1,\"title\":\"Shoes\"},{\"id\":2,\"title\":\"Furniture\"}";
		String actual = response.getBody();
		assertTrue(actual.contains(expected));

	}

	@Test
	public void testAddCategory() {
		Category mockCategory = new Category("MOCK 1");

		HttpEntity entity = new HttpEntity(mockCategory, header);
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categories"), HttpMethod.POST,
				entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		assertTrue(actual.contains("/categories"));

	}

	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}
}
