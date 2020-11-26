package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Service.ApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ApprovalController {

    private final ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping(path = "/approval")
    public String approval(Model model) {
        System.err.println("get approval");
        return "approval/type";
    }

    @GetMapping(path = "/approval/lifeList")
    public String approvalLife(Model model) {
        List<Life> lifeList = approvalService.selectLife();
        model.addAttribute("lifeList", lifeList);
        System.err.println("get approval/lifeList");
        return "approval/lifeList";
    }

    @GetMapping(path = "/approval/fireList")
    public String approvalFire(Model model) {
        List<Fire> fireList = approvalService.selectFire();
        model.addAttribute("fireList", fireList);
        System.err.println("get approval/fireList");
        return "approval/fireList";
    }

    @GetMapping(path = "/approval/lossList")
    public String approvalLoss(Model model) {
        List<LossProportionality> lossList = approvalService.selectLoss();
        model.addAttribute("lossList", lossList);
        System.err.println("get approval/lossList");
        return "approval/lossList";
    }

}
