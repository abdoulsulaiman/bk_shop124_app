package com.bk.shop4.demo.ServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;


import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.Repository.CargoRepo;
import com.bk.shop4.demo.Repository.ClientOrderRepo;
import com.bk.shop4.demo.Repository.ClientRepo;
import com.bk.shop4.demo.Repository.DrinkRepo;
import com.bk.shop4.demo.Repository.OrderItemRepo;
import com.bk.shop4.demo.Service.IDrinkService;



@RunWith(SpringRunner.class)
public class DrinkServiceImplIntegrationTest {
	
	 @TestConfiguration
	    static class DrinkServiceImplTestContextConfiguration {
	        @Bean
	        public IDrinkService drinkservice() {
	            return new DrinkServiceImpl();
	        }
	    }
	 
	
	  @Autowired
	  private DrinkServiceImpl drinkService;

	    @MockBean
	    private DrinkRepo drinkrepo;
	    
	    @MockBean
	    private ClientOrderRepo orderrepo;
	    
	    @MockBean
		OrderItemRepo itemrepo;
		
	    @MockBean
		ClientRepo crepo;
		
	    @MockBean
		CargoRepo cargorepo;

	    
		@Test
		public void testCreateDrink() {
			Drink drink=new Drink();
			drink.setName("Juice");
			drink.setDescription("Juice Desc");
			drink.setPrice(700.0);
			drink.setQuantity(10);
			
			Mockito.when(drinkrepo.save(drink)).thenReturn(drink);
			assertThat(drinkService.create(drink)).isEqualTo(drink);
		}
		
		@Test
		public void testGetDrinkByName() {
			Drink drink=new Drink();
			drink.setName("Juice");
			drink.setDescription("Juice Desc");
			drink.setPrice(700.0);
			drink.setQuantity(10);
			Mockito.when(drinkrepo.findByName("Juice")).thenReturn(drink);
			assertThat(drinkService.getDrinkByName("Juice")).isEqualTo(drink);
		}

}
