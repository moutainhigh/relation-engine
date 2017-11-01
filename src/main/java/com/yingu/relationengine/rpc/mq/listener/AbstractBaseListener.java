package com.yingu.relationengine.rpc.mq.listener;


import com.yingu.relationengine.exception.BusinessException;
import com.yingu.relationengine.rpc.mq.publish.IBaseSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <B>文件名称：</B>AbstractBaseListener<BR>
 * <B>文件描述：</B><BR>
 * <BR>
 * <B>版权声明：</B>(C)2016-2018<BR>
 * <B>公司部门：</B>东方银谷 研发二部 CBG<BR>
 * <B>创建时间：</B>2016/12/26<BR>
 *
 * @version 1.0
 **/
public abstract class AbstractBaseListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseListener.class);

    private static final String ERROR_COUNT = "errorCount";

    public void process(String object, IBaseSender sender, IBaseSender errorSender) {
        try {
            handler(object, sender, errorSender);
        } catch (Exception e) {
            LOGGER.error("RabbitMQListener:" + e.getMessage());
            if (errorSender != null) {
                errorSender.send(object);
            }
        }
    }

    private void handler(String json, IBaseSender sender, IBaseSender errorSender){
        if (json == null) {
            return;
        }

        boolean sended = execute(json);
        if (!sended) {
            //自增并保存errorCount
            //重新放回队列
            throw new BusinessException("放到ERROR队列-{}" + json);
        }
    }

    public abstract boolean execute(String json);
}
