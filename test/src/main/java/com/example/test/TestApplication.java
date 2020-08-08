package com.example.test;

import com.example.test.controllers.TestController;
import com.example.test.models.ServiceInfos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {

		SpringApplication.run(TestApplication.class, args);

		ServiceInfos infos = new ServiceInfos();
		System.out.println(infos.getServerPort());

		TestController controller = new TestController();
		controller.getServiceInfos();
	}

}
