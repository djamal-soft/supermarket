package com.ntic.discovery.controllers;

import com.ntic.discovery.dto.query.MsQueryDto;
import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.service.IQueryService;
import com.ntic.discovery.service.impl.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsQueryController implements IQueryService {

    @Autowired
    QueryServiceImpl queryService;

    /**
     * find microservice by key and version
     * @param keys
     * @param version [ -1 When it is unknown ]
     * @return MsQueryDto
     */
    @GetMapping(value = "discovery/{keys}/{version}")
    @Override
    public Microservice getMs(@PathVariable("keys") String keys, @PathVariable("version") float version) {
        Microservice microservice = queryService.getMs(keys, version);

        return microservice;
    }
}
