package com.my.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class InitConfigure {

private static HashMap<String, String> property;
	
	public InitConfigure() {
		property = new HashMap<String, String>();
	}
	
	static {
		try{
			reloadAll();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
    }
	
	private static void reloadAll() {
    	if(property == null)	property = new HashMap<String, String>();
    	strageMsg();
    }

    private static void strageMsg() {
    	HashMap<String, String> map = new HashMap<String, String>();
    	Properties p = new Properties();
        try {
            p.load((InitConfigure.class).getClassLoader().getResourceAsStream("key.properties"));
            
            String key;
            String value;
            for(Enumeration<?> enu = p.propertyNames(); enu.hasMoreElements(); map.put(key, value))
            {
                key = (String)enu.nextElement();
                value = p.getProperty(key);
            }
        } catch(FileNotFoundException e) {
        	throw new RuntimeException(e.toString());
        } catch(IOException io){
        	throw new RuntimeException(io.toString());
        }
    	property = map;
    }

    public static String getProperty(String key) {
        String str = "";
        if(property != null) {
            if(property.containsKey(key)){
            	str = property.get(key);
            }
        }
        return str;
    }
}
