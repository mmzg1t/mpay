package com.jeequan.jeepay.components.mq.vender;

import com.jeequan.jeepay.components.mq.model.AbstractMQ;

/**
* MQ 消息发送器 接口定义
*
*/
public interface IMQSender {

    /** 推送MQ消息， 实时 **/
    void send(AbstractMQ mqModel);

    /** 推送MQ消息， 延迟接收，单位：s **/
    void send(AbstractMQ mqModel, int delay);

}
