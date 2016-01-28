package com.go_euro.test.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class SaveFileUtility {
    private final static Logger log = LoggerFactory.getLogger(SaveFileUtility.class);
    public static boolean saveFile(String filePath, String data, boolean isAppend){
        boolean response = false;
        try{
            File file = new File(filePath);
            if(!isAppend) {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
            }else{
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
            if(isAppend)
                Files.write(Paths.get(filePath), data.getBytes("UTF8"), StandardOpenOption.APPEND);
            else
                Files.write(Paths.get(filePath), data.getBytes("UTF8"), StandardOpenOption.CREATE);
            response = true;
        }catch (IOException e) {
            log.error("Failed to save file in path {}", filePath, e);
        }
        return response;
    }
}
