package com.InsuranceSystem.Controller;

import com.InsuranceSystem.Development.Life;
import com.InsuranceSystem.Service.DevelopService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController extends UiUtils {

    private final DevelopService developService;

    public MyController(DevelopService developService) {
        this.developService = developService;
    }


    @GetMapping(path = "/main")
    public String openMain(Model model) {
        System.err.println("get main");
        return "/main";
    }

    @GetMapping(path = "/develop")
    public String openDevelop(Model model) {
        System.err.println("get develop");
        return "dev/type";
    }

    @GetMapping(path = "/develop/life")
    public String devLife(Model model) {
        model.addAttribute("life", new Life());
        System.err.println("get dev/life");
        return "dev/life";
    }

    @PostMapping(value = "/develop/registerLife")
    public String registerLife(final Life params, Model model) {
        try {
            boolean isRegistered = developService.registerLife(params);
            if (isRegistered == false) {
                return showMessageWithRedirect("생명보험 등록에 실패하였습니다.", "/develop", Method.GET, null, model);
            }
        } catch (DataAccessException e) {
            return showMessageWithRedirect("DB 처리 과정에 문제가 발생하였습니다.", "/develop", Method.GET, null, model);
        } catch (Exception e) {
            return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/develop", Method.GET, null, model);
        }
        return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/develop", Method.GET, null, model);
    }

    @GetMapping(path = "/develop/fire")
    public String devFire(Model model) {
        System.err.println("get dev/fire");
        return "dev/fire";
    }

    @GetMapping(path = "/develop/loss")
    public String devLoss(Model model) {
        System.err.println("get dev/loss");
        return "dev/loss";
    }

    @GetMapping(path = "/obtain")
    public String devObtain(Model model) {
        System.err.println("get obtain");
        return "dev/obtain";
    }

}