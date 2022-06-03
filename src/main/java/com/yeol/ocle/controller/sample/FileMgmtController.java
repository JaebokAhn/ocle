package com.yeol.ocle.controller.sample;

import com.yeol.ocle.comn.consts.OcleConst;
import com.yeol.ocle.comn.exception.BizOcleException;
import com.yeol.ocle.comn.message.MessageService;
import com.yeol.ocle.service.aws.s3.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/sample/fileupload")
public class FileMgmtController {
    private final String preFix = "/sample/fileupload/";
    private final String postFix = "";

    private final String getViewName(String pageName){
        return this.preFix + pageName + this.postFix;
    }

    /**
     * 메시지서비스
     */
    @Autowired
    private MessageService messageService;

    /**
     * AWS S3(파일서버) 서비스
     */
    @Autowired
    private S3Service s3Service;

    /**
     * 파일업로드 form
     * @return
     */
    @GetMapping("/fileupload")
    public String fileUploadForm(){
        log.info("FileMgmtController.fileUploadForm START");
        return this.getViewName("fileupload");
    }

    /**
     * 파일업로드 Submit
     * @return
     */
    @PostMapping("/fileupload")
    public String fileUploadSubmit(@RequestParam("file")MultipartFile file, Model model){

        try {

            /** S3Service.upload 호출 */
            String fileUrl = s3Service.upload(file);

            model.addAttribute("fileUrl", fileUrl);

        } catch (BizOcleException e) {
            model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_E);
            model.addAttribute("msgeCode", e.getMessageId());
            model.addAttribute("msgeCntn", messageService.getMessage(e.getMessageId(), e.getArguments()));
            return this.getViewName("fileupload");
        } catch (Exception e) {
            e.printStackTrace();    //[TODO] 삭제 필요

            model.addAttribute("prcsRsltCode", OcleConst.PRCS_RSLT_CODE_E);
            model.addAttribute("msgeCode", OcleConst.MSGE_CODE_SYSE0001);
            model.addAttribute("msgeCntn", messageService.getMessage(OcleConst.MSGE_CODE_SYSE0001));
            return this.getViewName("fileupload");
        }

        return this.getViewName("fileupload");
    }
}
