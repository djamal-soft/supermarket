package com.supermarket.employeeUI.controllers;

import com.supermarket.employeeUI.models.Client;
import com.supermarket.employeeUI.proxies.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientsController {

    @Autowired
    ClientProxy clientProxy;

    @GetMapping(value = "clients")
    public String allClients(Model model) {

        List<Client> clients = clientProxy.allClients();

        model.addAttribute("clients", clients);

        return "pages/admin/clients";
    }

    @DeleteMapping(value = "clients")
    public String deleteClient(@RequestParam("id") int id) {

        clientProxy.deleteClient(id);

        return "redirect:/clients";
    }
}
