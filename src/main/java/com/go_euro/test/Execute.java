package com.go_euro.test;

import com.go_euro.test.service.APIService;
import com.go_euro.test.service.ServiceLocator;
import com.go_euro.test.utility.Constants;
import com.go_euro.test.utility.InputValidationUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class Execute {
    private final static Logger log = LoggerFactory.getLogger(Execute.class);
    private static APIService apiService = ServiceLocator.getApiService();

    public static void main(String [] args){
        if(InputValidationUtility.isValidate(args)){
            if(apiService.execute(args[0])){
                log.info("CSV File has been generated Successfully at {}", Constants.CSV_FILE_PATH);
            }else{
                log.warn("Something went wrong with the CSV file generation. Please check above logs.");
            }
        }
    }
}
