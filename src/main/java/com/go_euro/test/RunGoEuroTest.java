package com.go_euro.test;

import com.go_euro.test.service.APIService;
import com.go_euro.test.service.ServiceLocator;
import com.go_euro.test.configuration.Constants;
import com.go_euro.test.utility.InputValidationUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class RunGoEuroTest {
    private final static Logger log = LoggerFactory.getLogger(RunGoEuroTest.class);
    private static APIService apiService = ServiceLocator.getApiService();

    public static void main(String [] args){
    	
    	if(!InputValidationUtility.isValidate(args)){
    		log.error("Invalid arguments provided");
    		return;
    	}
    	
    	if(apiService.invoke(args[0])){
    		log.info("CSV File has been generated Successfully at {}", Constants.CSV_FILE_PATH);
    		return;
    	}
    	
        log.warn("Something went wrong with the CSV file generation. Please check above logs.");
    }
}
