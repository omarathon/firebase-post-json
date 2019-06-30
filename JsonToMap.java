/*
    A lightweight, simple, JSON parser utilising Google's GSON library.
    Converts a JSON file into a Map<String, Object>: a recursive structure.
    Reference: https://github.com/google/gson

    Author: Omar Tanner, 2019 -- open source.
*/

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

public class JsonToMap {
    private Map<String, Object> map; // The resulting object to which the Java standard library may be easily applied.

    // Takes the URL Object to the JSON file, and then converts and deserializes into a Map<String, Object> object.
    public JsonToMap(URL u) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(u.openStream())); // Construct a BuffedReader for the input URL.
        Type mapType = new TypeToken<Map<String, Object>>(){}.getType(); // Store in a Google object the type of the output of the upcoming fromJson.
        this.map = new Gson().fromJson(r, mapType); // Construct Map<String, Object> from the input json via fromJson and the above type, from a Gson instance.
    }

    // Getter for the obtained map
    public Map<String, Object> getMap() {
        return map;
    }
}