package AppAuto_OtaTest;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ota_Test02 {
    AndroidDriver<WebElement> androidDriver;

    @BeforeTest
    public void Test_Start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


//        desiredCapabilities.setCapability("deviceName", "172.18.28.160");
        desiredCapabilities.setCapability("deviceName", "荣耀手机");
        desiredCapabilities.setCapability("udid", "172.18.28.190:5555");




        desiredCapabilities.setCapability("platformName", "Android");

        desiredCapabilities.setCapability("appPackage", "com.hihonor.app.wearables");
        desiredCapabilities.setCapability("appActivity", "com.hihonor.app.wearables.MainActivity");

        desiredCapabilities.setCapability("noReset", "True");
//        desiredCapabilities.setCapability("automationName", "uiautomator2");
//    androidDriver = new AndroidDriver<WebElement>(new URL("http://172.18.28.194:4723/wd/hub"), desiredCapabilities);
        androidDriver = new AndroidDriver<>(new URL("http://172.18.28.194:190/wd/hub"), desiredCapabilities);

        //隐式等待
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        System.out.println("初始化成功");
    }

    @Test(invocationCount = 10,timeOut = 720000)
//    @Test
    public void Test_OTA() throws Exception {
//        androidDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        点击扫描
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/main_btn_scan")).click();
//        点击开始扫码
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/scan_btn_scan")).click();

////        连接手环
        WebElement add = null;
        List<String> BluetoothAddressList = new ArrayList<>();
//            Set<String>
        BluetoothAddressList.add("78:02:B7:BA:1E:35");
        BluetoothAddressList.add("78:02:B7:F1:1F:60");
        BluetoothAddressList.add("78:02:B7:1F:BB:F4");
        BluetoothAddressList.add("78:02:B7:E6:76:82");
        BluetoothAddressList.add("78:02:B7:14:6D:64");
        BluetoothAddressList.add("78:02:B7:39:AA:42");
        while (add == null) {
            for (int i = 0; i < BluetoothAddressList.size(); i++) {
                try {
                    add = androidDriver.findElement(By.xpath("//android.widget.TextView[@text=\"" + BluetoothAddressList.get(i) + "\"]"));
                    if (add != null) {
                        add.click();
                        Thread.sleep(1000);
                        System.out.println("已连接："+BluetoothAddressList.get(i));
                        break;
                    }
                    System.out.println("未连接："+BluetoothAddressList.get(i));
                } catch (Exception e) {
                    System.out.println(add);
                }
            }
            if (add != null) {
                break;
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
        //OTA
        androidDriver.findElement(By.id("com.hihonor.app.wearables:id/main_btn_ota")).click();

//        try {
//            androidDriver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
//        } catch (Exception E) {
//            System.out.println("已点击");
//        }

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
        Activity activity = new Activity("com.hihonor.app.wearables", "com.hihonor.app.wearables.MainActivity");
        androidDriver.startActivity(activity);
    }


    @AfterTest
    public void Test_end() {
        androidDriver.closeApp();
        System.out.println("关闭app");
    }

}
