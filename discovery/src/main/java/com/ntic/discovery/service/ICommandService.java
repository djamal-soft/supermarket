package com.ntic.discovery.service;


import com.ntic.discovery.dto.command.AddMsCommandDto;
import com.ntic.discovery.dto.command.DeleteMsCommandDto;
import com.ntic.discovery.dto.command.ReplaceMsCommandeDto;

public interface ICommandService {

    void addMs(AddMsCommandDto addMs);
    void deleteMs(DeleteMsCommandDto deleteMs);
    void replaceMs(ReplaceMsCommandeDto replaceMs);
}
