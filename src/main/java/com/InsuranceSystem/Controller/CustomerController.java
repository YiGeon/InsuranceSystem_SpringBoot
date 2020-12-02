package com.InsuranceSystem.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Service.CustomerService;

@Controller
public class CustomerController extends UiUtils {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping(path = "/customer")
	public String getCustomer(Model model) {
		List<Customer> customerList = customerService.selectCustomerList();
		model.addAttribute("customerList", customerList);
		System.err.println("get customer");
		return "customer/customerManage";
	}
	
	@GetMapping(path = "/customer/customerInfo")
	public String getCustomerInfo(@RequestParam(value = "customerID", required = false)int customerID, Model model) {
		if(customerID == 0) {
			return "redirect:/customer";
		}
		
		Customer customer = customerService.selectCustomer(customerID);
		if(customer == null) {
			return "redirect:/customer";
		}
		model.addAttribute("customer", customer);
		System.err.println("get customerInfo");
		return "customer/customerInfo";
	}
}
