package com.yeol.ocle.controller.api.admin;

import com.yeol.ocle.comn.cmdto.ComnMsgeDTO;
import com.yeol.ocle.comn.utils.BeanCopyUtils;
import com.yeol.ocle.comn.utils.OcleUtils;
import com.yeol.ocle.controller.GenericApiController;
import com.yeol.ocle.controller.admin.dto.IntgCodeDTO;
import com.yeol.ocle.controller.api.admin.dto.IntgCodeApiDTO;
import com.yeol.ocle.model.intgcode.IntgCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/admin/intgcode")
public class IntgCodeMgmtAPI extends GenericApiController<IntgCode, String> {
    /**
     * 통합코드저장
     * @param intgCode
     * @return
     */
    @PostMapping("/IntnCodeMgmtAPI001c")
    public ResponseEntity<?> saveIntgCode(@Valid @RequestBody IntgCodeApiDTO intgCode,
                                          BindingResult bindingResult) {

        //참고) @RequestBody는 Validator를 사용할 수 없음.
        ComnMsgeDTO comnMsge = OcleUtils.convertBindingResultToComnMsge(bindingResult);

        return new ResponseEntity(comnMsge, HttpStatus.CREATED);
    }
}
