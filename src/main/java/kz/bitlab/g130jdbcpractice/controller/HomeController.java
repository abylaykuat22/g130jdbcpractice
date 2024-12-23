package kz.bitlab.g130jdbcpractice.controller;

import kz.bitlab.g130jdbcpractice.entity.ApplicationRequest;
import kz.bitlab.g130jdbcpractice.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ApplicationRequestService applicationRequestService;

    @GetMapping("/")
    public String home(Model model) {
        List<ApplicationRequest> applicationRequests = applicationRequestService.getAllApplicationRequests();
        model.addAttribute("zayavki", applicationRequests);
        return "home";
    }

    @GetMapping("/application-requests/{id}")
    public String detailsPage(@PathVariable Long id, Model model) {
        ApplicationRequest applicationRequest = applicationRequestService.getApplicationRequestById(id);
        model.addAttribute("zayavka", applicationRequest);
        return "application-request";
    }

    @PostMapping("/application-requests/add")
    public String addApplicationRequest(ApplicationRequest applicationRequest) {
        applicationRequestService.addApplicationRequest(applicationRequest);
        return "redirect:/";
    }

    @PostMapping("/application-requests/update")
    public String updateApplicationRequest(ApplicationRequest applicationRequest) {
        applicationRequestService.updateApplicationRequest(applicationRequest);
        return "redirect:/";
    }

    @PostMapping("/application-requests/delete/{id}")
    public String deleteApplicationRequest(@PathVariable Long id) {
        applicationRequestService.deleteApplicationRequestById(id);
        return "redirect:/";
    }
}
