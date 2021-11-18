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

public class CsvConverter {

    public static Map<String, Integer> toMap(String input) {
        String[] args = {"lowercase-count", "special-signs-count", "numbers-count", "uppercase-count", "length"};
        Map<String, Integer> output = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            int index = input.indexOf(";");
            System.out.println();
            int indexOfSeparator = input.indexOf("<") + 3;
            int result = Integer.parseInt(input.substring(index + 1, indexOfSeparator));
            input = input.substring(index + 2 + Integer.toString(result).length());
            output.put(args[i], result);
        }
        return output;
    }

    public static String toJson(String input){
        Map<String, Integer> map = CsvConverter.toMap(input);
        return map.toString();
    }

    public static String toText(String input){
        Map<String, Integer> map = CsvConverter.toMap(input);
        StringBuilder output = new StringBuilder();
        for (var entry : map.entrySet()) {
            output.append(entry.getKey() + " = " + entry.getValue() + "<br>");
        }
        return output.toString();
    }

    public static String toXML(String input){
        Map<String, Integer> map = CsvConverter.toMap(input);
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("map", java.util.Map.class);
        return xStream.toXML(map);
    }

}
