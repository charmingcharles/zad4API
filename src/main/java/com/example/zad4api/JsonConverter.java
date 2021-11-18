package com.example.zad4api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.HashMap;
import java.util.Map;

public class JsonConverter {

    public static String toXML(String input){
        Map<String, Object> map = new Gson()
                .fromJson(input, new TypeToken<HashMap<String, Object>>() {
                }.getType());
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("map", java.util.Map.class);
        return xStream.toXML(map);
    }

    public static String toText(String input){
        Map<String, Object> map = new Gson()
                .fromJson(input, new TypeToken<HashMap<String, Object>>() {
                }.getType());
        StringBuilder output = new StringBuilder();
        for (var entry : map.entrySet()) {
            output.append(entry.getKey() + " = " + entry.getValue() + "<br>");
        }
        return output.toString();
    }

    public static String toCSV(String input){
        Map<String, Object> map = new Gson()
                .fromJson(input, new TypeToken<HashMap<String, Object>>() {
                }.getType());
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
