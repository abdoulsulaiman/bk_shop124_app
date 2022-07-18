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

import com.bk.shop4.demo.InnerDomain.InnerClientData;

import com.bk.shop4.demo.Service.IClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = " Clients Endpoints",description = "All Endpoints Related To Clients")
@RequestMapping("api/clients")
public class ClientController {
	
	
	@Autowired
	IClientService clientservice;
	
	
	@Operation(summary = "Get a list of all clients", description = "Endpoints for Retrieving a list of all clients")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_clients() {
	
		return new ResponseEntity<Object>(clientservice.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get a specific client by ID", description = "Endpoints for Retrieving  a specific client by ID")
	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClientByUuid(@PathVariable("uuid") String uuid) {

		return new ResponseEntity<Object>(clientservice.findByUuid(uuid), HttpStatus.OK);
	}
	
	@Operation(summary = "Deleting a specific client by ID", description = "Endpoints for Deleting  a specific client by ID")
	@RequestMapping(method = RequestMethod.DELETE,value = "/delete/{uuid}",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public  ResponseEntity<Object> deleteClient(@PathVariable String uuid){
		clientservice.delete(uuid);
	    return new ResponseEntity<Object>(HttpStatus.OK);
	}
	@Operation(summary = "Create  a new client ", description = "Endpoints for Creating  a new client")
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterClient(@RequestBody InnerClientData params) throws ParseException {

		return new ResponseEntity<>(clientservice.CreateInitial(params), HttpStatus.OK);
	}
	
	@Operation(summary = "Get a list of the 3 closest cargo companies ", description = "Endpoints for Retrieving  a list of the 3 closest cargo companies by A specific Client ")
	@RequestMapping(value = "/{uuid}/closest_cargoes/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClientClosestCargoCompanies(@PathVariable("uuid") String uuid) {

		return new ResponseEntity<Object>(clientservice.GetClosestCargo(uuid), HttpStatus.OK);
	}
	
}
