package com.yingu.relationengine.rpc.mq.publish;

import com.yingu.relationengine.rpc.mq.exchange.ExchangeDefinition;
import org.springframework.stereotype.Component;

/**
 * <B>文件名称：</B><BR>
 * <B>文件描述：</B>入列<BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>16/12/22<BR>
 *
 * @version 1.0
 **/

@Component
public class ResponseChannelSender extends AbstractBaseSender {

    @Override
    void handler(String json) {
        this.rabbitTemplate.convertAndSend(ExchangeDefinition.RESPONSE_CHANNEL_EXCHANGE_NAME, ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY, json);
    }

    @Override
    void queueExceptionHandler(String json) {
        // do nothing
    }
}
