package lib;/*
    A lightweight, simple, JSON parser utilising Google's GSON library.
    Converts a JSON file into a Map<String, Object>: a recursive structure.
    Reference: https://github.com/google/gson

    Author: Omar Tanner, 2019 -- open source.
*/

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

public class JsonToMap {
    // Takes the File Object to the JSON file, and then converts and deserializes into a Map<String, Object> object.
    public static Map<String, Object> parse(File jsonFile) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile))); // Construct a BuffedReader for the input JSON File object
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType(); // Store in a Google object the type of the output of the upcoming fromJson.
        return new Gson().fromJson(r, mapType); // Construct Map<String, Object> from the input json via fromJson and the above type, from a Gson instance.
    }
}