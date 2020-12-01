package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Customer.Customer;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Sale.AssociationCus;
import com.InsuranceSystem.Service.UWService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UWController extends UiUtils{
    private final UWService uwService;

    public UWController(UWService uwService) {
        this.uwService = uwService;
    }

    @GetMapping(path = "/uw")
    public String getUW(Model model) {
        List<AssociationCus> subInfoList = uwService.selectSubInfo();
        model.addAttribute("subInfoList", subInfoList);
        System.err.println("get uw/uwList");
        return "uw/uwList";
    }

    @PostMapping(path = "/uw/approve")
    public String approve(@RequestParam(value = "id", required = false) Integer id, Model model) {
        try {
            boolean isRegistered = uwService.approveCus(id);
            System.err.println("approve subinfoId: " + id);
            if (isRegistered == false) {
                return showMessageWithRedirect("가입 승인에 실패하였습니다.", "/uw", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/uw", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/uw", Method.GET, null, model);
        }
        return showMessageWithRedirect("가입 승인이 완료되었습니다.", "/uw", Method.GET, null, model);
    }

}
