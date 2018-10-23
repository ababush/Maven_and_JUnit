package com.endava.utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class UtilProprieties {

            private static final Logger logger = ProjectLogger.getInstance(UtilProprieties.class);

            protected static Properties appProps = null;

    public UtilProprieties(String srcToConfigFile) {
              if (srcToConfigFile.equals("")) {
                    throw new NullPointerException("Path to file can't be empty");
                } else {
            getProprietiesFile(srcToConfigFile);
        }
    }



    private void getProprietiesFile(String configPath) {

        try {
            appProps = new Properties();
            appProps.load(new FileInputStream(configPath));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public HashMap getStringProprieties() {
        HashMap<String, String> mapForProprieties = new HashMap<String, String>();
        try {
            // All key-value pairs of proprieties
            for (String key : appProps.stringPropertyNames()) {
                if (!isNumber(appProps.getProperty(key))) {
                    mapForProprieties.put(key, appProps.getProperty(key));
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return mapForProprieties;
    }

    public HashMap getNumericalProprieties() {
        HashMap<String, Number> mapForNumProprieties = new HashMap();
        try {
            for (String key : appProps.stringPropertyNames()) {
                if (isNumber(appProps.getProperty(key)) && !isInt(appProps.getProperty(key))) {
                    mapForNumProprieties.put(key, Float.parseFloat(appProps.getProperty(key)));
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return mapForNumProprieties;
    }

    public HashMap getIntProprieties() {
        HashMap<String, Integer> mapForIntProprieties = new HashMap();
        try {
            for (String key : appProps.stringPropertyNames()) {
                if (isInt(appProps.getProperty(key))) {
                    mapForIntProprieties.put(key, Integer.parseInt(appProps.getProperty(key)));
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return mapForIntProprieties;
    }

    public static boolean isInt(String s) {
        return s.trim().matches("\\d+");
        //[0-9] any number
        // *    0 or more of preceding token
        // \\   escape next symbol ( in my case - dot)
        // ?    match between 0 and 1 of the preceding token
    }

    public static boolean isNumber(String s) {
        return s.trim().matches("[0-9]*\\.?[0-9]*");
        //[0-9] any number
        // *    0 or more of preceding token
        // \\   escape next symbol ( in my case - dot)
        // ?    match between 0 and 1 of the preceding token
    }

}
