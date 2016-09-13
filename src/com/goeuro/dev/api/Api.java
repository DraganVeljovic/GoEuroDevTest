package com.goeuro.dev.api;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVWriter;

public class Api {
	private static final String POSITION_SUGGESTION_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	private static final int HTTP_OK = 200;
	
	public JsonArray getPositionSuggestion(String cityName) throws RuntimeException {
		final String urlString = POSITION_SUGGESTION_URL + cityName;

		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 	conn.setRequestMethod("GET");
		 	conn.connect();
		 	
			if (conn.getResponseCode() != HTTP_OK) {
				throw new RuntimeException("Failed to fetch data from the server.");
			}
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			StringBuffer sb = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
			in.close();
			
			conn.disconnect();
			
			return new JsonParser().parse(sb.toString()).getAsJsonArray();		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main (String[] args) {
		Api api = new Api();
		api.getPositionSuggestion("Berlin");
	}
}
