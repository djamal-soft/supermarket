package com.ntic.discovery.service.impl;


import com.ntic.discovery.dto.query.MsQueryDto;
import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.exception.NotFoundException;
import com.ntic.discovery.repository.MicroserviceRepository;
import com.ntic.discovery.service.IQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements IQueryService {

    @Autowired
    private MicroserviceRepository mRepository;

    @Override
    public Microservice getMs(String keys, float version) {

        Microservice microservice;

        if(version == -1.0) {
            microservice = mRepository.getTopByMkeysContainingOrderByVersionDesc(keys);
        }
        else {
            microservice = mRepository.getTopByMkeysContainingAndVersionOrderByVersionDesc(keys, version);
        }

        if(microservice != null) {
//            MsQueryDto msQueryDto = new MsQueryDto();
//            msQueryDto.setId(microservice.getId());
//            msQueryDto.setAddress(microservice.getAddress());
//            msQueryDto.setKeys(microservice.getMkeys());
//            msQueryDto.setVersion(microservice.getVersion());
//
//            return msQueryDto;
            return microservice;
        }

        // MS not fount
        throw new NotFoundException();
    }
}
