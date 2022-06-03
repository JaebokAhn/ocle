package com.yeol.ocle.controller.api.admin;

import com.yeol.ocle.controller.GenericApiController;
import com.yeol.ocle.controller.admin.dto.IntgCodeDTO;
import com.yeol.ocle.model.intgcode.IntgCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> saveIntgCode(@RequestBody IntgCodeDTO intgCode) {

        return new ResponseEntity(intgCode, HttpStatus.CREATED);
    }
}
