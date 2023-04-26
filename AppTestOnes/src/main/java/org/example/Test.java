package org.example;

import java.io.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) throws IOException {
//        ResourceBundle resource = ResourceBundle.getBundle("Test");
////        System.out.println(resource.getString("add01"));
        InputStream is = Test.class.getClassLoader().getResourceAsStream("Test.properties");
        Properties properties = new Properties();
        properties.load(is);
        System.out.println("add01");

//        ResourceBundle rb2 = ResourceBundle.getBundle("appOtaText");//读取resources/config目录下的application.properties  appOtaText
//        for(String key : rb2.keySet()) {
//            String value = rb2.getString(key);
//            System.out.println(key + ":" + value);
////        }
//        InputStream in = new BufferedInputStream(new FileInputStream("appOtaText.properties")) ;
//        Properties p = new Properties();
//
//        p.load(in) ;
//
//        System.out.println(p.getProperty("add01"));
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("appOtaText"));
//        System.out.println(properties);

    }
}
