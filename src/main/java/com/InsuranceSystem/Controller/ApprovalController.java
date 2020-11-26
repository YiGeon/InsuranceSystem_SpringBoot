package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Development.Fire;
import com.InsuranceSystem.Development.Insurance;
import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Development.LossProportionality;
import com.InsuranceSystem.Service.ApprovalService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ApprovalController extends UiUtils {

    private final ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping(path = "/approval")
    public String getApproval(Model model) {
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


    @PostMapping(path = "/approval/approve")
    public String approveIns(@RequestParam(value = "name", required = false) String name, Model model) {
        try {
            boolean isRegistered = approvalService.approveIns(name);
            System.err.println("approve " + name);
            if (isRegistered == false) {
                return showMessageWithRedirect("보험 승인에 실패하였습니다.", "/approval", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/approval", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/approval", Method.GET, null, model);
        }
        return showMessageWithRedirect("보험 승인이 완료되었습니다.", "/approval", Method.GET, null, model);
    }

}
