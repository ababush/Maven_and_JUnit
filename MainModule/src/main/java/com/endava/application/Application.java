package com.endava.application;

import com.endava.utils.UtilProprieties;

import java.util.HashMap;

public class Application {

    public static void main(String[] args) {
        final String srcToConfig = "MainModule/target/maven-archiver/pom.properties";
        final UtilProprieties properties = new UtilProprieties(srcToConfig);
        printProprieties(properties.getNumericalProprieties(), "Numeric");
        printProprieties(properties.getStringProprieties(), "String");
    }

    private static void printProprieties(HashMap map, String typeOfProperties){
        System.out.println( "\n===== " + typeOfProperties.trim() + "-value proprieties: =====");
        map.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}