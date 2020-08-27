package com.ntic.cloning.dao;

import com.ntic.cloning.models.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MicroserviceDao extends JpaRepository<Microservice, Integer> {

    Microservice findByMkeysAndAddressAndVersion(String mKyes, String address, float version);
}
