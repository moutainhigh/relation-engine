package com.yingu.relationengine.rpc.mq.channel;

import java.util.List;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2017/8/20<BR>
 *
 * @author 李云龙  liyunlong@yingu.com
 * @version 1.0
 **/


public class ResponseThreeParty {
    private String globalId;
    private String status;//100:成功,
    private String message;
    private List<ThirdPartyResponse> data;

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ThirdPartyResponse> getData() {
        return data;
    }

    public void setData(List<ThirdPartyResponse> data) {
        this.data = data;
    }
}
