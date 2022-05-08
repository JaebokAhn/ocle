package com.yeol.ocle.controller.join;

import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.model.userinfo.User;
import com.yeol.ocle.repository.usermgmt.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main/join")
@Slf4j
public class JoinController {

    /**
     * 사용자 repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * 비밀번호 암호화
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final String preFix = "main/join/";
    private final String postFix = "";

    private final String getViewName(String pageName){
        return preFix + pageName + postFix;
    }

    /**
     * 회원가입 폼
     * @param
     * @return
     */
    @GetMapping("/join")
    public String joinForm(){
        return this.getViewName("joinForm");
    }

    @PostMapping("/join")
    public String joinSubmit(User user){
        user.setRole(OcleConst.MEMB_ROLE_DVSN_CODE_CNSM);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info(user.toString());
        userRepository.save(user);
        return "redirect:/main/login/login";
    }
}
