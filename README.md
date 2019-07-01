# # firebase-post-json

A tool to *quickly POST a JSON file to a Google Firebase*: utilising **firebase4j** and **Google Firebase**.

## Dependencies

This project was developed via Maven, and used the following dependencies as libraries:

 - **Google Firebase** - com.google.firebase, firebase-admin
 - **firebase4j** - com.github.bane73, firebase4j
 - **Google Gson** - com.google.code.gson, gson
 
One must install such dependencies within their project to allow the implementation of this tool.

## Usage

One may use this tool by adding the above dependencies to their project, and storing **FirebasePostJson.java**, as well as the **files within lib**, somewhere within their project. Then they may proceed to interact with **FirebasePostJson.java** in an *examplar fashion* shown within **Example.java**.

## Main Files
 - **FirebasePostJson.java** - The central file combining the auxiliary files, posts a JSON file to a Google Firebase (essentially a wrapper for POST requests within firebase4j, however with additional JSON processing).
 - **Example.java** - An examplar use of FirebasePostJson.
 
## Auxiliary Files
 - **lib/FirebaseConnection.java** - Small wrapper for the firebase4j library, simply for establishing a connection.
 - **lib/Auth.java** - General authentication class for Google Firebase.
 - **lib/JsonToMap.java** - A lightweight, simple, JSON parser utilising Google's GSON library. Converts a JSON file into a Map of (String, Object) pairs.
 
 ## Warning

At present, firebase4j has issues with OAuth 2.0 authentication, however when fixed this tool shall work with authentication. Therefore, one is urged only to use this tool in projects where security isn't a concern.
