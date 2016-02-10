package com.go_euro.test.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class InputValidationUtility {
    public static boolean isValidate(final String [] arguments){
        //boolean response = false;
    	
        if(arguments == null || arguments.length == 0 || arguments.length > 1)
            return false;
        
        String expression = "^[a-zA-Z\b]*$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(arguments[0]);
        
        if(matcher.matches())
        	return true;
        
        return false;
    }   
}
