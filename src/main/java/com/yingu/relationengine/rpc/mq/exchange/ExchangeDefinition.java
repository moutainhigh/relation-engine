package com.yingu.relationengine.rpc.mq.exchange;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B>队列名称<BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>16/12/8<BR>
 *
 * @author 李云龙  liyunlong@yingu.com
 * @version 1.0
 **/


public class ExchangeDefinition {

    public static final String REQUEST_CHANNEL_EXCHANGE_NAME            = "request.channel.exchange";
    public static final String REQUEST_CHANNEL_ROUTING_KEY              = "routing-key:request-channel";
    public static final String REQUEST_CHANNEL_ROUTING_KEY_ERROR        = "routing-key-error:request-channel";
    public static final String REQUEST_CHANNEL_ROUTING_KEY_UNKNOWN      = "routing-key-unknown:request-channel";

    public static final String RESPONSE_CHANNEL_EXCHANGE_NAME           = "response.channel.exchange";
    public static final String RESPONSE_CHANNEL_ROUTING_KEY             = "routing-key:response-channel";
    public static final String RESPONSE_CHANNEL_ROUTING_KEY_ERROR       = "routing-key-error:response-channel";
    public static final String RESPONSE_CHANNEL_ROUTING_KEY_RELATION_ERROR       = "routing-key-relation-error:response-channel";
    public static final String RESPONSE_CHANNEL_ROUTING_KEY_UNKNOWN     = "routing-key-unknown:response-channel";


}
