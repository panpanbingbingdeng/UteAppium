package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("deviceName","172.18.28.194:5555");
        desiredCapabilities.setCapability("platformName","Android");
        desiredCapabilities.setCapability("appPackage","com.hihonor.app.wearables");
        desiredCapabilities.setCapability("appActivity","com.hihonor.app.wearables.MainActivity");
//        desiredCapabilities.setCapability("appPackage","com.tencent.mobileqq");
//        desiredCapabilities.setCapability("appActivity","com.tencent.mobileqq..activity.SplashActivity");



//        desiredCapabilities.setCapability("automationName","uiautomator2");

        AndroidDriver<WebElement> androidDriver= new AndroidDriver<WebElement>(new URL("http://172.18.28.194:4723/wd/hub"),desiredCapabilities);




    }
}