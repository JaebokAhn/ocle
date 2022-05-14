package com.yeol.ocle.comn.exception;

public class CodedOcleException extends OcleException{
    private static final long serialVersionUID = -7819009115311393797L;

    private String[] arguments;

    public CodedOcleException(String messageId) {
        super(messageId);
        this.arguments = new String[0];
    }

    public CodedOcleException(String messageId, String... arguments) {
        super(messageId);
        this.arguments = arguments;
    }

    public CodedOcleException(String messageId, Throwable e) {
        super(messageId, e);
        this.arguments = new String[0];
    }

    public CodedOcleException(String messageId, Throwable e, String... arguments) {
        super(messageId, e);
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return this.arguments;
    }
}
