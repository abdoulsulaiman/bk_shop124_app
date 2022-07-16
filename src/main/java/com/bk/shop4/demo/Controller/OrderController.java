package com.bk.shop4.demo.Controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.bk.shop4.demo.Domain.Cargo;
import com.bk.shop4.demo.Domain.Client;
import com.bk.shop4.demo.InnerDomain.InnerCompleteOrder;
import com.bk.shop4.demo.InnerDomain.InnerOrderData;
import com.bk.shop4.demo.Service.ICargoService;
import com.bk.shop4.demo.Service.IClientOrderService;
import com.bk.shop4.demo.Service.IClientService;
import io.swagger.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Client Orders Endpoints",description = "All Endpoints Related To Client Orders")
@RequestMapping("api/orders")
public class OrderController {
	
	@Autowired
	IClientOrderService orderservice;
	
	@Autowired
	IClientService clientservice;
	
	@Autowired
	ICargoService cargoservice;
	
	@Operation(summary = "Get All Orders", description = "Endpoints for Retrieving all Orders")
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(HttpServletRequest request) {
	
		return new ResponseEntity<Object>(orderservice.findAll(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get A Specific Order By ID", description = "Endpoints for Retrieving Order Detail by ID")
	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCargoByUuid(HttpServletRequest request, @PathVariable("uuid") String uuid) {

		return new ResponseEntity<Object>(orderservice.findByUuid(uuid), HttpStatus.OK);
	}
	@Operation(summary = "Delete A Specific Order by ID", description = "Endpoints for Deleting A Specific Order by ID")
	@RequestMapping(method = RequestMethod.DELETE,value = "/delete/{uuid}",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public  ResponseEntity<Object> deleteCargo(HttpServletRequest request, @PathVariable String uuid){
		orderservice.delete(uuid);
	    return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@Operation(summary = "Create A New Order", description = "Endpoints for Creating A New Order")
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Object> RegisterCargo(@RequestBody InnerOrderData params, HttpSession session) throws ParseException {

		return new ResponseEntity<>(orderservice.createInitialOrder(params), HttpStatus.OK);
	}
	
	@Operation(summary = "Complete An Order", description = "Endpoints for Completing An Order")
	@RequestMapping(value = "/{uuid}/complete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> CompleteOrder(HttpServletRequest request,@RequestBody InnerCompleteOrder params) {
			
		return new ResponseEntity<Object>(orderservice.completeOrder(params), HttpStatus.OK);
	}
	@Operation(summary = "Get top 10 paid orders, their client details, and transporter details", description = "Endpoints for Retrieving Top 10 most Paid Orders")
	@RequestMapping(value = "/get_most_paid/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getMostPaidOrders(HttpServletRequest request) {
			
		return new ResponseEntity<Object>(orderservice.getMostPaidOrder(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get top five orders that were requested by different clients", description = "Endpoints for Retrieving top five orders that were requested by different clients")
	@RequestMapping(value = "/get_top_five/client/orders/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getClientTopFiveOrder(HttpServletRequest request) {
			
		return new ResponseEntity<Object>(orderservice.getClientTopFiveOrder(), HttpStatus.OK);
	}

}
