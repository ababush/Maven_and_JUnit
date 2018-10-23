package com.endava.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ProjectLogger {



    public static Logger getInstance(Class<?> className) {

        final Logger logger = LogManager.getLogger(className.getName());
        String log4jConfPath = "C:\\Users\\ababus\\IdeaProjects\\HW_ModularityProject\\ModularityProject\\Utils\\src\\main\\resources\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        return logger;
    }


}
