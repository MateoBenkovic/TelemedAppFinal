package com.telemed.telemedApp;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

        @GetMapping("/login")
        public String login() {
            return "login.html";
        }

        @GetMapping("/")
        public String loginIndex() {
            return "redirect:/login";
        }

        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.invalidate();
            return "redirect:/login";
        }
    }
