package org.example;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("deviceName","172.18.28.126:5555");
        desiredCapabilities.setCapability("deviceName", "357979100062987");
        desiredCapabilities.setCapability("udid", "172.18.28.126:5555");
        //
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.hihonor.app.wearables");
        desiredCapabilities.setCapability("appActivity", "com.hihonor.app.wearables.MainActivity");
//        desiredCapabilities.setCapability("noReset", "True");


        desiredCapabilities.setCapability("automationName", "uiautomator2");
        AndroidDriver<WebElement> androidDriver = new AndroidDriver<WebElement>(new URL("http://172.18.28.194:126/wd/hub"), desiredCapabilities);

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


        //连接手环
        WebElement add = null;
        String BluetoothAddress = "78:02:B7:BA:1E:35";
        String BluetoothAddress2 = "78:02:B7:F1:1F:60";
        while (add == null) {
            try {
                add = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"" + BluetoothAddress + "\"]"));
                if (add != null) {
                    add.click();
                    System.out.println(BluetoothAddress);
                    break;
                }

            } catch (Exception e) {
                System.out.println(add);
            }
            try {
                add = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"" + BluetoothAddress2 + "\"]"));
                if (add != null) {
                    add.click();
                    System.out.println(BluetoothAddress2);
                    break;
                }
                System.out.println(BluetoothAddress2);
            } catch (Exception e) {
                System.out.println(add);
            }
            androidDriver.findElement(By.id("com.hihonor.app.wearables:id/scan_btn_scan")).click();
            androidDriver.findElement(By.id("com.hihonor.app.wearables:id/scan_btn_scan")).click();
        }
        while (true) {
            String pageSource = androidDriver.getPageSource();
            if (!pageSource.contains("android.widget.ProgressBar")) {
                Thread.sleep(1000);
                KeyEvent keyEvent = new KeyEvent();
                keyEvent.withKey(AndroidKey.BACK);
                androidDriver.pressKey(keyEvent);
                System.out.println("滑动返回");
                break;
            }
            Thread.sleep(1000);
        }

        System.out.println("连接手环ok");

        Thread.sleep(2000);


        //OTA
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/main_btn_ota")).click();
        androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/bt_start_ota")).click();

        WebDriverWait wait = new WebDriverWait(androidDriver, 600);
        WebElement until = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text='升级完成']")));
        if (until != null) {
            Thread.sleep(2000);
            KeyEvent keyEvent = new KeyEvent();
            keyEvent.withKey(AndroidKey.BACK);
            androidDriver.pressKey(keyEvent);
            System.out.println("升级ok");
        }


    }
}