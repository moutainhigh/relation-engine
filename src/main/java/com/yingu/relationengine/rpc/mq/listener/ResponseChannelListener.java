package com.yingu.relationengine.rpc.mq.listener;

import com.yingu.relationengine.rpc.mq.channel.ResponseThreeParty;
import com.yingu.relationengine.rpc.mq.publish.ResponseChannelErrorSender;
import com.yingu.relationengine.rpc.mq.publish.ResponseChannelSender;
import com.yingu.relationengine.rpc.mq.queues.Queues;
import com.yingu.relationengine.service.RelationService;
import com.yingu.relationengine.utils.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B>监听<BR>
 * <BR>
 *
 *     信审反馈回来的数据
 *     1、补单
 *     2、拒绝
 *     3、通过
 *
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>16/12/22<BR>
 *
 *
 * @version 1.0
 **/

@Component

public class ResponseChannelListener extends AbstractBaseListener {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    ResponseChannelSender responseChannelSender;
    @Autowired
    ResponseChannelErrorSender responseChannelErrorSender;

    @Autowired
    RelationService relationService;

    @RabbitListener(queues = Queues.RESPONSE_CHANNEL_QUEUE_RELATION, containerFactory="rabbitListenerContainerFactory")
    public void process(@Payload String json) {
        String str = new String(json);
        LOGGER.info("receive :{}",str);
        super.process(str, responseChannelSender, responseChannelErrorSender);
    }


    /**
     * 三方反馈结果
     * 1、校验数据
     *      如果解析异常，放入 unknown
     *      如果查询没有这个用户等异常，放入 error
     *      否则继续
     * 1、查询反馈结果数据
     * @param json
     * @return
     */
    @Override
    public boolean execute(String json) {
        LOGGER.info("receive execute :{}",json);
        ResponseThreeParty body = new ResponseThreeParty();
        try {
            body = JsonMapper.nonDefaultMapper().fromJson(json, ResponseThreeParty.class);
        }catch (Exception e){
            LOGGER.error("解析异常{}-{}", json, e.getMessage());
            responseChannelErrorSender.send(json);
            return true;
        }
        // 处置三方返回信息
        relationService.dispose(body);
        return true;
    }

}
