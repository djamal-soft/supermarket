package com.supermarket.employeeUI.controllers;

import com.supermarket.employeeUI.auth.ApplicationUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(value = "/login")
    public String authView() {

        return "pages/auth-login";
    }

    @GetMapping(value = "success-login")
    public String redirectAfterLogin() {
        String redirectTo = "";

        switch (getEmployeeRole()) {
            case "DELIVERY_MAN":
                redirectTo = "redirect:/my-orders";
                break;

            case "ADMIN":
                redirectTo = "redirect:/statistics";
                break;
        }

        return redirectTo;
    }

    /**
     * Get logged id employee id
     * @return int
     */
    private String getEmployeeRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser employee = (ApplicationUser)authentication.getPrincipal();

        return employee.getRole();
    }
}
