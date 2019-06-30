/*
    Posts a JSON file to a Google Firebase,
    utilising the Google GSON library and the firebase4j library. (Wrapper for firebase4j post requests)
    References: https://github.com/google/gson (GSON), https://github.com/bane73/firebase4j (firebase4j).
    **WARNING**: Authentication via OAuth 2.0 currently malfunctional until fixed issue within firebase4j!!

    Author: Omar Tanner, 2019 -- open source.
*/

import lib.FirebaseConnection;
import lib.JsonToMap;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

// In lib.FirebaseConnection the Firebase is established, then from such Firebase object one may perform POST requests with it
public class FirebasePostJson {
    /* Post to the firebase the given JSON file at the File object, at the given path in the database (relative to the base-url the lib.FirebaseConnection was initialised with).
       Return a FirebaseResponse object (part of firebase4j) detailing the result. */
    public static FirebaseResponse post(FirebaseConnection con, File jsonFile, String path) throws RuntimeException {
        if (!con.isEstablished()) throw new IllegalStateException("firebase4j has no connection to the Google Firebase!");
        Map<String, Object> map;
        // Attempt to obtain map of JSON from JsonToMap
        try {
            // Utilises lib.JsonToMap parser to obtain a Map<String, Object> Object for the input JSON File object
            map = JsonToMap.parse(jsonFile);
        }
        catch (IOException e) { // Failed to convert to map.
            throw new RuntimeException("ERROR: Failure to convert JSON to map. ADDITIONAL INFO: "+ e.toString());
        }
        // Attempt to post JSON map to Firebase
        try {
            // Obtain Firebase connection from lib.FirebaseConnection object, and attempt to post via firebase4j, passing the path to post at and the map representation of the JSON. Return the obtained FirebaseResponse object.
            return con.get().post(path, map);
        }
        catch (JacksonUtilityException | FirebaseException | UnsupportedEncodingException e) {
            throw new RuntimeException("ERROR: Failure to post JSON map to Firebase. ADDITIONAL INFO: "+ e.toString());
        }
    }

    // Posts a JSON at the root of the firebase4j connection (at the base-url)
    public static FirebaseResponse post(FirebaseConnection con, File jsonFile) throws RuntimeException {
        return post(con, jsonFile, null);
    }
}