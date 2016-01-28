package com.go_euro.test.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class InputValidationUtility {
    public static boolean isValidate(String [] arguments){
        boolean response = false;
        if(arguments.length > 1)
            response = false;
        else if(arguments.length == 1){
            String expression = "^[a-zA-Z\b]*$";
            //CharSequence inputStr = str;
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(arguments[0]);
            if(matcher.matches())
            {
                response = true;
            }
            else
            {
                response = false;
            }
        }
        return response;
    }
}
