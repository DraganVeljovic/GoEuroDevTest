package com.goeuro.dev.api.data;

import com.google.gson.JsonObject;

public class PositionData implements Data {
	private static final String ID = "_id";
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String GEO_POSITION = "geo_position";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	
	private int id;
	private String name;
	private String type;
	private double latitude;
	private double longitude;
	
	public void setValues(JsonObject obj) {
		id = obj.get(ID).getAsInt();
		name = obj.get(NAME).getAsString();
		type = obj.get(TYPE).getAsString();
		
		JsonObject geoPosition = obj.get(GEO_POSITION).getAsJsonObject();
		latitude = geoPosition.get(LATITUDE).getAsDouble();
		longitude = geoPosition.get(LONGITUDE).getAsDouble();
	}
	
	public String[] getValues() {
		String[] values = new String[5];
		
		values[0] = Integer.toString(id);
		values[1] = name;
		values[2] = type;
		values[3] = Double.toString(latitude);
		values[4] = Double.toString(longitude);
		
		return values;
	}
	
	public String[] getMetada() {
		String[] metadata = new String[5];
		
		metadata[0] = ID;
		metadata[1] = NAME;
		metadata[2] = TYPE;
		metadata[3] = LATITUDE;
		metadata[4] = LONGITUDE;
		
		return metadata;
	}
}
