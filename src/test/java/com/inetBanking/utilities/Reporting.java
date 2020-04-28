/**
 * 
 */
package com.inetBanking.utilities;
//Listener Class used to generate extent report

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.TestAttributeTestContext;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


/**
 * @author rathore
 *
 */
public class Reporting extends TestListenerAdapter {

	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentHtmlReporter htmlReporter;

	public void onStart(ITestContext testContext) {
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());// time stamp
		String repName = "Test-Report-"+timestamp+".html";
		//System.out.println("ReportName "+repName);
		//System.out.println(System.getProperty("user.dir")+"/test-output/"+repName);
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);// SpecifyLocation
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("User","RANU");

		htmlReporter.config().setDocumentTitle("Banking Test Project");// Tile of the Report
		htmlReporter.config().setReportName("Functional Test Report");// name of the Report
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.STANDARD);
		System.out.println(testContext.getName()+" test case started");
	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());// create new enter in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));// Create a passed entry in Report
																							
	}

	public void onTestFailure(ITestResult tr) {
		logger=extent.createTest(tr.getName());//create new enter in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//Create a failed entry in Report	
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f =new File(screenshotPath);
		
			if(f.exists()) {
				try {
					logger.addScreenCaptureFromPath(screenshotPath);
					//logger.fail("Screenshot is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			}catch(IOException e) {
				e.getMessage();}
			
			}
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());// create new enter in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));// Create a Skipped entry in testreport
																							
		
	}
	
	
	public void onFinish(ITestContext textcontext) {
		extent.flush();
		 
	}

}
