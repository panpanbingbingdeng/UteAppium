package org.example;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;



import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("deviceName","172.18.28.126:5555");
        //
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.hihonor.app.wearables");
        desiredCapabilities.setCapability("appActivity", "com.hihonor.app.wearables.MainActivity");


        desiredCapabilities.setCapability("automationName", "uiautomator2");
        AndroidDriver<WebElement> androidDriver = new AndroidDriver<WebElement>(new URL("http://172.18.28.194:4723/wd/hub"), desiredCapabilities);

        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //点击应用授权
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/main_btn_permission")).click();
//        点击允许访问通讯录
        androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
//        点击使用期前允许使用
        androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
//        点击获取设备信息
        androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();

//        点击扫描
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/main_btn_scan")).click();
//        点击开始扫码
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/scan_btn_scan")).click();
        WebElement add = null;
        String BluetoothAddress = "78:02:B7:BA:1E:35";
        String BluetoothAddress2 = "78:02:B7:F1:1F:60";
        while (add == null) {
            try {
                add = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"" + BluetoothAddress + "\"]"));
                if (add != null) {
                    add.click();
                    break;
                }
                System.out.println(BluetoothAddress);
            } catch (Exception e) {
                System.out.println(add);
            }
            try {
                add = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"" + BluetoothAddress2 + "\"]"));
                if (add != null) {
                    add.click();
                    break;
                }
                System.out.println(BluetoothAddress2);
            } catch (Exception e) {
                System.out.println(add);
            }

            androidDriver.findElement(By.id("com.hihonor.app.wearables:id/scan_btn_scan")).click();
            androidDriver.findElement(By.id("com.hihonor.app.wearables:id/scan_btn_scan")).click();


        }
        Thread.sleep(100);
        System.out.println("ok");

        String s = androidDriver.currentActivity();
        System.out.println(s);

        Thread.sleep(2000);
        while (true){
            String pageSource = androidDriver.getPageSource();
            System.out.println(pageSource);
            if (! pageSource.contains("android.widget.ProgressBar")){
                Thread.sleep(1000);
                AppiumDriverInit.SingleSliding(androidDriver,20,1200,400,1200,800);
                System.out.println(pageSource);
                break;
            }
            Thread.sleep(1000);
        }



    }
}