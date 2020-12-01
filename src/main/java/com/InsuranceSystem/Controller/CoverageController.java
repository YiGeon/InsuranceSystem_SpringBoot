package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Service.CoverageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CoverageController {
    private final CoverageService coverageService;

    public CoverageController(CoverageService coverageService) {
        this.coverageService = coverageService;
    }


    @GetMapping(path = "/receipt")
    public String gerReceipt(Model model) {
        List<Customer> customerList = coverageService.selectCus();
        model.addAttribute("customerList", customerList);
        System.err.println("get coverage/customerList");
        return "coverage/customerList";
    }

    @GetMapping(path = "/receipt/search")
    public String gerReceipt(@RequestParam(value = "keyword") String keyword, Model model) {
        List<Customer> customerList = coverageService.selectCusByName(keyword);
        model.addAttribute("customerList", customerList);
        System.err.println("get coverage/customerList search: " + keyword);
        return "coverage/customerList";
    }
}
