package com.example.zad4api;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.HashMap;
import java.util.Map;

public class TextConverter {

    public static Map<String, Integer> toMap(String input) {
        String[] args = {"lowercase-count", "special-signs-count", "numbers-count", "uppercase-count", "length"};
        Map<String, Integer> output = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            int index = input.indexOf("=") + 1;
            int indexOfSeparator = input.indexOf(System.getProperty("line.separator"));
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

    public static String toXML(String input){
        Map<String, Integer> map = CsvConverter.toMap(input);
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("map", java.util.Map.class);
        return xStream.toXML(map);
    }

    public static String toCSV(String input){
        Map<String, Integer> map = CsvConverter.toMap(input);
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
