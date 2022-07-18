package com.bk.shop4.demo.Controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

import org.mockito.internal.verification.VerificationModeFactory;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.reset;

import com.bk.shop4.demo.Domain.Drink;

import com.bk.shop4.demo.ServiceImpl.DrinkServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=DrinkController.class,excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class DrinkControllerUnitTest {
	
	

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DrinkServiceImpl service;

    @Before
    public void setUp() throws Exception {
    }
	
	   @Test
	    public void CreateDrink() throws Exception {
	        Drink drink = new Drink();
	        drink.setName("d1");
	        drink.setDescription("d1 Desc");
	        drink.setPrice(700.0);
	        drink.setQuantity(10);
	        
	        given(service.CreateInitial(Mockito.any())).willReturn(drink);

	        mvc.perform(post("/api/drinks/save/").contentType(MediaType.APPLICATION_JSON).content(mapToJson(drink))).andExpect(status().isOk());
	        verify(service, VerificationModeFactory.times(1)).CreateInitial(Mockito.any());
	        reset(service);
	    }
	   
	
	   @Test
	    public void FindAllDrinkTest() throws Exception {
	       
		   Drink drink = new Drink();
	        drink.setName("d1");
	        drink.setDescription("d1 Desc");
	        drink.setPrice(700.0);
	        drink.setQuantity(10);
	        
	        Drink drink1 = new Drink();
	        drink.setName("d2");
	        drink.setDescription("d2 Desc");
	        drink.setPrice(700.0);
	        drink.setQuantity(10);
	        Drink drink2 = new Drink();
	        
	        drink.setName("d3");
	        drink.setDescription("d3 Desc");
	        drink.setPrice(700.0);
	        drink.setQuantity(10);

	        List<Drink> alldrinks = Arrays.asList(drink, drink1, drink2);

	        given(service.findAll()).willReturn(alldrinks);

	        mvc.perform(get("/api/drinks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	        verify(service, VerificationModeFactory.times(1)).findAll();
	        reset(service);
	    }
	   
	   private String mapToJson(Object object)throws JsonProcessingException{
				ObjectMapper objectMapper=new ObjectMapper();
				return objectMapper.writeValueAsString(object);
			}
		   
}

