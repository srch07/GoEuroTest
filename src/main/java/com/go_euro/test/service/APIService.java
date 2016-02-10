package com.go_euro.test.service;

import com.go_euro.test.configuration.DisplayMessages;
import com.go_euro.test.utility.CacheUtility;
import com.go_euro.test.configuration.Constants;
import com.go_euro.test.utility.SaveFileUtility;
import com.go_euro.test.utility.URLConnectionUtility;
import com.go_euro.test.vo.ParsedJsonRowVO;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class APIService {
    private final static Logger log = LoggerFactory.getLogger(APIService.class);
    private final static String API_URL = Constants.API_URL;
    private final static String CSV_FILE_PATH = Constants.CSV_FILE_PATH;
    private final static CacheUtility applicationCache = CacheUtility.getCache();
    
    private ParsedJsonRowVO parseJsonToAttributes(final Map jsonRow){
        ParsedJsonRowVO parsedJsonRowVO = null;
        
        String id = String.valueOf(jsonRow.get(Constants.JSON_ATTRIBUTE_ID));
        id = getNumericalValue(id);
        
        String name = String.valueOf(jsonRow.get(Constants.JSON_ATTRIBUTE_NAME));
        String type = String.valueOf(jsonRow.get(Constants.JSON_ATTRIBUTE_TYPE));
        Map geo_position = (Map)jsonRow.get(Constants.JSON_ATTRIBUTE_GEOPOSITION);
        
        if(geo_position== null) 
        	return parsedJsonRowVO;
        
        String latitudePosition = String.valueOf(geo_position.get(Constants.JSON_ATTRIBUTE_LATITUDE));
        String longitudePosition = String.valueOf(geo_position.get(Constants.JSON_ATTRIBUTE_LONGITUDE));
    
        if(isNotValid(id) || isNotValid(name) || isNotValid(type) || isNotValid(latitudePosition) || isNotValid(longitudePosition))
        	return parsedJsonRowVO;
        
        parsedJsonRowVO = new ParsedJsonRowVO(id, name, type, latitudePosition, longitudePosition);
        return parsedJsonRowVO;
    }

    private boolean isNotValid(final String inputString) {
        return "null".equals(inputString) || inputString.isEmpty();
    }

	private String getNumericalValue(final String inputString) {
		
		if(isNotValid(inputString))
			return inputString;
		
		if(inputString.indexOf(".") != -1)
            return inputString.substring(0, inputString.indexOf("."));
		
		return inputString;
	}
    
    public boolean invoke(final String cityName){
        
    	List<ParsedJsonRowVO> extractedDataFromJsonList;
        ParsedJsonRowVO parsedJsonRowVO;
        if((extractedDataFromJsonList = (List<ParsedJsonRowVO>) applicationCache.getIfPresent(cityName)) != null)
        	return formatDataToCsvAndSave(extractedDataFromJsonList);
        	
        extractedDataFromJsonList = new ArrayList<ParsedJsonRowVO>();
        String urlResponse = URLConnectionUtility.readUrl(API_URL + cityName);
        if(urlResponse == null)
        	return false;
        
        List<Map> jsonResponse = new Gson().fromJson(urlResponse, List.class);
        
        if(jsonResponse == null || jsonResponse.isEmpty()){
        	log.warn(DisplayMessages.NO_JSON_RESPONSE_RECIEVED, cityName);
        	return false;
        }
        for (Map jsonRow : jsonResponse) {
            parsedJsonRowVO = parseJsonToAttributes(jsonRow);
            if (parsedJsonRowVO != null)
                extractedDataFromJsonList.add(parsedJsonRowVO);
        }
        
        if(extractedDataFromJsonList != null && !extractedDataFromJsonList.isEmpty())
            applicationCache.put(cityName, extractedDataFromJsonList);
        
        return formatDataToCsvAndSave(extractedDataFromJsonList);
    }

	private boolean formatDataToCsvAndSave(final List<ParsedJsonRowVO> extractedDataFromJsonList) {
		StringBuffer csvFormattedData = new StringBuffer();
		if(extractedDataFromJsonList == null || extractedDataFromJsonList.isEmpty())
			return false;
        
        for(ParsedJsonRowVO parsedJsonRowVO : extractedDataFromJsonList){
            csvFormattedData.append(parsedJsonRowVO.getCSVFormat());
        }
        return SaveFileUtility.save(CSV_FILE_PATH, csvFormattedData.toString(), Constants.APPEND_ALLOWED_TO_SAVE_CSV_DATA);
        
	}

}
