package dir.ui.filemanager.tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import arjunasdk.config.RunConfig;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import unitee.annotations.TestClass;
import unitee.annotations.TestMethod;

@TestClass
public class QuitSmoking {
	
	static AndroidDriver driver;
	static DesiredCapabilities capa;

	public void setUpClassInstance() throws Exception {
		capa = new DesiredCapabilities();
		capa.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capa.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capa.setCapability(MobileCapabilityType.DEVICE_NAME, "5670d164");
		capa.setCapability(MobileCapabilityType.PLATFORM_VERSION, "4.4.2");
		capa.setCapability(MobileCapabilityType.NO_RESET, true);
		capa.setCapability(MobileCapabilityType.FULL_RESET, false);
		capa.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
		String apkPath = RunConfig.value("directory.project.root").asString().replace("mproject", "bin\\tools\\de.baumann.quitsmoking_22.apk");
		capa.setCapability(MobileCapabilityType.APP, apkPath);
		capa.setCapability("appPackage", "de.baumann.quitsmoking");
		
		capa.setCapability("appActivity", "de.baumann.quitsmoking.MainActivity");
		driver = new AndroidDriver(capa);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	
	@TestMethod
	public void createEntry() throws Exception
	{
		//Test Case: To create the entry.
		List<WebElement> daryHeader = driver.findElements(By.xpath("//android.widget.TextView[@text = 'Diary | title']"));
		if(daryHeader.size() == 0)
		{
			daryHeader = driver.findElements(By.xpath("//android.widget.TextView[@text = 'Diary | emoticon']"));
		}
		daryHeader.get(0).click();
		driver.findElement(By.id("de.baumann.quitsmoking:id/fab")).click();
		driver.findElement(By.id("de.baumann.quitsmoking:id/imageButtonPaste")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Paste actual time']")).click();
		driver.findElement(By.id("de.baumann.quitsmoking:id/note_text_input")).sendKeys("This is test content");
		driver.findElement(By.id("de.baumann.quitsmoking:id/action_save")).click();
		
	}
	
	@TestMethod
	public void addSomeHabits() throws Exception
	{
		//Test Case: To add the smoking habits to application
		driver.findElement(By.xpath("//android.support.v7.widget.LinearLayoutCompat/android.widget.ImageView")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text = 'Settings']")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().text(\"Smoke habits\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text = 'Smoke habits']")).click();
		driver.findElement(By.id("de.baumann.quitsmoking:id/editNumber")).clear();
		driver.findElement(By.id("de.baumann.quitsmoking:id/editNumber")).sendKeys("4");
		driver.findElement(By.id("de.baumann.quitsmoking:id/editTime")).clear();
		driver.findElement(By.id("de.baumann.quitsmoking:id/editTime")).sendKeys("35");
		driver.findElement(By.xpath("//android.widget.Button[@text = 'OK']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Settings']/../android.widget.ImageButton")).click();
	}
	
}
