package com.supermarket.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class EmployeesApplication {

	@Autowired
	private static Environment environment;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EmployeesApplication.class, args);
		environment = (Environment) context.getBean("environment");
		String port = environment.getProperty("local.server.port");
		String host = "localhost";

		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			;
		}

		RegisterController register = new RegisterController();
		register.registerServices(host, port);
	}

}
