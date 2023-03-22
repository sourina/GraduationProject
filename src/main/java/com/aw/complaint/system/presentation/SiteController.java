package com.aw.complaint.system.presentation;

import com.aw.complaint.system.business.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {

    @Autowired
    ClientService clientService;

    @GetMapping("/about-us")
    public String aboutUs(Model model) {
        model.addAttribute("client",clientService.getClient());
        return "about-us";
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("client",clientService.getClient());
        return "articles";
    }
    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("client",clientService.getClient());
        return "homepage";
    }
}
