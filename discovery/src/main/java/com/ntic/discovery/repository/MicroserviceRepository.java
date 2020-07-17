package com.ntic.discovery.repository;


import com.ntic.discovery.entity.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroserviceRepository extends JpaRepository<Microservice, Integer> {

    Microservice findById(int id);
    Microservice getTopByMkeysContainingOrderByVersionDesc(String mkeys);
    Microservice getTopByMkeysContainingAndVersionOrderByVersionDesc(String mkeys, float version);
}
