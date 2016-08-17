package com.mega.robert.chromecastmoneymaker.util;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mega.robert.chromecastmoneymaker.CastActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by RTingirica on 17.08.2016.
 */

public class Requests {
    private String connect_url;
    private String password;
    private String username;

    public Requests(String connect_url, String username, String password){
        this.connect_url = connect_url;
        this.username = username;
        this.password = password;
    }

    public String executeRequest(String endpoint, String method) {
        StringBuilder response  = new StringBuilder();

        try {
            URL url = new URL(connect_url + endpoint);
            JSONObject cred = new JSONObject();
            //TODO: change to https
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);
            cred.put("user", username);
            cred.put("pass", password);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setRequestProperty("X-CastMon-Auth", cred.toString());

            int status = conn.getResponseCode();
            System.out.println("Status:" + status);

            String line;
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            conn.connect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return response.toString();
    }

    private String getQuery(Map<String, String> map)  throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : map.entrySet()) {

            if (first)
                first = false;
            else
                result.append("&");

            String key = entry.getKey();
            String value = entry.getValue();

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value, "UTF-8"));
        }
        //print here the result
        return result.toString();
    }
}
