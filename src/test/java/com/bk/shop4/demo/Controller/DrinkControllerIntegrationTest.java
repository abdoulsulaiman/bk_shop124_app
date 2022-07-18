package com.bk.shop4.demo.Controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bk.shop4.demo.Application;
import com.bk.shop4.demo.Domain.Drink;
import com.bk.shop4.demo.InnerDomain.InnerDrinkData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DrinkControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate testRestTemplate;
	private HttpHeaders headers=new HttpHeaders();
	
	
	@Test
	public void testCreateStudent() throws Exception {
		InnerDrinkData data=new InnerDrinkData();
		data.setName("Juice");
		data.setDescription("Juice Desc");
		data.setPrice(700);
		data.setQuantity(10);
		
		Drink drink=new Drink();
		drink.setName("Juice");
		drink.setDescription("Juice Desc");
		data.setPrice(700);
		
		drink.setQuantity(10);
		String URITOCreateStudent="/api/drinks/save/";
		String inputiInJson=this.mapToJson(data);
		
		HttpEntity<InnerDrinkData>entity=new HttpEntity<InnerDrinkData>(data,headers);
		ResponseEntity<String>response=testRestTemplate.exchange(
				formFullURLWithPort(URITOCreateStudent), HttpMethod.POST, entity, String.class);
	
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
	}
	
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	
	private String formFullURLWithPort(String uri) {
		return "http://localhost:"+port+uri;
	}

}
