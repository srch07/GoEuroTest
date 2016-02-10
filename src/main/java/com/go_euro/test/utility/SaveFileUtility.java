package com.go_euro.test.utility;

import com.go_euro.test.configuration.Constants;
import com.go_euro.test.configuration.DisplayMessages;
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
    public static boolean save(final String filePath, final String data, final boolean isAppend){
        boolean response = false;
        try{
            File file = new File(filePath);
            if (!file.exists()) 
                file.createNewFile();
            
            if(isAppend){
                Files.write(Paths.get(filePath), data.getBytes(Constants.FILE_FORMAT),
                        StandardOpenOption.APPEND);
            }else{
            	Files.write(Paths.get(filePath), data.getBytes(Constants.FILE_FORMAT),
                        StandardOpenOption.CREATE);
            }
            response = true;
        }catch (IOException e) {
            log.error(DisplayMessages.FAILED_TO_SAVE_DATA_IN_FILE, filePath, e);
        }
        return response;
    }
}
