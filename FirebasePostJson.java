/*
    Posts a JSON file to a Google Firebase,
    utilising the Google GSON library and the firebase4j library. (Wrapper for firebase4j post requests)
    References: https://github.com/google/gson (GSON), https://github.com/bane73/firebase4j (firebase4j).
    **WARNING**: Authentication via OAuth 2.0 currently malfunctional until fixed issue within firebase4j!!

    Author: Omar Tanner, 2019 -- open source.
*/

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

// In FirebaseConnection the Firebase is established, then from such Firebase object one may perform POST requests with it
public class FirebasePostJson extends FirebaseConnection {
    // Using parent constructors

    public FirebasePostJson(String baseUrl) throws FirebaseException {
        super(baseUrl);
    }

    // **WARNING**: Authentication via OAuth 2.0 currently malfunctional until fixed issue within firebase4j!!
    public FirebasePostJson(String baseUrl, URL tokenUrl) throws FirebaseException, IOException {
        super(baseUrl, tokenUrl);
    }

    public FirebasePostJson() {
        super();
    }

    /* Post to the firebase the given JSON file at the URL object, at the given path in the database (relative to the base-url the FirebaseConnection was initialised with).
       Return a FirebaseResponse object (part of firebase4j) detailing the result. */
    public FirebaseResponse post(URL jsonUrl, String path) throws IOException, JacksonUtilityException, FirebaseException {
        if (!isEstablished()) throw new IllegalStateException("firebase4j has no connection to the Google Firebase!");
        // Utilises JsonToMap parser to obtain a Map<String, Object> Object for the input JSON.
        JsonToMap p = new JsonToMap(jsonUrl);
        Map<String, Object> map = p.getMap();
        // Obtain Firebase connection from superclass, and attempt to post via firebase4j, passing the path to post at and the map representation of the JSON. Return the obtained FirebaseResponse object.
        return get().post(path, map);
    }

    // Posts a JSON at the root of the firebase4j connection (at the base-url)
    public FirebaseResponse post(URL jsonUrl) throws IOException, JacksonUtilityException, FirebaseException {
        return post(jsonUrl, null);
    }
}