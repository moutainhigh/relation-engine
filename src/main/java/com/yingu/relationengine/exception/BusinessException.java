package com.yingu.relationengine.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B>业务异常<BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>16/12/9<BR>
 *
 * @author 李云龙  liyunlong@yingu.com
 * @version 1.0
 **/


//            "timestamp": 1468158273181,
//            "status": 500,
//            "error": "Internal Server Error",
//            "exception": "ServiceException",
//            "message": "sn should not be empty",
//            "path": "/employee

public class BusinessException extends RuntimeException{

    private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

    private String source;  // default UserManagement、crm
    // last add
    private Date    timestamp = new Date();
    private int     status;
    private String  error;
    private String  exception;
    private String  path;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(final int code, String message) {
        super(message);
        this.setCode(code);
    }
}
