package com.yeol.ocle.comn.exception;

import com.yeol.ocle.comn.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

public class BizOcleException extends CodedOcleException{
    private static final long serialVersionUID = 8824349327393922245L;

    protected int msgeOtptDvsnCode;
    protected String messageId;

    public int getMsgeOtptDvsnCode() {
        return this.msgeOtptDvsnCode;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public BizOcleException(String messageId) {
        super(messageId);
        this.msgeOtptDvsnCode = 0;
        this.messageId = messageId;
    }


    public BizOcleException(String messageId, String... arguments) {
        this(messageId, 0, (String[])arguments);
    }



    public BizOcleException(String messageId, int msgeOtptDvsnCode, String... arguments) {
        super(messageId, arguments);
        this.msgeOtptDvsnCode = msgeOtptDvsnCode;
        this.messageId = messageId;
    }

    public BizOcleException(String messageId, Throwable e) {
        this(messageId, 0, (Throwable)e);
    }



    public BizOcleException(String messageId, int msgeOtptDvsnCode, Throwable e) {
        super(messageId, e);
        this.msgeOtptDvsnCode = msgeOtptDvsnCode;
        this.messageId = messageId;
    }

    public BizOcleException(String messageId, Throwable e, String... arguments) {
        this(messageId, 0, e, arguments);
    }

    public BizOcleException(String messageId, int msgeOtptDvsnCode, Throwable e, String... arguments) {
        super(messageId, e, arguments);
        this.msgeOtptDvsnCode = msgeOtptDvsnCode;
        this.messageId = messageId;
    }
}
