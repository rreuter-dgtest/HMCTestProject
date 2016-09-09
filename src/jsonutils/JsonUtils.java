package jsonutils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils 
{
	public Map<String, String> convertSimpleJsonToMap(String json) {
		Type mapType = new TypeToken<Map<String, String>>(){}.getType();
		Map<String, String> formattedJson = new Gson().fromJson(json, mapType);
		return formattedJson;
	}
	
	public ArrayList<Object> convertComplexJsonToArrayList(String json) {
		Type mapType = new TypeToken<ArrayList<Object>>(){}.getType();
		ArrayList<Object> formattedJson = new Gson().fromJson(json, mapType);
		return formattedJson;
	}
}