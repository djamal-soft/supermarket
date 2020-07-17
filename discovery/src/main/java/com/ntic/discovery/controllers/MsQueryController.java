package com.ntic.discovery.controllers;

import com.ntic.discovery.dto.query.MsQueryDto;
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
     *
     * @param keys
     * @param version [ -1 When it is unknown ]
     * @return MsQueryDto
     */
    @GetMapping(value = "discovery/{keys}/{version}")
    @Override
    public MsQueryDto getMs(@PathVariable("keys") String keys, @PathVariable("version") float version) {

        return queryService.getMs(keys, version);
    }
}
