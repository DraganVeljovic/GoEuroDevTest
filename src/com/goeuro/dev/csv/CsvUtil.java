package com.goeuro.dev.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.goeuro.dev.api.data.Data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVWriter;

public class CsvUtil {	
	public CSVWriter getWriter(String filename) throws RuntimeException {
		try {
			File file = new File(filename);
			if (!file.exists() && file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
			
			return new CSVWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to create CSVWriter.");
		}
	}
	
	public void writeToCsv(JsonArray array, Class<?> cls, String filename) {
		try {
			CSVWriter writer = getWriter(filename);
			
			Data data = (Data) cls.newInstance();
			
			writer.writeNext(data.getMetada());
			for (int i = 0; i < array.size(); i++) {
				JsonObject obj = array.get(i).getAsJsonObject();
				data.setValues(obj);
				writer.writeNext(data.getValues());
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
