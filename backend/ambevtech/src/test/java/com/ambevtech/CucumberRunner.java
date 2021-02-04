package com.ambevtech;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/ambevtech/features",
        glue = {"com.ambevtech"},
        plugin = {"pretty", "json:target/cucumber-json-report.json"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        dryRun = false
)
public class CucumberRunner {

}