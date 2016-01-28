package com.go_euro.test.service;

import com.go_euro.test.utility.CacheUtility;
import com.go_euro.test.utility.Constants;
import com.go_euro.test.utility.SaveFileUtility;
import com.go_euro.test.utility.URLConnectionUtility;
import com.go_euro.test.vo.OutputVO;
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
    private final static CacheUtility cache = CacheUtility.getCache();
    private OutputVO extractRequiredFields(Map jsonElement){
        OutputVO outputVO = null;
        String id = String.valueOf(jsonElement.get(Constants.JSON_ATTRIBUTE_ID));
        //Little hack to remove anything after decimal if id is returned as float by gson
        if(id != null && !id.isEmpty())
            if(id.indexOf(".") != -1)
                id = id.substring(0, id.indexOf("."));
        String name = String.valueOf(jsonElement.get(Constants.JSON_ATTRIBUTE_NAME));
        String type = String.valueOf(jsonElement.get(Constants.JSON_ATTRIBUTE_TYPE));
        String latitude = null;
        String longitude = null;
        Map geo_position = (Map)jsonElement.get(Constants.JSON_ATTRIBUTE_GEOPOSITION);
        if(geo_position!= null) {
            latitude = String.valueOf(geo_position.get(Constants.JSON_ATTRIBUTE_LATITUDE));
            longitude = String.valueOf(geo_position.get(Constants.JSON_ATTRIBUTE_LONGITUDE));
        }
        if(id!=null && name!=null && type!=null && latitude!=null && longitude!=null
                && !id.isEmpty() && !name.isEmpty() && !type.isEmpty() && !latitude.isEmpty() && !longitude.isEmpty()){
            outputVO = new OutputVO(id, name, type, latitude, longitude);
        }
        return outputVO;
    }
    private boolean processService(String cityName){
        boolean response = false;
        List<OutputVO> outputVOList;
        OutputVO outputVO;
        if((outputVOList = (List<OutputVO>)cache.getIfPresent(cityName)) == null){
            outputVOList = new ArrayList<OutputVO>();
            String urlResponse = URLConnectionUtility.readUrl(API_URL + cityName);
            if (urlResponse != null) {
                List<Map> jsonResponse = new Gson().fromJson(urlResponse, List.class);
                if (!(jsonResponse == null || jsonResponse.isEmpty())) {
                    for (Map jsonElement : jsonResponse) {
                        outputVO = extractRequiredFields(jsonElement);
                        if (outputVO != null)
                            outputVOList.add(outputVO);
                    }
                } else {
                    log.warn("Search Query for City {} returned no response", cityName);
                }
            }
            if(outputVOList != null && !outputVOList.isEmpty())
                cache.put(cityName, outputVOList);
        }
        StringBuffer stringBuffer = new StringBuffer();
        if(outputVOList != null && !outputVOList.isEmpty()){
            for(OutputVO outputVOLocal : outputVOList){
                stringBuffer.append(outputVOLocal.getCSVFormat());
            }
            response = SaveFileUtility.saveFile(CSV_FILE_PATH, stringBuffer.toString(), false);
        }
        return response;
    }
    public boolean execute(String cityName){
        boolean response = false;
        response = processService(cityName);
        return response;
    }
}
