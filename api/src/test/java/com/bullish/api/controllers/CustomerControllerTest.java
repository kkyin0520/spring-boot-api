package com.bullish.api.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bullish.api.service.StoreService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

	@Test
	void testCreateCatalogProduct() {
		String examplePurchase = "{\"amount\":\"1\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/basket/product/0")
                .accept(MediaType.APPLICATION_JSON).content(examplePurchase)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
	        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	        assertEquals("", response.getContentAsString());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void testRemoveCatalogProduct() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/basket/product/0");
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
	        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());       
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void getReceipt() {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/receipt");
        MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();

			MockHttpServletResponse response = result.getResponse();
	        assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
	        assertEquals("", response.getContentAsString());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
