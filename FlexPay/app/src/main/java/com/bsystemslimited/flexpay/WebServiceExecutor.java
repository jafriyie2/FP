package com.bsystemslimited.flexpay;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Hecatonchries on 10/22/2015.
 */
public class WebServiceExecutor {

    private boolean isOnline() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public JSONObject HttpsGetData(String url) throws Exception {

        return null;
    }

    public JSONObject HttpGetData(String url) throws Exception {

        URL json = new URL(url);
        URLConnection connection = json.openConnection();
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(20000);
        if (isOnline()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String line = reader.readLine().toString();
            JSONObject jsonResponse = new JSONObject(line);
            reader.close();
            return jsonResponse;
        }
        return null;

    }

}
