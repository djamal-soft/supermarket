package com.ntic.discovery.service.impl;


import com.ntic.discovery.dto.command.AddMsCommandDto;
import com.ntic.discovery.dto.command.DeleteMsCommandDto;
import com.ntic.discovery.dto.command.ReplaceMsCommandeDto;
import com.ntic.discovery.entity.Microservice;
import com.ntic.discovery.exception.DuplicatedEntryException;
import com.ntic.discovery.exception.NotFoundException;
import com.ntic.discovery.repository.MicroserviceRepository;
import com.ntic.discovery.service.ICommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements ICommandService {

    @Autowired
    private MicroserviceRepository mRepository;


    @Override
    public void addMs(AddMsCommandDto addMs) {

        Microservice microservice = new Microservice();
        microservice.setName(addMs.getName());
        microservice.setAddress(addMs.getAddress());
        microservice.setMkeys(addMs.getMkeys());
        microservice.setVersion(addMs.getVersion());
        microservice.setStatus(addMs.getStatus());

        mRepository.save(microservice);
    }

    @Override
    public void deleteMs(DeleteMsCommandDto deleteMs) {
        Microservice microservice = new Microservice();
        microservice.setId(deleteMs.getId());

        try {
            mRepository.save(microservice);
        }
        catch (Exception e) {
            throw new DuplicatedEntryException();
        }
    }

    @Override
    public void replaceMs(ReplaceMsCommandeDto replaceMs) {
        Microservice microservice = mRepository.findById(replaceMs.getId());
        if(microservice == null)
            throw  new NotFoundException();

        microservice.setName(replaceMs.getName());
        microservice.setAddress(replaceMs.getAddress());
        microservice.setMkeys(replaceMs.getMkeys());
        microservice.setVersion(replaceMs.getVersion());
        microservice.setStatus(replaceMs.getStatus());

        try {
            mRepository.save(microservice);
        }
        catch (Exception e) {
            throw new DuplicatedEntryException();
        }
    }
}
