package com.yingu.relationengine.config;

import com.yingu.relationengine.rpc.mq.exchange.ExchangeDefinition;
import com.yingu.relationengine.rpc.mq.queues.Queues;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitConfig  implements RabbitListenerConfigurer {

    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    /**
     * 创建Exchange，建立routingkey和queue关系
     * <p>
     * 发起三方反欺诈鉴别
     * queue 队列声明 - 消息队列
     * durable:是否持久化
     * exclusive: 仅创建者可以使用的私有队列，断开后自动删除，默认为false
     * auto_delete: 当所有消费客户端连接断开后，是否自动删除队列，默认为false
     */
    @Bean
    public TopicExchange RequestChannelTopicExchange(ConnectionFactory connectionFactory) {
        //创建exchange
        TopicExchange topicExchange = new TopicExchange(ExchangeDefinition.REQUEST_CHANNEL_EXCHANGE_NAME);

        // 正常
        Queue rq = new Queue(Queues.REQUEST_CHANNEL_QUEUE, true);
        Binding rqBinding = BindingBuilder.bind(rq).to(topicExchange).with(ExchangeDefinition.REQUEST_CHANNEL_ROUTING_KEY);
        // 备份
        Queue backup = new Queue(Queues.REQUEST_CHANNEL_QUEUE_BACKUP, true);
        Binding backupBinding = BindingBuilder.bind(backup).to(topicExchange).with(ExchangeDefinition.REQUEST_CHANNEL_ROUTING_KEY);
        // error
        Queue error = new Queue(Queues.REQUEST_CHANNEL_QUEUE_ERROR, true);
        Binding errorBinding = BindingBuilder.bind(error).to(topicExchange).with(ExchangeDefinition.REQUEST_CHANNEL_ROUTING_KEY_ERROR);
        // error
        Queue unknown = new Queue(Queues.REQUEST_CHANNEL_QUEUE_UNKNOWN, true);
        Binding unknownBinding = BindingBuilder.bind(unknown).to(topicExchange).with(ExchangeDefinition.REQUEST_CHANNEL_ROUTING_KEY_UNKNOWN);

        //声明交换器、队列、绑定规则
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(topicExchange);
        rabbitAdmin.declareQueue(rq);
        rabbitAdmin.declareBinding(rqBinding);
        rabbitAdmin.declareQueue(backup);
        rabbitAdmin.declareBinding(backupBinding);
        rabbitAdmin.declareQueue(error);
        rabbitAdmin.declareBinding(errorBinding);
        rabbitAdmin.declareQueue(unknown);
        rabbitAdmin.declareBinding(unknownBinding);
        return topicExchange;
    }

    /**
     * 创建Exchange，建立routingkey和queue关系
     * <p>
     * 接收三方反欺诈鉴别
     * queue 队列声明 - 消息队列
     * durable:是否持久化
     * exclusive: 仅创建者可以使用的私有队列，断开后自动删除，默认为false
     * auto_delete: 当所有消费客户端连接断开后，是否自动删除队列，默认为false
     */
    @Bean
    public TopicExchange ResponseChannelTopicExchange(ConnectionFactory connectionFactory) {
        //创建exchange
        TopicExchange topicExchange = new TopicExchange(ExchangeDefinition.RESPONSE_CHANNEL_EXCHANGE_NAME);

        // 正常
        Queue rq = new Queue(Queues.RESPONSE_CHANNEL_QUEUE, true);
        Binding rqBinding = BindingBuilder.bind(rq).to(topicExchange).with(ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY);
        //关系引擎使用
        Queue gx = new Queue(Queues.RESPONSE_CHANNEL_QUEUE_RELATION, true);
        Binding gxBinding = BindingBuilder.bind(gx).to(topicExchange).with(ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY);
        // 备份
        Queue backup = new Queue(Queues.RESPONSE_CHANNEL_QUEUE_BACKUP, true);
        Binding backupBinding = BindingBuilder.bind(backup).to(topicExchange).with(ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY);
        // error
        Queue error = new Queue(Queues.RESPONSE_CHANNEL_QUEUE_ERROR, true);
        Binding errorBinding = BindingBuilder.bind(error).to(topicExchange).with(ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY_ERROR);
        // relation error
        Queue relationerror = new Queue(Queues.RESPONSE_CHANNEL_QUEUE_RELATION_ERROR, true);
        Binding relationerrorBinding = BindingBuilder.bind(relationerror).to(topicExchange).with(ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY_RELATION_ERROR);
        // unknown
        Queue unknown = new Queue(Queues.RESPONSE_CHANNEL_QUEUE_UNKNOWN, true);
        Binding unknownBinding = BindingBuilder.bind(unknown).to(topicExchange).with(ExchangeDefinition.RESPONSE_CHANNEL_ROUTING_KEY_UNKNOWN);

        //声明交换器、队列、绑定规则
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(topicExchange);
        rabbitAdmin.declareQueue(rq);
        rabbitAdmin.declareBinding(rqBinding);
        rabbitAdmin.declareQueue(gx);
        rabbitAdmin.declareBinding(gxBinding);
        rabbitAdmin.declareQueue(backup);
        rabbitAdmin.declareBinding(backupBinding);
        rabbitAdmin.declareQueue(error);
        rabbitAdmin.declareBinding(errorBinding);
        rabbitAdmin.declareQueue(relationerror);
        rabbitAdmin.declareBinding(relationerrorBinding);
        rabbitAdmin.declareQueue(unknown);
        rabbitAdmin.declareBinding(unknownBinding);
        return topicExchange;
    }

}
