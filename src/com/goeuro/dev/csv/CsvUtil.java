package com.goeuro.dev.csv;

import java.io.FileWriter;
import java.io.IOException;

import com.goeuro.dev.api.data.Data;
import com.goeuro.dev.api.data.PositionData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVWriter;

public class CsvUtil {
	public String[] packToCSVRow(String ...values) {
		String[] csvRow = new String[values.length];
		
		for (int i = 0; i < values.length; i++) {
			csvRow[i] = values[i];
		}
		
		return csvRow;
	}
	
	public CSVWriter getWriter(String filename) throws RuntimeException {
		try {
			return new CSVWriter(new FileWriter(filename));
		} catch (IOException e) {
			throw new RuntimeException("Failed to create csv writer.");
		}
	}
	
	public void writeToCsv(JsonArray array, Data data, String filename) {
		CSVWriter writer = getWriter(filename);
		writer.writeNext(data.getTypes());
		for (int i = 0; i < array.size(); i++) {
			JsonObject obj = array.get(i).getAsJsonObject();
			data.setValues(obj);
			writer.writeNext(data.getValues());
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
