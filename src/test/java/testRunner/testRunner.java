package testRunner;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={".//Features//login.feature"},
		glue="stepDefinitions",
		plugin={"pretty",
				"html:reports/report.html",
				"json:reports/report.json"},
		dryRun=false,
		monochrome=true
		)

public class testRunner {

}
