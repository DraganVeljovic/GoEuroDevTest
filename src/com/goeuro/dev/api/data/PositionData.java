package com.goeuro.dev.api.data;

import com.google.gson.JsonObject;

public class PositionData implements Data {
	private static final String ID = "_id";
	private static final String NAME = "name";
	private static final String GEO_POSITION = "geo_position";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	
	public void setValues(JsonObject obj) {
		id = obj.get(ID).getAsInt();
		name = obj.get(NAME).getAsString();
		
		JsonObject geoPosition = obj.get(GEO_POSITION).getAsJsonObject();
		latitude = geoPosition.get(LATITUDE).getAsDouble();
		longitude = geoPosition.get(LONGITUDE).getAsDouble();
	}
	
	public String[] getValues() {
		String[] values = new String[4];
		
		values[0] = Integer.toString(id);
		values[1] = name;
		values[2] = Double.toString(latitude);
		values[3] = Double.toString(longitude);
		
		return values;
	}
	
	public String[] getTypes() {
		String[] types = new String[4];
		
		types[0] = ID;
		types[1] = NAME;
		types[2] = LATITUDE;
		types[3] = LONGITUDE;
		
		return types;
	}
}
