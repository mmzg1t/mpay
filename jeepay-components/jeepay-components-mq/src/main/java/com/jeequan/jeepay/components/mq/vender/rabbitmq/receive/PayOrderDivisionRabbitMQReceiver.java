package com.jeequan.jeepay.components.mq.vender.rabbitmq.receive;

import com.jeequan.jeepay.components.mq.constant.MQVenderCS;
import com.jeequan.jeepay.components.mq.model.PayOrderDivisionMQ;
import com.jeequan.jeepay.components.mq.vender.IMQMsgReceiver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * rabbitMQ消息接收器：仅在vender=rabbitMQ时 && 项目实现IMQReceiver接口时 进行实例化
 * 业务：  支付订单分账通知
 *
 */
@Component
@ConditionalOnProperty(name = MQVenderCS.YML_VENDER_KEY, havingValue = MQVenderCS.RABBIT_MQ)
@ConditionalOnBean(PayOrderDivisionMQ.IMQReceiver.class)
public class PayOrderDivisionRabbitMQReceiver implements IMQMsgReceiver {

    @Autowired
    private PayOrderDivisionMQ.IMQReceiver mqReceiver;

    /** 接收 【 queue 】 类型的消息 **/
    @Override
    @RabbitListener(queues = PayOrderDivisionMQ.MQ_NAME)
    public void receiveMsg(String msg){
        mqReceiver.receive(PayOrderDivisionMQ.parse(msg));
    }

}
