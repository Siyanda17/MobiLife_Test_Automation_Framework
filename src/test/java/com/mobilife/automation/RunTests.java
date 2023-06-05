package com.mobilife.automation;


import com.mobilife.automation.glue.StepDefinition;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        tags = "@Done",
        plugin = {"pretty","html:target/cucumber-html-report.html"},
        objectFactory = io.cucumber.spring.SpringFactory.class

)


public class RunTests  {
    @Test
    public void test(){}

    @After
    public void tearDown(){
       // StepDefinition.extent.flush();
    }
}
