package com.ntic.discovery.service;


import com.ntic.discovery.dto.query.MsQueryDto;
import com.ntic.discovery.entity.Microservice;

public interface IQueryService {

    Microservice getMs(String keys, float version);
}
