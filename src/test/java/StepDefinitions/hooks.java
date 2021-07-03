package StepDefinitions;

import Utilities.baseDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class hooks {

    @Before
    public void before(Scenario scenario)
    {
        System.out.println("The scenario is started");
        System.out.println("Scenario id = "+ scenario.getId());
        System.out.println("Scenario name = "+ scenario.getName());
    }

    @After
    public void after(Scenario scenario)
    {
        System.out.println("The scenario is finished");
        System.out.println("Result of the scenario = "+scenario.getStatus());
        System.out.println("Scenario isFailed = "+ scenario.isFailed());

        if (scenario.isFailed())
        {
            LocalDateTime dateTime=LocalDateTime.now();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd.MM.yy");

            TakesScreenshot screenshot=(TakesScreenshot) baseDriver.getDriver();
            File targetFile= screenshot.getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(targetFile,
                        new File("target/FailedScreenShots/"+scenario.getId()+dateTime.format(formatter)+".png"));

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        baseDriver.driverQuit();
    }
}
