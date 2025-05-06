package com.jeequan.jeepay.core.service;

import com.jeequan.jeepay.core.entity.PayOrder;

/***
* 码牌相关逻辑
*/
public interface IMchQrcodeManager {

    /**
     * 功能描述: 查询商户配置信息
     *
     * @Return: com.jeequan.jeepay.core.entity.PayOrder
     */
    PayOrder queryMchInfoByQrc(String id);

}
