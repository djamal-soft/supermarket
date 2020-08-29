package com.ntic.discovery.service.impl;


import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.enums.MicroserviceStatus;
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

            microservice = mRepository.getTopByMkeysAndStatusOrderByIdDesc(keys, MicroserviceStatus.AVAILABLE);
            if(microservice == null)
                microservice = mRepository.getTopByMkeysOrderByIdDesc(keys);
        }
        else {
            microservice = mRepository.getTopByMkeysAndVersionAndStatusOrderByIdDesc(keys, version, MicroserviceStatus.AVAILABLE);
            if(microservice == null)
                microservice = mRepository.getTopByMkeysAndVersionOrderByIdDesc(keys, version);
        }

        if(microservice != null) return microservice;

        // MS not fount
        throw new NotFoundException();
    }
}
