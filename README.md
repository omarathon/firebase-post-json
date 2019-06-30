# # firebase-post-json

A tool to **quickly POST a JSON file to a Google Firebase**: utilising **firebase4j** and **Google Firebase**.

## Dependencies

This project was developed via Maven, as a result one may need to install the relevant dependencies:

 - **Google Firebase** - com.google.firebase, firebase-admin, development version: 6.8.1
 - **firebase4j** - com.github.bane73, firebase4j, development version: Tmaster-b6f90e9764-1
 - **Google Gson** - com.google.code.gson, gson, development version: 2.8.5

## Main Files
 - **FirebasePostJson.java** - The central file combining the auxiliary files, posts a JSON file to a Google Firebase (essentially a wrapper for POST requests within firebase4j, however with additional JSON processing).
 - **Example.java** - An example use of FirebasePostJson.
 
## Auxiliary Files
 - **lib/FirebaseConnection.java** - Small wrapper for the firebase4j library, simply for establishing a connection.
 - **lib/Auth.java** - General authentication class for Google Firebase.
 - **lib/JsonToMap.java** - A lightweight, simple, JSON parser utilising Google's GSON library. Converts a JSON file into a Map of (String, Object) pairs.
 
 ## Warning
 
At present, firebase4j has issues with OAuth 2.0 authentication, however when fixed this tool shall work with authentication. Therefore, one is urged only to use this tool for projects where security isn't a concern.
