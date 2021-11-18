package com.example.zad4api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.*;

@RestController
public class MyRestController {

    static private final String[] possibleTypes = {"text", "json", "xml", "csv"};

    static private final String URL = "http://localhost:8090/";

    private static String getFromURL(String endpointValue, String text){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL + endpointValue + "?text=" + text, String.class);
    }

    @RequestMapping(value = "/anyresult", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE}) //example: http://localhost:8100/anyresult?text=Karol&type=json
    public String jsonresult(@RequestParam(value = "text", defaultValue = "Karol") String text, @RequestParam(value = "type", defaultValue = "text") String type) {
        return Arrays.asList(possibleTypes).contains(type.toLowerCase(Locale.ROOT)) ? getFromURL(type + "result", text) : "Sorry! Wrong data type. Try again...";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE}) //example: http://localhost:8100/convert?from=json&to=xml
    public String saveData(@RequestBody String body, @RequestParam(value = "from", defaultValue = "json") String from, @RequestParam(value = "to", defaultValue = "text") String to) {
        if(from.equals("text")){
            switch (to) {
                case "json":
                    return TextConverter.toJson(body);
                case "xml":
                    return TextConverter.toXML(body);
                case "csv":
                    return TextConverter.toCSV(body);
                case "text":
                    return body;
                default:
                    return "Error!";
            }
        }
        else if(from.equals("json")){
            switch (to) {
                case "xml":
                    return JsonConverter.toXML(body);
                case "csv":
                    return JsonConverter.toCSV(body);
                case "text":
                    return JsonConverter.toText(body);
                case "json":
                    return body;
                default:
                    return "Error!";
            }
        }
        else if(from.equals("xml")){
            switch (to) {
                case "json":
                    return XmlConverter.toJson(body);
                case "csv":
                    return XmlConverter.toCSV(body);
                case "text":
                    return XmlConverter.toText(body);
                case "xml":
                    return body;
                default:
                    return "Error!";
            }
        }
        else if(from.equals("csv")){
            switch (to) {
                case "json":
                    return CsvConverter.toJson(body);
                case "xml":
                    return CsvConverter.toXML(body);
                case "text":
                    return CsvConverter.toText(body);
                case "csv":
                    return body;
                default:
                    return "Error!";
            }
        }
        return body;
    }
}

