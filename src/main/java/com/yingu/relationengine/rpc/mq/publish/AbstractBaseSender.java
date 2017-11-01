package com.yingu.relationengine.rpc.mq.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <B>文件名称：</B>AbstractBaseSender<BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2016/12/27<BR>
 *
 * @version 1.0
 **/
public abstract class AbstractBaseSender implements IBaseSender {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseSender.class);

    static int SCALE = 16;


    @Autowired
    AmqpTemplate rabbitTemplate;


    @Override
    public void send(String json) {
        execute(json);
    }

    void execute(String json) {
        try {
            handler(json);
        } catch (Exception e) {
            LOGGER.error("AbstractBaseSender:{}----{}", e.getMessage(), json );
            queueExceptionHandler(json);
        }
    }

    abstract void handler(String map);
    abstract void queueExceptionHandler(String json);
}
