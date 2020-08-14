package com.supermarket.clientUI.controller;

import com.supermarket.clientUI.models.Client;
import com.supermarket.clientUI.proxies.ClientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    ClientProxy clientProxy;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "login")
    public String loginView(Model model) {

        return "login";
    }

    @GetMapping(value = "register")
    public String registerView(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }

    @PostMapping(value = "register")
    public String register(Client client) {

        System.out.println(client);
        String password = passwordEncoder.encode(client.getPassword());
        client.setPassword(password);
        clientProxy.registerClient(client);

        return "redirect:/login";
    }
}
