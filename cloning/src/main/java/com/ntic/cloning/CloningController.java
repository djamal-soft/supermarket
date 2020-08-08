package com.ntic.cloning;

import com.ntic.cloning.dto.NewServiceDto;
import com.ntic.cloning.models.Microservice;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CloningController {

    @PostMapping(value = "new-instance")
    public void newInstance(@RequestBody Microservice microservice) {

        Microservice microserviceCopy = new Microservice();
        microserviceCopy.setId(10);
        microserviceCopy.setAddress("http://localhost:8091/new-service");
        microserviceCopy.setMkeys("copy-copy");
        microserviceCopy.setVersion(3);

        NewServiceDto newServiceDto = new NewServiceDto();
        newServiceDto.setOldService(microservice);
        newServiceDto.setNewService(microserviceCopy);



//        microservice.setAddress("http://localhost:8091/new-instance-success");

        String controlServiceUrl = "http://localhost:8091/new-service";

        RestTemplate rest = new RestTemplate();
        HttpEntity<NewServiceDto> request = new HttpEntity<>(newServiceDto);
        System.out.println(microservice.getAddress());
        try {
            rest.postForLocation(controlServiceUrl, request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
