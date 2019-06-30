/*
    Small wrapper for the firebase4j library, simply for establishing a connection.
    An Object storing a connection to a Google Firebase via the firebase4j library.
    Utilised within FirebasePostJson as the connector to post from.
    Reference: https://github.com/bane73/firebase4j

    **WARNING**: Authentication via OAuth 2.0 currently malfunctional until fixed issue within firebase4j!!

    Author: Omar Tanner, 2019 -- open source.
*/

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.service.Firebase;
import java.io.IOException;
import java.net.URL;

public class FirebaseConnection {
    private Firebase connection; // Main Object storing firebase4j connection to Firebase
    private boolean established = false;
    private String baseUrl = null;
    private URL tokenUrl = null;

    /* Two constructors: 
       one with OAuth 2.0 token, one without. Both require the base url of the database. */

    // Establish connection with no OAuth 2.0 token, only with base url
    public FirebaseConnection (String baseUrl) throws FirebaseException {
        connectWithoutToken(baseUrl);
    }

    // **WARNING**: Currently manfunctional, due to issue within firebase4j (using "auth" instead of "access_token" as parameter for OAuth 2.0 API key)!!
    // Establish connection with base url and OAuth 2.0 token via URL object storing its location
    public FirebaseConnection(String baseUrl, URL tokenUrl) throws FirebaseException, IOException {
        connectWithToken(baseUrl, tokenUrl);
    }

    // Default empty constructor
    public FirebaseConnection() {}

    // Establish connection with no OAuth 2.0 token, only with base url
    public void connectWithoutToken(String baseUrl) throws FirebaseException {
        this.connection = new Firebase(baseUrl);
        this.baseUrl = baseUrl;
        established = true;
    }

    // Establish connection with base url and OAuth 2.0 token via URL object storing its location
    // **WARNING**: Currently manfunctional, due to issue within firebase4j (using "auth" instead of "access_token" as parameter for OAuth 2.0 API key)!!
    public void connectWithToken(String baseUrl, URL tokenUrl) throws FirebaseException, IOException {
        // Authenticate to Firebase via Auth wrapper, and generate access token.
        Auth auth = new Auth(tokenUrl, baseUrl);
        String privateKey = auth.getAccessToken();
        // Finally construct object
        this.connection = new Firebase(baseUrl, privateKey);
        this.baseUrl = baseUrl;
        this.tokenUrl = tokenUrl;
        established = true;
    }

    // Getter for the connection
    public Firebase get() throws IllegalStateException {
        if (!established) throw new IllegalStateException("Connection not established!");
        return this.connection;
    }

    // Getter for the base URL string in which the connection was established with
    public String getBaseUrl() {
        if (!established) throw new IllegalStateException("Connection not established!");
        return this.baseUrl;
    }

    // Getter for the OAuth 2.0 token URL Object in which the connection may have been established with
    public URL getTokenUrl() {
        if (!established) throw new IllegalStateException("Connection not established!");
        if (tokenUrl == null) throw new IllegalStateException("Connection was established without an OAuth 2.0 token!");
        return this.tokenUrl;
    }

    // Getter for established boolean
    public boolean isEstablished() {
        return this.established;
    }
}