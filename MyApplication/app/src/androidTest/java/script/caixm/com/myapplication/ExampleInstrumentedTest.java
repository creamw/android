package script.caixm.com.myapplication;

import android.content.Context;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    /**
     * 惠头条脚本
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void runTask() throws InterruptedException, IOException {
        UiDevice mDevice=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());//获取设备用例
        Runtime rt = Runtime.getRuntime();
        UiObject returning;
        int mi;
        while(true) {
            Thread.sleep(1000);
            mDevice.click(500,500);
            Thread.sleep(500);
            try{
                returning = mDevice.findObject(new UiSelector().text("返回"));
                //boolean exists = returning.exists();
                //System.out.println(exists + "元素状态");
                if(!returning.exists()){
                    mDevice.executeShellCommand("am force-stop com.cashtoutiao");
                    Thread.sleep(500);
                    rt.exec("am start -n com.cashtoutiao/.common.ui.SplashActivity");
                    Thread.sleep(1200);
                    mDevice.click(500,500);
                    Thread.sleep(5000);//页面加载等待时间
                }
            }catch (Exception e){
                try {
                    mDevice.executeShellCommand("am force-stop com.cashtoutiao");
                    Thread.sleep(1000);
                    rt.exec("am start -n com.cashtoutiao/.common.ui.SplashActivity");
                } catch (IOException e1) {
                    System.out.println("重新启动activity失败");
                    e1.printStackTrace();
                }
                Thread.sleep(1200);
                mDevice.click(500,500);
                e.printStackTrace();
            }

            mi = 0;
            while(mi<40){
                returning = mDevice.findObject(new UiSelector().text("返回"));
                if(!returning.exists()){
                    break;
                }
                mDevice.swipe(500,1700,500,0,150);
                Thread.sleep(1500);//
                mi++;
            }
            //Thread.sleep(2500);
            mDevice.pressBack();
            //boolean exists = mDevice.findObject(new UiSelector().text("点击领取")).exists();
            //如果没有就重启activity
            if (!mDevice.findObject(new UiSelector().text("点击领取")).exists()){
                mDevice.executeShellCommand("am force-stop com.cashtoutiao");
                Thread.sleep(1000);
                rt.exec("am start -n com.cashtoutiao/.common.ui.SplashActivity");
            }
            //Thread.sleep(500);
            mDevice.swipe(500,1600,500,1100,50);
        }
        //adb shell am instrument -w -r   -e debug false -e class 'script.caixm.com.myapplication.ExampleInstrumentedTest#runTask' script.caixm.com.myapplication.test/android.support.test.runner.AndroidJUnitRunner
    }

    /**
     * 聚头条脚本
     * @throws IOException
     * @throws InterruptedException
     * @throws UiObjectNotFoundException
     */
    @Test
    public void test() throws IOException, InterruptedException, UiObjectNotFoundException {
        UiDevice mDevice=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());//获取设备用例

    }
}
