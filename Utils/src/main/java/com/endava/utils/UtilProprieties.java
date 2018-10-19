package com.endava.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UtilProprieties {
            public static final Logger LOGGER = Logger.getLogger("UtilProprieties.java");
            protected static FileHandler fh;
            protected static Properties appProps = null;

    public UtilProprieties(String srcToConfigFile) {
                setLoggingFile();
                if (srcToConfigFile.equals("")) {
                    throw new NullPointerException("Path to file can't be empty");
                } else {
            getProprietiesFile(srcToConfigFile);
        }
    }

    private static void setLoggingFile() {
        try {
            fh = new FileHandler("C:\\Users\\ababus\\IdeaProjects\\HW_ModularityProject\\ModularityProject\\Utils\\src\\main\\resources\\MavenAppLogFile.log");
            LOGGER.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProprietiesFile(String configPath) {

        try {
            appProps = new Properties();
            appProps.load(new FileInputStream(configPath));
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
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
            LOGGER.info(ex.getMessage());
        }
        return mapForProprieties;
    }

    public HashMap getNumericalProprieties() {
        HashMap<String, Number> mapForIntProprieties = new HashMap();
        try {
            for (String key : appProps.stringPropertyNames()) {
                if (isNumber(appProps.getProperty(key))) {
                    mapForIntProprieties.put(key, Float.parseFloat(appProps.getProperty(key)));
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getMessage());
        }
        return mapForIntProprieties;
    }

    private static boolean isNumber(String s) {
        return s.trim().matches("[0-9]*\\.?[0-9]");
        //[0-9] any number
        // *    0 or more of preceding token
        // \\   escape next symbol ( in my case - dot)
        // ?    match between 0 and 1 of the preceding token
    }

}
