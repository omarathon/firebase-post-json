/*
    An example use of FirebasePostJson.
*/

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import java.io.*;
import java.net.URL;

public class Example {
    public static void main(String[] args) {
        // For example, in Maven storing a JSON called "example_data.json" in resources, and seek to upload.
        URL jsonUrl = Example.class.getResource("example_data.json");
        // Currently malfunctional, however to authenticate via OAuth 2.0, pass it a URL object of the OAuth 2.0 authentication json file. E.g storing it as "auth.json" in resources:
        URL authUrl = Example.class.getResource("auth.json");

        // Obtain the poster by constructing a FirebasePostJson object, with the root URL of the firebase, e.g "https://my-project.firebaseio.com" (the https/http here is important!!)
        FirebasePostJson poster;
        try {
            poster = new FirebasePostJson("https://my-project.firebaseio.com");
            // with authentication, this would be new FirebasePostJson("https://my-project.firebaseio.com", authUrl)
            // (the authentication URL is the second parameter)
        } catch (FirebaseException e) {
            // Errors from construction of poster imply failure to connect.
            System.err.println("Failure to connect: " + e.toString());
            return;
        }
        
        // Now attempt to post the example json file into the directory "~/node1/node2/" (where ~ indicates base directory used to construct the poster above)
        try {
            // First parameter is the URL object to the json to post, second is directory
            poster.post(jsonUrl, "node1/node2");
        } catch (IOException | JacksonUtilityException | FirebaseException e) {
            // Errors from post imply failure to post.
            System.err.println("Failure to post: " + e.toString());
            return;
        }
    }
}
