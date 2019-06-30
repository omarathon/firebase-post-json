/*
    An example use of FirebasePostJson.
*/

import lib.FirebaseConnection;
import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import java.io.*;

public class Example {
    public static void main(String[] args) {
        // For example, storing a JSON called "example_data.json" in C:\data, and seek to upload.
        File jsonFile = new File("C:/data/example_data.json");
        // Currently malfunctional, however to authenticate via OAuth 2.0, pass it a URL object of the OAuth 2.0 authentication json file. E.g storing it as "auth.json" in C:/data
        File authFile = new File("C:/data/auth.json");

        // Obtain firebase4j connection by constructing a FirebaseConnection object, with the root URL of the firebase, e.g "my-project.firebaseio.com"
        FirebaseConnection con;
        try {
            con = new FirebaseConnection("https://my-project.firebaseio.com");
            // with authentication, this would be new FirebaseConnection("my-project.firebaseio.com", authFile)
            // (the authentication File object is the second parameter)
        } catch (FirebaseException e) {
            // Errors from construction of poster imply failure to connect.
            System.err.println("Failure to connect: " + e.toString());
            return;
        }
        
        // Now attempt to post the example json file into the directory "~/node1/node2/" (where ~ indicates base directory used to construct the poster above)
        try {
            // First parameter is the URL object to the json to post, second is directory
            FirebasePostJson.post(jsonFile, "node1/node2");
        } catch (IOException | JacksonUtilityException | FirebaseException e) {
            // Errors from post imply failure to post.
            System.err.println("Failure to post: " + e.toString());
            return;
        }
    }
}
