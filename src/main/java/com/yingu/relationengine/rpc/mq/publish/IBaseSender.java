package com.yingu.relationengine.rpc.mq.publish;

/**
 * <B>文件名称：</B>IBaseSender<BR>
 * <B>文件描述：</B>发送服务<BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2016/12/26<BR>
 *
 * @author 吕宏业  lvhongye@yingu.com
 * @version 1.0
 **/
public interface IBaseSender {
    public void send(String json);
}
