package com.cities.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CityServiceImplTest {

	private CityServiceImpl cityServiceImpl = new CityServiceImpl();

	@Test
	public void areCitiesConnectedForConnectedtest() {
		String areConnected = cityServiceImpl.areCitiesConnected("banglore", "Lucknow", "testcities.txt");
		assertEquals("YES", areConnected);
	}

	@Test
	public void areCitiesConnectedForNotConnectedtest() {
		String areConnected = cityServiceImpl.areCitiesConnected("Trenton", "Lucknow", "testcities.txt");
		assertEquals("NO", areConnected);
	}

}
