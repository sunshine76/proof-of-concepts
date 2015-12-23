package com.sunshine.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by schinta6 on 12/21/15.
 */
public class PropertyFileLoader {

    private static Map<String, Properties> propertiesMap = new ConcurrentHashMap<String, Properties>();

    public static String getProperty(String fileName, String key) {
        InputStream input = null;
        Properties prop;
        try {
            if (propertiesMap.get(fileName) == null) {
                input = PropertyFileLoader.class.getClassLoader().getResourceAsStream(fileName);
                prop = new Properties();
                prop.load(input);
                propertiesMap.put(fileName, prop);
            }

        } catch (IOException ex) {
            //ignore for now.
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    //ignore for now.
                }
            }
        }
        if (propertiesMap.get(fileName) != null) {
            return propertiesMap.get(fileName).getProperty(key);
        } else {
            return null;
        }

    }

}
