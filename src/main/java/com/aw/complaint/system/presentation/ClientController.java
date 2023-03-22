package com.aw.complaint.system.presentation;

import com.aw.complaint.system.business.Client;
import com.aw.complaint.system.business.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/login")
    public String login(Model model) {
        Client client_login = new Client();
        model.addAttribute("client_emailId",client_login.getEmailId());
        model.addAttribute("client_password",client_login.getPassword());
        model.addAttribute("client_isAdmin",client_login.isAdmin());
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("emailId") String emailId,@RequestParam("password") String password,Model model) {
        if(clientService.logIn(emailId,password)){
            model.addAttribute("client",clientService.getClient());
            return "welcome";
        }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("client",new Client());
        return "signuppage";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute Client client, Model model) {
        clientService.signUp(client);
        model.addAttribute("client",clientService.getClient());
        return "welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        clientService.logOut(request);
        return "redirect:/";
    }
}
