package com.example.cfapi.Utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class InternetUtils {

    final String BASE_API = "https://codeforces.com/api/";

    final static String CONTEST_LIST_API = "https://codeforces.com/api/contest.list";
    final static String gym = "gym";

    public static URL BuildURL() {
        Uri uri = Uri.parse(CONTEST_LIST_API).buildUpon().appendQueryParameter(gym, String.valueOf(false)).build();

        URL url = null;

        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String GetJson(URL url) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            InputStream in = httpURLConnection.getInputStream();
            Scanner sc = new Scanner(in);

            sc.useDelimiter("\\A");

            try {
                if(sc.hasNext()) {
                    return sc.next();
                } else return null;
            } finally {
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
