package com.yeol.ocle.service.usermgmt.join;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.exception.OcleException;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.model.userinfo.User;
import com.yeol.ocle.repository.usermgmt.UserRepository;
import com.yeol.ocle.service.usermgmt.join.dto.JoinServiceInptDTO;
import com.yeol.ocle.service.usermgmt.join.dto.JoinServiceRsltDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JoinService {
    /**
     * 사용자 Repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * 비밀번호 암호화
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public JoinServiceRsltDTO join(JoinServiceInptDTO joinServiceInpt) {
        JoinServiceRsltDTO joinServiceRslt = null;

        log.info("JoinService.join START");

        String username = joinServiceInpt.getUsername();    //사용자ID
        String pasword = joinServiceInpt.getPassword(); //비밀번호

        //역할 (default : ROLE_CONSUMER(소비자) )
        String role = OcleUtils.nvlToString(joinServiceInpt.getRole(), OcleConst.USER_ROLE_DVSN_CODE_CNSM);
        String custNm = joinServiceInpt.getCustNm();    //고객명

        //가입방법구분코드 (default : 1 (이메일) )
        String joinMthdDvsnCode = OcleUtils.nvlToString(joinServiceInpt.getJoinMthdDvsnCode(), OcleConst.JOIN_MTHD_DVSN_CODE_1);

        //사용자권한등급코드 (default : 001 (일반사용자) )
        String userAtrtGradCode = OcleUtils.nvlToString(joinServiceInpt.getUserAtrtGradCode(), OcleConst.USER_ATRT_GRAD_CODE_001);

        User isJoinUser = userRepository.findByUsernameAndDltnYn(username, OcleConst.GENERAL_N);

        if(isJoinUser != null) {
            /* 이미 가입 된 아이디 입니다.*/
            throw new BizOcleException("COME0002");
        }

        User user = new User();
        user.setUsername(username); //사용자ID
        user.setPassword(passwordEncoder.encode(pasword));  //비밀번호 (암호화)
        user.setRole(role); //역할
        user.setCustNm(custNm); //고객명
        user.setJoinMthdDvsnCode(joinMthdDvsnCode); //가입방법구분코드
        user.setUserAtrtGradCode(userAtrtGradCode); //사용자권한등급코드

        //공통
        user.setRgsrId(username);   //등록자ID
        user.setLastChnrId(username);   //최종변경자ID
        user.setDltnYn(OcleConst.GENERAL_N);    //삭제여부

        userRepository.save(user);

        joinServiceRslt = new JoinServiceRsltDTO();
        ComnMsgeDTO comnMsge = new ComnMsgeDTO();
        comnMsge.setPrcsRsltCode(OcleConst.PRCS_RSLT_CODE_O);
        comnMsge.setMsgeCntn("회원 가입이 완료되었습니다.");

        joinServiceRslt.setComnMsge(comnMsge);

        log.info("JoinService.join END");
        return joinServiceRslt;
    }
}
