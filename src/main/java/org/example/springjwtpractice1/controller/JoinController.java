package org.example.springjwtpractice1.controller;

import org.example.springjwtpractice1.dto.JoinDTO;
import org.example.springjwtpractice1.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {
        System.out.println("joinDTO = " + joinDTO);
        joinService.join(joinDTO);
        return "join success";
    }
}
