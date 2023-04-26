package AppAuto_OtaTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;


public class TestNG_Test {
    private boolean state;
    @DataProvider
    public Object[][] addList(){
//        List<String> add=new ArrayList<>();

        return new Object[][] {
                {"dsad54sad","dsadas"},
                {"00021","489464"},
                {"00154021","489485464"}
        };
    }

//    @Description()
    public Object[][] addList2(){
//        List<String> add=new ArrayList<>();

        return new Object[][] {
                {"dsad54sad","dsadas"},
                {"00021","489464"},
                {"00154021","489485464"}
        };
    }


    @BeforeTest
    @Parameters({"data1","data2"})
    public void test_001() {
        test_001(null, null);
    }

    @BeforeTest()
    @Parameters({"data1","data2"})
    public void test_001(String data1 ,String data2){
        System.out.println(data1);
        System.out.println(data2);
        System.out.println("初始化完成");
    }

    @Test(dataProvider = "addList")
    @Parameters({"data1","data2"})
    public void test_002(String data1 ,String data2){
        System.out.println(data1);
        System.out.println(data2);
    }
    @Test
    @Parameters({"data1","data2"})
    public void test_0023(String data1 ,String data2){
        System.out.println(data1);
        System.out.println(data2);

    }
    @Test
    public void test6() throws IOException {

    }


//    @Test(dataProvider = "addList")
    @Parameters({})
    public void test_003(){


    }
}
