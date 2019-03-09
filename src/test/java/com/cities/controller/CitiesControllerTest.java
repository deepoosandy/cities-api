package com.cities.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cities.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class CitiesControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CitiesController citiesController;

	@Mock
	private CityService cityService;
	private String jsonInString = null;
	private ObjectMapper objectMapper=new ObjectMapper();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(citiesController).build();
	}

	@Test
	public void areCitiesConnectedForNotConnectedCitestest() throws Exception {
		jsonInString=objectMapper.writeValueAsString("NO");
		when(cityService.areCitiesConnected("Philadelphia", "Albany",null)).thenReturn("NO");
		mockMvc.perform(get("/connected?origin=Philadelphia&destination=Albany").contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void areCitiesConnectedForConnectedtest() throws Exception {
		jsonInString=objectMapper.writeValueAsString("YES");
		when(cityService.areCitiesConnected("Boston", "Philadelphia",null)).thenReturn("YES");
		mockMvc.perform(get("/connected?origin=Boston&destination=Philadelphia").contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString)).andExpect(status().isOk());
	}

}
