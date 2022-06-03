package com.yeol.ocle.controller.join.join.validator;

import com.yeol.ocle.controller.join.join.dto.JoinInptDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component  //Use Dependency Injection
public class JoinInptValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return JoinInptDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        JoinInptDTO inpt = (JoinInptDTO) obj;
        
        String username = inpt.getUsername();   //사용자ID
        String password = inpt.getPassword();   //비밀번호
        String passwordCnfm = inpt.getPasswordCnfm();   //비밀번호확인
        String custNm = inpt.getCustNm();   //고객명

        //아이디 체크
        if(StringUtils.isEmpty(username)) {
            errors.rejectValue("username", "key", "이메일주소를 입력하세요.");
        }


        //비밀번호
        if(StringUtils.isEmpty(password)) {
            errors.rejectValue("password", "key", "비밀번호를 입력하세요.");
        }

        //비밀번호 확인
        if(StringUtils.isEmpty(passwordCnfm)) {
            errors.rejectValue("passwordCnfm", "key", "비밀번호확인을 입력하세요.");
        }

        //비밀번호 길이 체크 [TODO] 비밀번호 길이체크 임시 주석
//        if(password.length() < 8) {
//            errors.rejectValue("password", "key", "비밀번호를 8자 이상으로 입력해주세요.");
//        }

        if(password.length() > 20) {
            errors.rejectValue("password", "key", "비밀번호를 20자 이하로 입력해주세요.");
        }

        //비밀번호 VS 비밀번호 확인 일치 여부 검증
        if(!password.equals(passwordCnfm)) {
            errors.rejectValue("passwordCnfm", "key", "비밀번호가 일치하지 않습니다.");
        }

        //고객명
        if(StringUtils.isEmpty(custNm)) {
            errors.rejectValue("custNm", "key", "고객명을 입력하세요.");
        }
        
        //[TODO] 비밀번호 생성 규칙 설정
    }
}
