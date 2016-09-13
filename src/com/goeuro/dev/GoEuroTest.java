package com.goeuro.dev;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		
		/*
		 * API gives response for anything rather than empty string.
		 */
		String cityName = args[0];
		if (cityName.equals("")) {
			throw new IllegalArgumentException(
					"Empty string is not valid argument for city name.");
		}
		parsedArgs.put(CITY_NAME, cityName);
		
		if (args.length > 1) {
			String outputFile = args[1];
			
			/*
			 * Method will throw IllegalArgumentException if path is not valid
			 */
			validateFIlePath(outputFile);
			
			parsedArgs.put(OUTPUT_FILE, outputFile);
		}
		
		return parsedArgs;
	}
	
	private static void validateFIlePath(String filepath) throws IllegalArgumentException {
		FileWriter fw = null;
		try {
			File file = new File(filepath);
			if (!file.exists() && file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			fw = new FileWriter(file);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Invalid output file path.");
		} finally {
			try {
				if (fw != null) {
					fw.close(); 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
