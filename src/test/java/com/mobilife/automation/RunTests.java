package com.mobilife.automation;


import com.mobilife.Driver.DriverSingleton;
import com.mobilife.automation.glue.StepDefinition;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        tags = "@Done",
        plugin = {"pretty","html:target/cucumber-html-report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        objectFactory = io.cucumber.spring.SpringFactory.class

)


public class RunTests  {
    @Test
    public void test(){}
    /*
     * @Override
     *
     * @DataProvider(parallel = true) public Object[][] scenarios() {
     * System.out.println("PARALLEL"); return super.scenarios(); }
     */
    @AfterAll
    public static void tearDown (){
       // StepDefinition.extent.flush();
        DriverSingleton.closeObjectInstance();
    }
}
