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

import com.bk.shop4.demo.InnerDomain.InnerCargoData;
import com.bk.shop4.demo.InnerDomain.InnerFrameData;
import com.bk.shop4.demo.Service.ICargoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Cargo Endpoints",description = "All Endpoints Related To Cargo Companies")
@RequestMapping("api/cargoes")
public class CargoController {
	@Autowired
	ICargoService cargoservice;
	
	@Operation(summary = "Get All Cargo Companies", description = "Endpoints for Retrieving all Cargo Companies")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_() {
	
		return new ResponseEntity<Object>(cargoservice.findAll(), HttpStatus.OK);
	}
	@Operation(summary = "Get a Specific Cargo Companies by Id", description = "Endpoints for Retrieving  Cargo Company by ID")
	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCargoByUuid(@PathVariable("uuid") String uuid) {

		return new ResponseEntity<Object>(cargoservice.findByUuid(uuid), HttpStatus.OK);
	}
	@Operation(summary = "Delete a Specific Cargo Companies by Id", description = "Endpoints for Deleting  Cargo Company by ID")
	@RequestMapping(method = RequestMethod.DELETE,value = "/delete/{uuid}",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public  ResponseEntity<Object> deleteCargo(@PathVariable String uuid){
		cargoservice.delete(uuid);
	    return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@Operation(summary = "Save A New Cargo Company", description = "Endpoints for Saving A New  Cargo Company ")
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterCargo(@RequestBody InnerCargoData params) throws ParseException {

		return new ResponseEntity<>(cargoservice.CreateInitial(params), HttpStatus.OK);
	}
	@Operation(summary = "Get List Drinks Cargo Company Transported by Date Range ", description = "Endpoints for Retrieving List of Drinks Transported by A specific Cargo Company by Date Range ")
	@RequestMapping(value = "/{uuid}/get_list_of_transported_drinks/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> get_list_of_transported_drinks(@PathVariable("uuid") String uuid,@RequestBody InnerFrameData params) {

		return new ResponseEntity<Object>(cargoservice.GetDrinksByDateRange(uuid, params.getStartDate(), params.getEndDate()), HttpStatus.OK);
	}
	
}
