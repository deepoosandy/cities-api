package com.cities.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityUtils {
	public static final Logger LOGGER = LoggerFactory.getLogger(CityUtils.class);

	public static final String FILENAME = "cities.txt";

	private Set<String> connectedCites = null;

	public CityUtils() {
		this.connectedCites = new HashSet<>();
	}

	public static String getFilePath(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getPath();
	}

	public Set<String> getConnectedCitiesList(String fileName, String cityName) {

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.filter(line -> line.contains(cityName)).forEach(line -> {
				String city = Arrays.asList(line.split(",")).stream().filter(c -> !c.equalsIgnoreCase(fileName))
						.map(String::trim).findFirst().get();
				if (!connectedCites.contains(city)) {
					connectedCites.add(city);
					getConnectedCitiesList(fileName, city);
				}

			});

		} catch (IOException e) {
			LOGGER.error("Exception : ", e);
		}
		return connectedCites;

	}

	public static String areConnected(Set<String> cities1, Set<String> cities2) {
		String areConnected = "NO";
		for (String city : cities1) {
			boolean areConnectedFlag = cities2.stream().anyMatch(p -> (p.trim()).equalsIgnoreCase(city.trim()));
			if (areConnectedFlag) {
				areConnected = "YES";
				break;
			}
		}
		return areConnected;

	}

}
