package com.go_euro.test.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class URLConnectionUtility {
    final static Logger log = LoggerFactory.getLogger(URLConnectionUtility.class);

    public static String readUrl(String urlString) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } catch (Exception e){
            log.error("Failed to get data from remote url {}", urlString, e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
            }catch(IOException e){
                log.error("Failed to close reader for remote url {}", urlString, e);
            }
        }
        return null;
    }
}
