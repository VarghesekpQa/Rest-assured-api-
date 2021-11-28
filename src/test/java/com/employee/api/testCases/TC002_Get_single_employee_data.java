package com.employee.api.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.api.base.testBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_single_employee_data extends testBase {

	@BeforeClass
	void getEmployeeData() throws InterruptedException{
		
		logger.info("****TC002_Get_single_employee_data started****");
		RestAssured.baseURI ="https://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employee/"+empId);
		
		Thread.sleep(5000);	
	}
	
	@Test
	void checkResponseBody() {	
		
		logger.info("****checking response body****");
		String resData = response.getBody().asString();
		
		logger.info("response body==>"+" "+resData);
		Assert.assertTrue(resData!=null);
	}
	
	@Test
	void checkStatusCode() {
		
		logger.info("****checking status code****");
		int statusCode = response.getStatusCode();
		
		logger.info("status code"+" "+statusCode);
		Assert.assertEquals(statusCode,200);
	}
	
	@Test
	void checkStatusLine() {
		
		logger.info("****checking status line****");
		String statusLine = response.getStatusLine();
		
		System.out.println(statusLine);
		
		logger.info("status code"+" "+statusLine);
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType() {
		logger.info("****checking content type****");
		String contentType = response.header("Content-Type");
		logger.info("content type is " +contentType );
		Assert.assertEquals(contentType,"application/json");
	}
	
	@Test
	void checkServerType() {
		logger.info("****checking server type****");
		String serverType = response.header("Server");
		logger.info("content type is " +serverType );
		Assert.assertEquals(serverType,"cloudflare");
	}
	
	@Test
	void checkResponseTime() {
		logger.info("****checking response time****");
		long resTime = response.getTime();
		logger.info("response time is " +resTime );
		
		if(resTime>2000) {
			
			logger.warn("response time is greater than 2000!!!");
		}
		
		Assert.assertTrue(resTime<2000);
	}
	
	
	@Test
	void tearDown() {
		logger.info("****finished TC002_Get_single_employee_data**** ");
	}

}
