package com.example.zad4api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.util.HashMap;
import java.util.Map;

public class XmlConverter {

    private static Map<String, Double> toMap(String input) {
        String[] args = {"lowercase-count", "special-signs-count", "numbers-count", "uppercase-count", "length"};
        JSONObject json = XML.toJSONObject(input); //xml -> json
        JSONArray mapa = json.getJSONObject("map").getJSONArray("entry");
        Map<String, Double> output = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new Gson()
                    .fromJson(mapa.getJSONObject(i).toString(), new TypeToken<HashMap<String, Object>>() {
                    }.getType());
            String operation = map.get("string").toString();
            int index = operation.substring(1, operation.length() - 1).indexOf(",");
            double result = Double.parseDouble(operation.substring(index + 3, operation.length() - 1));
            output.put(args[i], result);
        }
        return output;
    }

    public static String toJson(String input){
        Map<String, Double> map = XmlConverter.toMap(input);
        return map.toString();
    }

    public static String toText(String input){
        Map<String, Double> map = XmlConverter.toMap(input);
        StringBuilder output = new StringBuilder();
        for (var entry : map.entrySet()) {
            output.append(entry.getKey() + " = " + entry.getValue() + "<br>");
        }
        System.out.println(output.toString());
        return output.toString();
    }

    public static String toCSV(String input){
        Map<String, Double> map = XmlConverter.toMap(input);
        StringBuilder output = new StringBuilder();
        for (var entry : map.entrySet()) {
            output.append(entry.getKey())
                    .append(';')
                    .append(entry.getValue())
                    .append(System.getProperty("line.separator"))
                    .append("<br>");
        }
        return output.toString();
    }

}
