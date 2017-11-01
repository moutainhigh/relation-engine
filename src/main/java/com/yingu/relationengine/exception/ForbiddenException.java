package com.yingu.relationengine.exception;

import org.springframework.http.HttpStatus;

/**
 * <B>文件名称：</B>UNAuthorizedException<BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2017/02/22<BR>
 *
 * @author
 * @version 1.0
 **/
public class ForbiddenException extends BusinessException {

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN.value(), message);
    }

}
