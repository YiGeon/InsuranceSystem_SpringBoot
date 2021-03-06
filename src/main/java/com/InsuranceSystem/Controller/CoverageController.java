package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Coverage.Accident;
import com.InsuranceSystem.Coverage.DamageAssessed;
import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Sale.AssociationCus;
import com.InsuranceSystem.Service.CoverageService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoverageController extends UiUtils {
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

    @PostMapping(path = "/receipt/selectCustomer")
    public String selectCustomer (@RequestParam(value = "id") Integer id, Model model) {
        List<AssociationCus> subInfoList = coverageService.selectSubInfoByID(id);
        List<LossProportionality> insuranceList = new ArrayList<>();
        for (int i = 0; i < subInfoList.size(); i++) {
            insuranceList.add(coverageService.selectInsByID(subInfoList.get(i).getInsuranceID()));
        }
        Customer customer = coverageService.selectCustomerByID(id);
        model.addAttribute("insuranceList", insuranceList);
        model.addAttribute("insurance", new LossProportionality());
        model.addAttribute("customer", customer);
        model.addAttribute("accident", new Accident());
        System.err.println("get coverage/receiptForm/" + id);
        return "coverage/receiptForm";
    }

    @PostMapping(path = "/receipt/submit")
    public String selectCustomer (final Accident accident, final Customer customer, final LossProportionality parmInsurance, Model model) {
        try {
            int guaranteeAmount = 0;
            int limitRate = 0;
            LossProportionality insurance = coverageService.selectInsByID(parmInsurance.getInsuranceID());
            if (insurance.getInsuranceType().equals(Insurance.insuranceType.Life)) {
                guaranteeAmount = coverageService.selectLifeByID(insurance.getInsuranceID()).getGuaranteeAmount();
            } else if (insurance.getInsuranceType().equals(Insurance.insuranceType.Fire)) {
                guaranteeAmount = coverageService.selectFireByID(insurance.getInsuranceID()).getGuaranteeAmount();
            } else if (insurance.getInsuranceType().equals(Insurance.insuranceType.Lossproportionality)) {
                guaranteeAmount = coverageService.selectLossByID(insurance.getInsuranceID()).getGuaranteeAmount();
                limitRate = coverageService.selectLossByID(insurance.getInsuranceID()).getLimitRate();
            }
            insurance.setGuaranteeAmount(guaranteeAmount);
            insurance.setLimitRate(limitRate);
            
            accident.setReceivedAmount(coverageService.calReceivedAmount(accident,insurance, customer));
            accident.setCustomerID(customer.getCustomerID());
            accident.setCustomerName(customer.getCustomerName());
            boolean isRegistered = coverageService.submitReceipt(accident);
            if (isRegistered == false) {
                return showMessageWithRedirect("사고 등록에 실패하였습니다.", "/receipt", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/receipt", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/receipt", Method.GET, null, model);
        }
        return showMessageWithRedirect("사고 등록이 완료되었습니다.", "/receipt", Method.GET, null, model);

    }

    @GetMapping(path = "/accident")
    public String getAccidentList(Model model) {
        List<Accident> accidentList = coverageService.selectAllAccident();
        model.addAttribute("accidentList", accidentList);
        System.err.println("get coverage/accidentList");
        return "coverage/accidentList";
    }

    @PostMapping(path = "/accident/approve")
    public String approveAccident(@RequestParam(value = "id") Integer id, Model model) {
        try {
            boolean isUpdated = coverageService.update_Accident(id);
            if (isUpdated == false) {
                return showMessageWithRedirect("사고 승인에 실패하였습니다.", "/accident", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/accident", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/accident", Method.GET, null, model);
        }
        return showMessageWithRedirect("사고 접수가 승인되었습니다.", "/accident", Method.GET, null, model);
    }

    @PostMapping(path = "/accident/delete")
    public String deleteAccident(@RequestParam(value = "id") Integer id, Model model) {
        try {
            boolean isUpdated = coverageService.delete_Accident(id);
            if (isUpdated == false) {
                return showMessageWithRedirect("사고 삭제에 실패하였습니다.", "/accident", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/accident", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/accident", Method.GET, null, model);
        }
        return showMessageWithRedirect("사고 접수가 삭제되었습니다.", "/accident", Method.GET, null, model);
    }


    @GetMapping(path = "/assess")
    public String getAssess(Model model) {
        List<Accident> accidentList = coverageService.selectAllAssessedAccident();
        model.addAttribute("accidentList", accidentList);
        System.err.println("get coverage/assessedAccidentList");
        return "coverage/assessedAccidentList";
    }

    @PostMapping(path = "/assess/pay")
    public String payOfInsurance(@RequestParam(value = "id") Integer id, Model model) {
        try {
            boolean isPayed = coverageService.delete_Accident(id);
            if (isPayed == false) {
                return showMessageWithRedirect("보험금 지급에 실패하였습니다.", "/assess", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/assess", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/assess", Method.GET, null, model);
        }
        return showMessageWithRedirect("사고 보험금 지급이 완료되었습니다.", "/assess", Method.GET, null, model);
    }
}
