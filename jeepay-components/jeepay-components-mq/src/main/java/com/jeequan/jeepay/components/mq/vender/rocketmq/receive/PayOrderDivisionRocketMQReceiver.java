package com.jeequan.jeepay.components.mq.vender.rocketmq.receive;

import com.jeequan.jeepay.components.mq.constant.MQVenderCS;
import com.jeequan.jeepay.components.mq.model.PayOrderDivisionMQ;
import com.jeequan.jeepay.components.mq.vender.IMQMsgReceiver;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * rocketMQ消息接收器：仅在vender=rocketMQ时 && 项目实现IMQReceiver接口时 进行实例化
 * 业务：  支付订单分账通知
 *
 */
@Component
@ConditionalOnProperty(name = MQVenderCS.YML_VENDER_KEY, havingValue = MQVenderCS.ROCKET_MQ)
@ConditionalOnBean(PayOrderDivisionMQ.IMQReceiver.class)
@RocketMQMessageListener(topic = PayOrderDivisionMQ.MQ_NAME, consumerGroup = PayOrderDivisionMQ.MQ_NAME)
public class PayOrderDivisionRocketMQReceiver implements IMQMsgReceiver, RocketMQListener<String> {

    @Autowired
    private PayOrderDivisionMQ.IMQReceiver mqReceiver;

    /** 接收 【 queue 】 类型的消息 **/
    @Override
    public void receiveMsg(String msg){
        mqReceiver.receive(PayOrderDivisionMQ.parse(msg));
    }

    @Override
    public void onMessage(String message) {
        this.receiveMsg(message);
    }

}
