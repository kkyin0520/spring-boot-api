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
@WebMvcTest(AdminController.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoreService storeService;
    
 
	@Test
	void testCreateCatalogProduct() {
		String exampleProduct = "{\"name\":\"Peach\",\"price\":\"10.5\"}";
	
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/admin/product")
                .accept(MediaType.APPLICATION_JSON).content(exampleProduct)
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
	            .delete("/admin/product/0");
	
	    MvcResult result = null;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
	
			MockHttpServletResponse response = result.getResponse();
	        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	        assertEquals("", response.getContentAsString());
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void testCreateDiscount() {
		String exampleDisconut = "{\"description\":\"B1G1 50%\",\"discountLot\":\"2\",\"discountRate\":\"0.75\"}";
		
	    RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/admin/product/0/discount")
	            .accept(MediaType.APPLICATION_JSON).content(exampleDisconut)
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
}
