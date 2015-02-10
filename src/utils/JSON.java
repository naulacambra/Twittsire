package utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//Classe per poder generar respostes en format JSON
public class JSON {
	private Gson gson;
	private JsonArray array;

	public JSON() {
		gson = new Gson();
		array = new JsonArray();
	}

	public void addPair(String property, String value) {
		JsonObject obj = new JsonObject();
		obj.addProperty(property, value);
		array.add(obj);
	}

	public void addPair(String property, Boolean value) {
		JsonObject obj = new JsonObject();
		obj.addProperty(property, value);
		array.add(obj);
	}

	public int getArraySize() {
		return array.size();
	}

	public String toString() {
		return gson.toJson(array);
	}
}
