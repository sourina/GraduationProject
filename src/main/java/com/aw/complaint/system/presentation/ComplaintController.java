package com.aw.complaint.system.presentation;

import com.aw.complaint.system.business.ClientService;
import com.aw.complaint.system.business.Complaint;
import com.aw.complaint.system.business.ComplaintService;
import com.aw.complaint.system.business.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;

    @Autowired
    ClientService clientService;

    @GetMapping("/complaint-page")
    public String getComplaintPage(Model model) {
        model.addAttribute("client",clientService.getClient());
        model.addAttribute("complaint",new Complaint());
        return "complaint-page";
    }
    @PostMapping("/submitted")
    public String createComplaint(@ModelAttribute Complaint complaint, Model model) {
        model.addAttribute("client",clientService.getClient());
        complaint.setClient(clientService.getClient());
        complaintService.createComplaint(complaint);
        model.addAttribute("trackingId",complaint.getId());
        return "confirm-page";
    }


    @GetMapping("/confirm-page")
    public String showComplaintById(@RequestParam (name="trackingId", required = false) Long id, Model model) {
        model.addAttribute("complaint",complaintService.trackComplaintById(id));
        model.addAttribute("client",clientService.getClient());
        model.addAttribute("statusOptions", Status.values());
        return "edit-complaint";
    }
    @PostMapping("/confirm-page")
    public String updateComplaint(@RequestParam Long id,@RequestParam (name="selectedStatus", required = true) String status, Model model) {
        model.addAttribute("client",clientService.getClient());
        complaintService.updateComplaint(id,status);
        return "redirect:/viewall";
    }

    @GetMapping("/view")
    public String viewComplaints(Model model){
        model.addAttribute("client",clientService.getClient());
        List<Complaint> complaintList =complaintService.viewComplaints(clientService.getClient().getEmailId());
        model.addAttribute("viewcomplaints",complaintList);
        return "client-allcomplaints";
    }


    @GetMapping("/viewall")
    public String viewAllComplaints(Model model){
        model.addAttribute("client",clientService.getClient());
        List<Complaint> allComplaintList =complaintService.viewAllComplaints();
        model.addAttribute("viewallcomplaints",allComplaintList);
        return "view-all-complaints";
    }

}

