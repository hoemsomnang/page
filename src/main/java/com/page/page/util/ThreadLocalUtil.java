package com.page.page.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {

    public final static String ERROR_MESSAGE = "ERROR_MESSAGE";
    public final static String ERROR_NAME = "ERROR_NAME";
    private static  ThreadLocal<Map<String,Object> >  threadLocal =ThreadLocal.withInitial(() -> new HashMap<>());

    public static void setErrorName( String errorName ) {
        threadLocal.get().put( ERROR_NAME ,errorName);
    }

    public static String getErrorName( ) {
        return  (String)threadLocal.get().get( ERROR_NAME );
    }
    public static void setErrorMessage( String errorMessage ) {
        String errorName = getErrorName();
        if (StringUtils.isNotBlank( errorName )
                || StringUtils.isNoneEmpty( errorName ) ) {
            threadLocal.get().put(ERROR_MESSAGE, errorMessage);
        }
    }
    public static String getErrorMessage( ) {
       return  (String)threadLocal.get().get( ERROR_MESSAGE );
    }

    public static void removeThreadLocalData( String name ) {
        threadLocal.get().remove(name);
    }
}
