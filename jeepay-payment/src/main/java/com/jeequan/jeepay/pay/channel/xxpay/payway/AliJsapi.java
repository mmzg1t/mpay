package com.jeequan.jeepay.pay.channel.xxpay.payway;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.core.exception.BizException;
import com.jeequan.jeepay.core.model.params.xxpay.XxpayNormalMchParams;
import com.jeequan.jeepay.core.utils.AmountUtil;
import com.jeequan.jeepay.pay.channel.alipay.AlipayKit;
import com.jeequan.jeepay.pay.channel.xxpay.XxpayPaymentService;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.AliBarOrderRS;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.AliJsapiOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.AliJsapiOrderRS;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.WxBarOrderRQ;
import com.jeequan.jeepay.pay.util.ApiResBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

/*
 * 小新支付 支付宝jsapi支付
 *
 */
@Service("xxpayPaymentByAliJsapiService") //Service Name需保持全局唯一性
public class AliJsapi extends XxpayPaymentService {

    @Override
    public String preCheck(UnifiedOrderRQ rq, PayOrder payOrder) {

        AliJsapiOrderRQ bizRQ = (AliJsapiOrderRQ) rq;
        if(StringUtils.isEmpty(bizRQ.getBuyerUserId())){
            throw new BizException("[buyerUserId]不可为空");
        }

        return null;
    }

    @Override
    public AbstractRS pay(UnifiedOrderRQ rq, PayOrder payOrder, MchAppConfigContext mchAppConfigContext) throws Exception{
        AliJsapiOrderRQ bizRQ = (AliJsapiOrderRQ) rq;
        XxpayNormalMchParams params = (XxpayNormalMchParams)configContextQueryService.queryNormalMchParams(mchAppConfigContext.getMchNo(), mchAppConfigContext.getAppId(), getIfCode());
        // 构造支付请求参数
        Map<String,Object> paramMap = new TreeMap();
        paramMap.put("mchId", params.getMchId());
        paramMap.put("productId", "8008"); // 支付宝服务端支付
        paramMap.put("mchOrderNo", payOrder.getPayOrderId());
        paramMap.put("amount", payOrder.getAmount() + "");
        paramMap.put("currency", "cny");
        paramMap.put("clientIp", payOrder.getClientIp());
        paramMap.put("device", "web");
        paramMap.put("returnUrl", getReturnUrl());
        paramMap.put("notifyUrl", getNotifyUrl(payOrder.getPayOrderId()));
        paramMap.put("subject", payOrder.getSubject());
        paramMap.put("body", payOrder.getBody());
        paramMap.put("channelUserId", bizRQ.getBuyerUserId());
        // 构造函数响应数据
        AliJsapiOrderRS res = ApiResBuilder.buildSuccess(AliJsapiOrderRS.class);
        ChannelRetMsg channelRetMsg = new ChannelRetMsg();
        res.setChannelRetMsg(channelRetMsg);
        // 发起支付
        JSONObject resObj = doPay(payOrder, params, paramMap, channelRetMsg);
        if(resObj == null) {
            return res;
        }
        String alipayTradeNo = resObj.getJSONObject("payParams").getString("alipayTradeNo");
        res.setAlipayTradeNo(alipayTradeNo);
        return res;
    }

}
