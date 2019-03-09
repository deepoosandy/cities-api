package com.cities.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cities.service.CityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-14T16:58:08.247Z")
@RestController
public class CitiesController {

	@Autowired
	private CityService cityService;

	   @ApiOperation(value = "", nickname = "areCitiesConnected", notes = "This end point will give YES if cities are connected else NO", tags={  })
	    @ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "Cites are connected"),
	        @ApiResponse(code = 400, message = "Bad request if cities are not connected")
	        })
	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public ResponseEntity<String> areCitiesConnected(@NotNull @ApiParam(value = "should be a origin name ", required = true) @Valid @RequestParam("origin") String origin,
			@NotNull @ApiParam(value = "should be a destination name.", required = true) @Valid @RequestParam("destination") String destination) {
		return cityService.areCitiesConnected(origin, destination,null).equalsIgnoreCase("YES")
				? new ResponseEntity<>(cityService.areCitiesConnected(origin, destination,null), HttpStatus.OK)
				: new ResponseEntity<>(cityService.areCitiesConnected(origin, destination,null), HttpStatus.BAD_REQUEST);

	}
}
