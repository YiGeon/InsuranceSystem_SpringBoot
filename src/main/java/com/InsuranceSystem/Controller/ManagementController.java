package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Service.ManagementService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagementController extends UiUtils{
    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping(path = "/management")
    public String getApproval(Model model) {
        List<Life> insuranceList = managementService.selectIns();
        model.addAttribute("insList", insuranceList);
        System.err.println("get insManage/insList_approval");
        return "insManage/insList_approval";
    }

    @PostMapping(path = "/management/delete")
    public String deleteIns(@RequestParam(value = "name", required = false) String name, Model model) {
        try {
            boolean isRegistered = managementService.deleteIns(name);
            System.err.println("delete " + name);
            if (isRegistered == false) {
                return showMessageWithRedirect("보험 삭제에 실패하였습니다.", "/management", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/management", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/management", Method.GET, null, model);
        }
        return showMessageWithRedirect("보험이 삭제되었습니다.", "/management", Method.GET, null, model);
    }
}
