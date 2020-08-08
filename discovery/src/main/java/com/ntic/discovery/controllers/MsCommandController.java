package com.ntic.discovery.controllers;


import com.ntic.discovery.dto.command.AddMsCommandDto;
import com.ntic.discovery.dto.command.DeleteMsCommandDto;
import com.ntic.discovery.dto.command.ReplaceMsCommandeDto;
import com.ntic.discovery.dto.query.MsQueryDto;
import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.service.ICommandService;
import com.ntic.discovery.service.impl.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MsCommandController {

    @Autowired
    private ICommandService commandService;

    @Autowired
    QueryServiceImpl queryService;

    @PostMapping(value = "register")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMs(@RequestBody AddMsCommandDto microservice){

        try{
            MsQueryDto ms = queryService.getMs(microservice.getMkeys(), microservice.getVersion());
//            System.out.println("exist");
//            //microservice.setId(ms.getId());
            ReplaceMsCommandeDto replaceDto = new ReplaceMsCommandeDto();
            replaceDto.setId(ms.getId());
            replaceDto.setAddress(microservice.getAddress());
            replaceDto.setMkeys(microservice.getMkeys());
            replaceDto.setVersion(microservice.getVersion());
            commandService.replaceMs(replaceDto);
        } catch (Exception e) {
            // Store microservice
            commandService.addMs(microservice);
        }


//        if(ms != null) {
//
//        }
//        else {
//            System.out.println("not exist");
//
//        }
//        commandService.addMs(microservice);

    }

    @DeleteMapping(value = "unregister/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMs(@PathVariable("id") int id){

        DeleteMsCommandDto deleteMsCommandDto = new DeleteMsCommandDto();
        deleteMsCommandDto.setId(id);

        commandService.deleteMs(deleteMsCommandDto);
    }

    @PutMapping(value = "replace")
    @ResponseStatus(HttpStatus.CREATED)
    public void replaceMs(@RequestBody ReplaceMsCommandeDto microservice){
        commandService.replaceMs(microservice);
    }
}
