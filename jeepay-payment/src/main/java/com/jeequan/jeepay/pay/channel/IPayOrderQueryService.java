package com.jeequan.jeepay.pay.channel;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;

/**
* 查单（渠道侧）接口定义
*
*/
public interface IPayOrderQueryService {

    /** 获取到接口code **/
    String getIfCode();

    /** 查询订单 **/
    ChannelRetMsg query(PayOrder payOrder, MchAppConfigContext mchAppConfigContext) throws Exception;

}
