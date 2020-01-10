package com.example.gifloaderapplication.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by luo on 20/01/10.
 */
public class HttpLoader {
    public static InputStream getInputStreanFormUrl(String param) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(param).openConnection();
        return conn.getInputStream();
    }

}
