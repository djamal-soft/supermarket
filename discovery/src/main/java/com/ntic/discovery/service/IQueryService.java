package com.ntic.discovery.service;


import com.ntic.discovery.dto.query.MsQueryDto;

public interface IQueryService {

    MsQueryDto getMs(String keys, float version);
}
