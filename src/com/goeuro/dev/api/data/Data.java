package com.goeuro.dev.api.data;

import com.google.gson.JsonObject;

public interface Data {
	public void setValues(JsonObject obj);
	public String[] getValues();
	public String[] getTypes();
}
