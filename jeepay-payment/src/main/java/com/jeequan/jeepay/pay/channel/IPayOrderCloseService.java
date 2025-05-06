package com.jeequan.jeepay.pay.channel;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;

/**
 * 关闭订单（渠道侧）接口定义
 *
 */
public interface IPayOrderCloseService {

    /** 获取到接口code **/
    String getIfCode();

    /** 查询订单 **/
    ChannelRetMsg close(PayOrder payOrder, MchAppConfigContext mchAppConfigContext) throws Exception;

}
