package com.go_euro.test.service;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class ServiceLocator {

    private static APIService apiService;

    public static APIService getApiService(){
        APIService apiServiceLocal = apiService;
        if(apiServiceLocal != null)
            return apiServiceLocal;

        synchronized(APIService.class) {
            if(apiService == null)
                apiService = new APIService();
        }

        return apiService;
    }
}
