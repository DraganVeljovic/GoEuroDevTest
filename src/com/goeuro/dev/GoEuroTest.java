package com.goeuro.dev;

import java.io.IOException;

import com.goeuro.dev.api.Api;
import com.goeuro.dev.api.data.PositionData;
import com.goeuro.dev.csv.CsvUtil;
import com.google.gson.JsonArray;

public class GoEuroTest {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println(
					"GoEuroTest cityName "
					+ "[ csvFilename = output.csv ] "
					+ "[ csvLocation = current directory ]");
			System.exit(1);
		}
		
		String cityName = args[0];
		if (!cityName.matches("[A-Z][a-z]+")) {
			System.out.println("Invalid city name.\nCity name must match following pattern '[A-Z][a-z]+'.");
		
		}
		
		Api api = new Api();
		CsvUtil csvUtil = new CsvUtil();
		
		JsonArray cities = api.getPositionSuggestion(cityName);
		
		csvUtil.writeToCsv(cities, new PositionData(), "output.csv");
	}
}
