package com.cities.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cities.service.CityService;
import com.cities.util.CityUtils;

@Service
public class CityServiceImpl implements CityService {
	public static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	public String areCitiesConnected(String originCity, String destinationCity, String resourceFilePath) {
		LOGGER.info("In the areCitiesConnected method of CityServiceImpl");
		
		String filePath=CityUtils.getFilePath(!StringUtils.isEmpty(resourceFilePath)?resourceFilePath:CityUtils.FILENAME);
		
		Set<String> connectedCities1 =new CityUtils().getConnectedCitiesList(filePath,
				originCity);
		Set<String> connectedCities2 = new CityUtils().getConnectedCitiesList(filePath, destinationCity);

		return CityUtils.areConnected(connectedCities1, connectedCities2);
	}

}
