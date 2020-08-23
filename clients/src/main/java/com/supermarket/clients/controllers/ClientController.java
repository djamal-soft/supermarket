package com.supermarket.clients.controllers;

import com.supermarket.clients.dao.ClientDao;
import com.supermarket.clients.exceptions.ClientNotFoundException;
import com.supermarket.clients.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientDao dao;

    @GetMapping(value = "clients")
    public List<Client> index() {

        return dao.findAll();
    }

    @GetMapping(value = "clients/{id}")
    public Client show(@PathVariable("id") int id) {

        Client client = dao.findById(id);

        if (client == null) throw new ClientNotFoundException();

        return client;
    }

    @GetMapping(value = "clientbyemail/{email}")
    public Client show(@PathVariable("email") String email) {

        Client client = dao.findByEmail(email);

        if (client == null) throw new ClientNotFoundException();

        return client;
    }

    @PostMapping(value = "clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client store(@Valid @RequestBody Client client) {

        return dao.save(client);
    }

    @PutMapping(value = "clients/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client update(@RequestBody Client client) {

        return dao.save(client);
    }

    @DeleteMapping(value = "clients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable("id") int id) {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            ;
        }
    }
}
