package com.bk.shop4.demo.Controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bk.shop4.demo.InnerDomain.InnerDrinkData;
import com.bk.shop4.demo.Service.IDrinkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;








@RestController
@Tag(name = " Drinks Endpoints",description = "All Endpoints Related To Drinks")
@RequestMapping("api/drinks")
public class DrinkController {
	
	@Autowired
	IDrinkService drinkservice;
	
	@Operation(summary = "Get a list of all drinks", description = "Endpoints for Retrieving all Drinks")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_() {
	
		return new ResponseEntity<Object>(drinkservice.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get a specific drink by ID", description = "Endpoints for Retrieving A Specific Drink by ID")
	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getDrinkByUuid(@PathVariable("uuid") String uuid) {

		return new ResponseEntity<Object>(drinkservice.findByUuid(uuid), HttpStatus.OK);
	}
	
	@Operation(summary = "Delete a specific drink by ID", description = "Endpoints for Deleting A Specific Drink by ID")
	@RequestMapping(method = RequestMethod.DELETE,value = "/delete/{uuid}",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public  ResponseEntity<Object> deleteDrink(@PathVariable String uuid){
		 drinkservice.delete(uuid);
	    return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@Operation(summary = "Creating a New Drink ", description = "Endpoints for Creating A New Drink")
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterRequest(@RequestBody InnerDrinkData params) throws ParseException {

		return new ResponseEntity<>(drinkservice.CreateInitial(params), HttpStatus.OK);
	}
	
	@Operation(summary = "Get a list of most consumed drinks and quantity ", description = "Endpoints for Getting a list of most consumed drinks and quantity")
	@RequestMapping(value = "/most_consumed/Drinks/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMostConsumedDrink() {
		
		return new ResponseEntity<Object>(drinkservice.getMostConsumedDrink(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get All Avalaible Drinks and Nearest Cargo Based on Client Location ", description = "Endpoints for Get a list of all available drinks and nearest cargo company to the client within 3km based on the client’s location")
	@RequestMapping(value = "byClient/{uuid}/AllData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllDrinkAndNearestCargo(@PathVariable("uuid") String uuid) {
			
		return new ResponseEntity<Object>(drinkservice.GetAllDrinkWithNearestCargo(uuid), HttpStatus.OK);
	}
	

}
