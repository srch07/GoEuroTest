package com.go_euro.test.configuration;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class Constants {
    public static final String  CSV_FILE_PATH = "D:/GoEuroTest.CSV";
    public static final String  API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
    public static final String  JSON_ATTRIBUTE_ID = "_id";
    public static final String  JSON_ATTRIBUTE_NAME = "name";
    public static final String  JSON_ATTRIBUTE_TYPE = "type";
    public static final String  JSON_ATTRIBUTE_GEOPOSITION= "geo_position";
    public static final String  JSON_ATTRIBUTE_LATITUDE = "latitude";
    public static final String  JSON_ATTRIBUTE_LONGITUDE = "longitude";
    public static final boolean APPEND_ALLOWED_TO_SAVE_CSV_DATA = false;
    public static final String  FILE_FORMAT = "UTF8";
    private Constants(){}
}
