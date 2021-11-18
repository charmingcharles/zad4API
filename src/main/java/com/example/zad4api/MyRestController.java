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
    public String saveData(@RequestBody String text) {
        //json -> all
        //xml -> all - zaraz


        //xml -> json
        JSONObject json = XML.toJSONObject(text); //xml -> json
        JSONArray mapa = json.getJSONObject("map").getJSONArray("entry");
        for(int i = 0; i < 5; i++){
            Map<String, Object> map = new Gson()
                    .fromJson(mapa.getJSONObject(i).toString(), new TypeToken<HashMap<String, Object>>() {
                    }.getType());
            String operation = map.get("string").toString();
            int index = operation.substring(1, operation.length() - 1).indexOf(",");
            double result = Double.parseDouble(operation.substring(index + 3, operation.length() - 1));
            System.out.println(result);
        }
        return text;
    }
}

