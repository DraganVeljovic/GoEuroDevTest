package com.goeuro.dev;

import java.util.HashMap;

import com.goeuro.dev.api.Api;
import com.goeuro.dev.api.data.PositionData;
import com.goeuro.dev.csv.CsvUtil;
import com.google.gson.JsonArray;


public class GoEuroTest {
	private static String usage() {
		return "Usage: "
				+ "GoEuroTest cityName "
				+ "[ csvFile = output.csv ] ";
	}
	
	private static final String CITY_NAME = "cityName";
	private static final String OUTPUT_FILE = "outputFile";
	private static HashMap<String, String> parseArgs(String[] args) throws IllegalArgumentException {
		HashMap<String, String> parsedArgs = new HashMap<String, String>();
		
		if (args.length == 0 || args.length > 2) {
			throw new IllegalArgumentException(usage());
		}
		
		String cityName = args[0];
		if (!cityName.matches("^[a-zA-Z\b]+$")) {
			throw new IllegalArgumentException(
					"Invalid city name.\nCity name must match following pattern '^[a-zA-Z\b]+$'.");
		}
		parsedArgs.put(CITY_NAME, cityName);
		
		if (args.length > 1) {
			String outputFile = args[1];
			parsedArgs.put(OUTPUT_FILE, outputFile);
		}
		
		return parsedArgs;
	}
	
	public static void main(String[] args) {
		try {
			HashMap<String, String> parsedArgs = parseArgs(args);
			
			Api api = new Api();
			CsvUtil csvUtil = new CsvUtil();
			
			String cityName = parsedArgs.get(CITY_NAME);
			JsonArray cities = api.getPositionSuggestion(cityName);
			
			String outputFile = parsedArgs.get(OUTPUT_FILE);
			csvUtil.writeToCsv(cities, PositionData.class, outputFile == null ? "output.csv" : outputFile);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
