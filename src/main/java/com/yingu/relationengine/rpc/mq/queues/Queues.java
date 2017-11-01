package com.yingu.relationengine.rpc.mq.queues;

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


public class Queues {

    public static final String REQUEST_CHANNEL_QUEUE                = "request.channel.queue";
    public static final String REQUEST_CHANNEL_QUEUE_ERROR          = "request.channel.queue.error";
    public static final String REQUEST_CHANNEL_QUEUE_UNKNOWN        = "request.channel.queue.unknown";
    public static final String REQUEST_CHANNEL_QUEUE_BACKUP         = "request.channel.queue.backup";


    public static final String RESPONSE_CHANNEL_QUEUE                = "response.channel.queue";
    public static final String RESPONSE_CHANNEL_QUEUE_RELATION       = "response.channel.queue.relation";
    public static final String RESPONSE_CHANNEL_QUEUE_RELATION_ERROR = "response.channel.queue.relation.error";
    public static final String RESPONSE_CHANNEL_QUEUE_ERROR          = "response.channel.queue.error";
    public static final String RESPONSE_CHANNEL_QUEUE_UNKNOWN        = "response.channel.queue.unknown";
    public static final String RESPONSE_CHANNEL_QUEUE_BACKUP         = "response.channel.queue.backup";


}
