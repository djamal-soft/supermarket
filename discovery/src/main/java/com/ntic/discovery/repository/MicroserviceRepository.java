package com.ntic.discovery.repository;


import com.ntic.discovery.entity.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicroserviceRepository extends JpaRepository<Microservice, Integer> {

    Microservice findById(int id);
    Microservice getTopByMkeysOrderByIdDesc(String mkeys);
    Microservice getTopByMkeysAndVersionOrderByIdDesc(String mkeys, float version);

    Microservice getTopByMkeysAndStatusOrderByIdDesc(String mkeys, String status);
    Microservice getTopByMkeysAndVersionAndStatusOrderByIdDesc(String mkeys, float version, String status);

}
