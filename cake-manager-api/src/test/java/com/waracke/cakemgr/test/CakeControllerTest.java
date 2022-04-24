package com.waracke.cakemgr.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.waracle.cakemgr.dto.CakeDto;

public class CakeControllerTest extends AbstractTest {

	@Test
	public void getCakesListTest() throws Exception {
		String uri = "/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		CakeDto[] cakeList = super.mapFromJson(content, CakeDto[].class);
		assertTrue(cakeList.length > 0);
	}

	@Test
	public void getCakesJsonTest() throws Exception {
		String uri = "/cakes";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.ALL)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertNotNull(content);
	}

	@Test
	@Transactional
	public void createCakesTest() throws Exception {
		String uri = "/cakes";
		CakeDto cake = new CakeDto("Red Velvet", "Fin red velvet cake",
				"https://cafedelites.com/wp-content/uploads/2018/05/Red-Velvet-Cake-IMAGE-43.jpg");

		String inputJson = super.mapToJson(cake);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString().toLowerCase();
		assertEquals(content, "true");
	}

}
