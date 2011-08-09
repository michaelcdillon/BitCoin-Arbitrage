package org.bca.util;

import java.util.logging.Logger;

import java.lang.StringBuilder;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class HttpsRequest {

    private static final String GET     = "GET";
    private static final String POST    = "POST";
    
    private static Logger log = Logger.getLogger (HttpsRequest.class.getName ());

    public static String get (String url) {
        return request (GET, url);
    }

    public static String post (String url) {
        return request (POST, url);
    }

    private static String request (String method, String path) {
        int           respCode = 0;
        StringBuilder data     = new StringBuilder ();

        try { 
            URL url = new URL (path); 
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection ();
            connection.setDoInput (true); 
            connection.setDoOutput (false);

            connection.setRequestMethod (method); 
            connection.setFollowRedirects (true); 
            
            respCode = connection.getResponseCode (); 
            log.fine ("Resp Code:" + respCode); 
            
            if (respCode != 200)
                return null;
             
            DataInputStream input = new DataInputStream (connection.getInputStream ()); 
            for (int c = input.read (); c != -1; c = input.read ()) 
                data.append ((char) c);
             
            input.close (); 

            return data.toString ();
        } 
        catch (Exception e)  { 
            log.warning ("An issue occured while fetching the url: " + path); 
            log.warning (e.toString ()); 
            return null;
        } 
    }
}
