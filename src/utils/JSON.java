package utils;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//Classe per poder generar respostes en format JSON
public class JSON {
	private Gson gson;
	private JsonArray array;
	private HashMap<String, String> map;

	public JSON() {
		gson = new Gson();
		array = new JsonArray();
		map = new HashMap<String, String>();
	}

	public void addPair(String property, String value) {
		JsonObject obj = new JsonObject();
		obj.addProperty(property, value);
		array.add(obj);
		map.put(property, value);
	}

	public void addPair(String property, Boolean value) {
		JsonObject obj = new JsonObject();
		obj.addProperty(property, value);
		array.add(obj);
		map.put(property, String.valueOf(value));
	}

	public int getArraySize() {
		return array.size();
	}
	
	public String getValue(String key){
		return map.get(key);
	}

	public String toString() {
		return gson.toJson(array);
	}
}
