package com.example.zad4api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

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

}
