package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Sale.AssociationCus;
import com.InsuranceSystem.Service.SaleService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SaleController extends UiUtils {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(path = "/sale")
    public String getSale(Model model) {
        System.err.println("get sale/type");
        return "sale/type";
    }

    @GetMapping(path = "/sale/life")
    public String saleLife(Model model) {
        List<Life> lifeList = saleService.selectLife();
        model.addAttribute("customer", new Customer());
        model.addAttribute("life", new Life());
        model.addAttribute("lifeList", lifeList);
        System.err.println("sale/form_life");
        return "sale/form_life";
    }


    @PostMapping(path = "sale/registerLifeCust")
    public String registerLifeCust(final Customer customer, final Life life, Model model) {
        try {
            boolean isRegisteredCust = saleService.registerCustomer(customer);      // 고객 가입을 먼저
            String customerID = saleService.selectCustID(customer.getCustomerName(), customer.getResidentNo());
            AssociationCus associationCus = new AssociationCus();
            associationCus.setCustomerID(Integer.parseInt(customerID));
            associationCus.setInsuranceID(life.getInsuranceID());
            boolean isRegisteredIns = saleService.registerIns(associationCus);
            if (isRegisteredCust == false || isRegisteredIns == false) {
                return showMessageWithRedirect("가입 등록에 실패하였습니다.", "/sale", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/sale", Method.GET, null, model);
        }
        return showMessageWithRedirect("가입 등록이 완료되었습니다.", "/sale", Method.GET, null, model);
    }

}
