package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.ContractConditions;
import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Insurance.insuranceType;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Development.ContractConditions.payCycle;
import com.InsuranceSystem.Sale.AssociationCus;
import com.InsuranceSystem.Service.CustomerService;
import com.InsuranceSystem.Service.SaleService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SaleController extends UiUtils {
    private final SaleService saleService;
    private final CustomerService customerService;

    public SaleController(SaleService saleService, CustomerService customerService) {
        this.saleService = saleService;
        this.customerService = customerService;
    }

    @GetMapping(path = "/sale")
    public String getSale(Model model) {
        System.err.println("get sale/type");
        return "sale/type";
    }


    /////////////////////////////////////////////////////////////////////// 생명보험가입

    @GetMapping(path = "/sale/life")
    public String saleLife(Model model) {
//        List<Life> lifeList = saleService.selectLife();
        model.addAttribute("customer", new Customer());
//        model.addAttribute("life", new Life());
//        model.addAttribute("lifeList", lifeList);
        System.err.println("sale/form_life");
        return "sale/form_life";
    }

    @PostMapping(path = "sale/lifeList")
    public String showLifeList(final Customer customer, Model model) {
        List<Life> lifeList = saleService.selectLife();
        model.addAttribute("lifeList", lifeList);
        model.addAttribute("customer", customer);
        model.addAttribute("life", new Life());
        System.err.println("get sale/lifeList");
        return "sale/lifeList";
    }


    @PostMapping(path = "sale/registerLife")
    public String registerLife(final Customer customer, final Life life, Model model) {
        try {
            boolean isRegisteredCust = saleService.registerCustomer(customer);      // 고객 가입을 먼저
            String customerID = saleService.selectCustID(customer.getCustomerName(), customer.getResidentNo());
            AssociationCus associationCus = new AssociationCus();
            associationCus.setCustomerID(Integer.parseInt(customerID));
            associationCus.setInsuranceID(life.getInsuranceID());
            
            //가입일 추가
            customer.setCustomerID(Integer.parseInt(customerID));
            customer.setAddInsuranceDate(LocalDate.now());
            ContractConditions contractConditions = saleService.selectContractConditions(life.getInsuranceID());
            customer.setMaturityDate(LocalDate.now().plusYears(contractConditions.getPeriod()));
            
            //보험료 추가
            int payment = calculate(customer, life.getInsuranceID());
            customer.setPremium(payment);
            
            boolean isRegisteredIns = saleService.registerIns(associationCus);
            boolean isupdateDate = saleService.updateDate(customer);
            if (isRegisteredCust == false || isRegisteredIns == false) {
                return showMessageWithRedirect("가입 등록에 실패하였습니다.", "/sale", Method.GET, null, model);
            }
            if(isupdateDate == false) {
            	System.out.println("날짜 오류");
            }
        } catch (DataAccessException e) {
        	System.out.println(e);
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        } catch (Exception e) {
        	System.out.println(e);
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        }
        return showMessageWithRedirect("가입 등록이 완료되었습니다.", "/sale", Method.GET, null, model);
    }


    /////////////////////////////////////////////////////////////////////// 화재보험가입

    @GetMapping(path = "/sale/fire")
    public String saleFire(Model model) {
        model.addAttribute("customer", new Customer());
        System.err.println("sale/form_fire");
        return "sale/form_fire";
    }

    @PostMapping(path = "sale/fireList")
    public String showFireList(final Customer customer, Model model) {
        List<Fire> lifeList = saleService.selectFire();
        model.addAttribute("fireList", lifeList);
        model.addAttribute("customer", customer);
        model.addAttribute("fire", new Fire());
        System.err.println("get sale/fireList");
        return "sale/fireList";
    }

    @PostMapping(path = "sale/registerFire")
    public String registerLife(final Customer customer, final Fire fire, Model model) {
        try {
            boolean isRegisteredCust = saleService.registerCustomer(customer);      // 고객 가입을 먼저
            String customerID = saleService.selectCustID(customer.getCustomerName(), customer.getResidentNo());
            AssociationCus associationCus = new AssociationCus();
            associationCus.setCustomerID(Integer.parseInt(customerID));
            associationCus.setInsuranceID(fire.getInsuranceID());
            
            //가입일 추가
            customer.setCustomerID(Integer.parseInt(customerID));
            customer.setAddInsuranceDate(LocalDate.now());
            ContractConditions contractConditions = saleService.selectContractConditions(fire.getInsuranceID());
            customer.setMaturityDate(LocalDate.now().plusYears(contractConditions.getPeriod()));
            
            
            //보험료 추가
            int payment = calculate(customer, fire.getInsuranceID());
            customer.setPremium(payment);
            
            boolean isupdateDate = saleService.updateDate(customer);
            boolean isRegisteredIns = saleService.registerIns(associationCus);
            if (isRegisteredCust == false || isRegisteredIns == false) {
                return showMessageWithRedirect("가입 등록에 실패하였습니다.", "/sale", Method.GET, null, model);
            }
            if(isupdateDate == false) {
            	System.out.println("날짜 오류");
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        }
        return showMessageWithRedirect("가입 등록이 완료되었습니다.", "/sale", Method.GET, null, model);
    }

    /////////////////////////////////////////////////////////////////////// 실비보험가입

    @GetMapping(path = "/sale/loss")
    public String saleLoss(Model model) {
        model.addAttribute("customer", new Customer());
        System.err.println("sale/form_loss");
        return "sale/form_loss";
    }

    @PostMapping(path = "sale/lossList")
    public String showLossList(final Customer customer, Model model) {
        List<LossProportionality> lossList = saleService.selectLoss();
        model.addAttribute("lossList", lossList);
        model.addAttribute("customer", customer);
        model.addAttribute("loss", new LossProportionality());
        System.err.println("get sale/lossList");
        return "sale/lossList";
    }

    @PostMapping(path = "sale/registerLoss")
    public String registerLoss(final Customer customer, final LossProportionality loss, Model model) {
        try {
            boolean isRegisteredCust = saleService.registerCustomer(customer);      // 고객 가입을 먼저
            String customerID = saleService.selectCustID(customer.getCustomerName(), customer.getResidentNo());
            AssociationCus associationCus = new AssociationCus();
            associationCus.setCustomerID(Integer.parseInt(customerID));
            associationCus.setInsuranceID(loss.getInsuranceID());
            
            //가입일 추가
            customer.setCustomerID(Integer.parseInt(customerID));
            customer.setAddInsuranceDate(LocalDate.now());
            ContractConditions contractConditions = saleService.selectContractConditions(loss.getInsuranceID());
            customer.setMaturityDate(LocalDate.now().plusYears(contractConditions.getPeriod()));
            
            //보험료 추가
            int payment = calculate(customer, loss.getInsuranceID());
            customer.setPremium(payment);
            boolean isupdateDate = saleService.updateDate(customer);
            boolean isRegisteredIns = saleService.registerIns(associationCus);
            if (isRegisteredCust == false || isRegisteredIns == false) {
                return showMessageWithRedirect("가입 등록에 실패하였습니다.", "/sale", Method.GET, null, model);
            }
            if(isupdateDate == false) {
            	System.out.println("날짜 오류");
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        }
        return showMessageWithRedirect("가입 등록이 완료되었습니다.", "/sale", Method.GET, null, model);
    }
    
    
    public int calculate(Customer customer, int insuranceID) {
    	int payment = 0;
    	
    	Insurance insurance = customerService.select_Insurance_insuranceID(insuranceID);
    	ContractConditions contractConditions = saleService.selectContractConditions(insuranceID);
    	
    	if(insurance.getInsuranceType().equals(insuranceType.Life)) {
    		Life life = saleService.select_Life_insuranceID(insuranceID);
    		insurance.setContractConditions(contractConditions);
    		double rate = life.calculateRate(customer);
    		System.out.println(life.getGuaranteeAmount());
    		if(contractConditions.getPayCycle().equals(payCycle.month)) {
    			payment = (int) (life.getGuaranteeAmount() * rate) / 12;
    		}else if (contractConditions.getPayCycle().equals(payCycle.week)) {
    			payment = (int) (life.getGuaranteeAmount() * rate) / 52;
    		}
    	}
    	else if(insurance.getInsuranceType().equals(insuranceType.Fire)) {
    		Fire fire = saleService.select_Fire_insuranceID(insuranceID);
    		insurance.setContractConditions(contractConditions);
    		double rate = fire.calculateRate(customer);
    		System.out.println(fire.getGuaranteeAmount());
    		if(contractConditions.getPayCycle().equals(payCycle.month)) {
    			payment = (int) (fire.getGuaranteeAmount() * rate) / 12;
    		}else if (contractConditions.getPayCycle().equals(payCycle.week)) {
    			payment = (int) (fire.getGuaranteeAmount() * rate) / 52;
    		}
    	}
    	else if(insurance.getInsuranceType().equals(insuranceType.Lossproportionality)) {
    		LossProportionality loss = saleService.select_Loss_insuranceID(insuranceID);
    		insurance.setContractConditions(contractConditions);
    		double rate = loss.calculateRate(customer);
    		if(contractConditions.getPayCycle().equals(payCycle.month)) {
    			payment = (int) (loss.getGuaranteeAmount() * rate) / 12;
    		}else if (contractConditions.getPayCycle().equals(payCycle.week)) {
    			payment = (int) (loss.getGuaranteeAmount() * rate) / 52;
    		}
    	}
    	System.out.println(payment);
    	return payment;
    }
}
