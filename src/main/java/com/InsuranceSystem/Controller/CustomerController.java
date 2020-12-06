package com.InsuranceSystem.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Sale.AssociationCus;
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
	public String getCustomerInfo(@RequestParam(value = "customerID", required = false) int customerID, Model model) {
		if (customerID == 0) {
			return "redirect:/customer";
		}

		Customer customer = customerService.selectCustomer(customerID);
		int insuranceID = customerService.select_CustomerIDtoInsuranceID(customerID);
		Life insurance = customerService.select_Insurance_insuranceID(insuranceID); // 원래 Insurance로 받아와야 되는데 안되네요 자바
																					// ㅆㅅㄲ
		if (customer == null) {
			return "redirect:/customer";
		}
		model.addAttribute("customer", customer);
		model.addAttribute("insurance", insurance);
		System.err.println("get customerInfo");
		return "customer/customerInfo";
	}
	
	@GetMapping(path = "/customer/modifiedLife")
	public String getModifiedInfo(@RequestParam(value = "customerID", required = false) int customerID, Model model) {
		if (customerID == 0) {
			return "redirect:/customer";
		}

		Customer customer = customerService.selectCustomer(customerID);
		int insuranceID = customerService.select_CustomerIDtoInsuranceID(customerID);
		Life insurance = customerService.select_Insurance_insuranceID(insuranceID); // 원래 Insurance로 받아와야 되는데 안되네요 자바
																					// ㅆㅅㄲ
		System.out.println(insurance.getInsuranceType());
		if (customer == null) {
			return "redirect:/customer";
		}
		model.addAttribute("customer", customer);
		model.addAttribute("insurance", insurance);
		System.err.println("get customerInfo");
		return "customer/modifiedLife";
	}
	@PostMapping(path = "customer/modifiedC")
	public String modifiedCustomer(final Customer customer, Model model) {
		try {
			
			System.out.println(customer.getCustomerName());
			System.out.println(customer.getCustomerID());
			System.out.println(customer.getAge());
			System.out.println(customer.getPhoneNo());
			boolean ismodifiedCustomer = customerService.updateCustomer(customer); // 고객 가입을 먼저
			
			if (ismodifiedCustomer == false) {
				return showMessageWithRedirect("정보 변경에 실패하였습니다.", "/customer", Method.GET, null, model);
			}
			
		} catch (DataAccessException e) {
			System.out.println(e);
			return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/customer", Method.GET, null, model);
		} catch (Exception e) {
			System.out.println(e);
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/customer", Method.GET, null, model);
		}
		return showMessageWithRedirect("변경이 완료되었습니다.", "/customer", Method.GET, null, model);
	}
	
	@PostMapping(path = "/customer/delete")
	public String deleteCustomer(@RequestParam(value = "customerID", required = false) int customerID, Model model) {
		try {
			System.out.println("@RequestParam :" + customerID);
			boolean isRegistered = customerService.deleteCustomer(customerID);
			System.err.println("delete " + customerID);
			if (isRegistered == false) {
				return showMessageWithRedirect("고객 삭제에 실패하였습니다.", "/customer", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/customer", Method.GET, null, model);
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/customer", Method.GET, null, model);
		}
		return showMessageWithRedirect("보험이 삭제되었습니다.", "/customer", Method.GET, null, model);
	}

	
}
