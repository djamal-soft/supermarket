package com.ntic.discovery.controllers;


import com.ntic.discovery.dto.command.AddMsCommandDto;
import com.ntic.discovery.dto.command.DeleteMsCommandDto;
import com.ntic.discovery.dto.command.ReplaceMsCommandeDto;
import com.ntic.discovery.dto.query.MsQueryDto;
import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.enums.MicroserviceStatus;
import com.ntic.discovery.requestHandler.RequestHandler;
import com.ntic.discovery.service.ICommandService;
import com.ntic.discovery.service.impl.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MsCommandController {

    @Autowired
    private ICommandService commandService;

    @Autowired
    QueryServiceImpl queryService;

    /**
     *
     * @param microservice
     */
    @PostMapping(value = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMs(@RequestBody AddMsCommandDto microservice){

        if(microservice.getStatus() == null)
            microservice.setStatus(MicroserviceStatus.AVAILABLE);

        try {
            commandService.addMs(microservice);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * delete service
     * @param id
     */
    @DeleteMapping(value = "unregister/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMs(@PathVariable("id") int id){

        DeleteMsCommandDto deleteMsCommandDto = new DeleteMsCommandDto();
        deleteMsCommandDto.setId(id);

        commandService.deleteMs(deleteMsCommandDto);
    }

    /**
     *
     * @param microservice
     */
    @PostMapping(value = "replace")
    @ResponseStatus(HttpStatus.CREATED)
    public void replaceMs(@RequestBody ReplaceMsCommandeDto microservice){

        if (microservice.getStatus().equals(MicroserviceStatus.AVAILABLE))
            notifyControlService(microservice);

        commandService.replaceMs(microservice);
    }

    private void notifyControlService(ReplaceMsCommandeDto microservice) {
        RequestHandler handler = new RequestHandler();
        HttpEntity<ReplaceMsCommandeDto> request = new HttpEntity<>(microservice);
        handler.setServiceKey("duplicated")
                .setServiceVersion(-1)
                .setRequest(request)
                .setMethod(RequestHandler.POST)
                .setResponseType(Object.class)
                .handle();
    }
}
