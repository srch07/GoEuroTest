package com.go_euro.test.utility;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */

import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by Abhishek Anand on 1/28/2016.
 */
public class CacheUtility {
    private static final int cachingDurationInHours = 18;
    private static final long cacheTotalElementSize = 900000L;
    private static com.google.common.cache.Cache<Object, Object> internalCache = null;
    private static CacheUtility cache = null;
    private CacheUtility(){
    }

    public static CacheUtility getCache(){
        if(cache == null){
            synchronized (CacheUtility.class){
                if(cache == null){
                    cache = new CacheUtility();
                    cache.initializeCache();
                }
            }
        }
        return cache;
    }
    private void initializeCache(){
        internalCache = CacheBuilder.newBuilder().maximumSize(cacheTotalElementSize)
                .expireAfterWrite(cachingDurationInHours, TimeUnit.HOURS).recordStats().build();
    }

    public Object getIfPresent(Object key){
        return internalCache.getIfPresent(key);
    }

    public void put(Object key, Object value){
        if(!(key == null || value == null)){
            internalCache.put(key, value);
        }
    }
}

