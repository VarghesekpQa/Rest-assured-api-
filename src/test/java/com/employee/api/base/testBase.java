package com.employee.api.base;



import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empId = "5";	//Input for get single user and update
	
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		logger =Logger.getLogger("employeeApiLogger");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
		
	}
	
}
