package com.supermarket.employeeUI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {

    @GetMapping(value = "statistics")
    public String statistics(Model model) {

        return "pages/admin/statistics";
    }
}
