package com.employee.api.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.api.base.testBase;
import com.employee.api.utilities.restUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Create_employee extends testBase {
	
	String ename = restUtils.empName();
	String esalary = restUtils.empSalary();
	String eage = restUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws InterruptedException {

		logger.info("****TC003_Create_employee started****");
		RestAssured.baseURI ="https://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		JSONObject reqParams = new JSONObject();
		
		reqParams.put("name", ename);
		reqParams.put("salary", esalary);
		reqParams.put("age", eage);
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(reqParams.toJSONString());
		
		response = httpRequest.request(Method.POST,"/create");
		
		Thread.sleep(5000);
		
	}
	
	@Test
	void checkResponseBody() {	
		
		logger.info("****checking response body****");
		String resData = response.getBody().asString();
		
		logger.info("response body==>"+" "+resData);
		Assert.assertTrue(resData.contains(ename));
		Assert.assertTrue(resData.contains(esalary));
		Assert.assertTrue(resData.contains(eage));
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
		logger.info("****finished TC003_Create_employee**** ");
	}
	
}
