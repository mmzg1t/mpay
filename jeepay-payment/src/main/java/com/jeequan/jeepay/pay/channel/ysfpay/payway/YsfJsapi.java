package com.jeequan.jeepay.pay.channel.ysfpay.payway;

import com.alibaba.fastjson.JSONObject;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.channel.ysfpay.YsfpayPaymentService;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.YsfJsapiOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.YsfJsapiOrderRS;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.pay.util.ApiResBuilder;
import com.jeequan.jeepay.pay.model.IsvConfigContext;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/*
 * 云闪付 jsapi
 *
 */
@Service("ysfpayPaymentByJsapiService") //Service Name需保持全局唯一性
public class YsfJsapi extends YsfpayPaymentService {

    @Override
    public String preCheck(UnifiedOrderRQ rq, PayOrder payOrder) {
        return null;
    }

    @Override
    public AbstractRS pay(UnifiedOrderRQ rq, PayOrder payOrder, MchAppConfigContext mchAppConfigContext) throws Exception {
        String logPrefix = "【云闪付(unionpay)jsapi支付】";
        JSONObject reqParams = new JSONObject();
        YsfJsapiOrderRS res = ApiResBuilder.buildSuccess(YsfJsapiOrderRS.class);
        ChannelRetMsg channelRetMsg = new ChannelRetMsg();
        res.setChannelRetMsg(channelRetMsg);

        YsfJsapiOrderRQ bizRQ = (YsfJsapiOrderRQ) rq;

        // 请求参数赋值
        jsapiParamsSet(reqParams, payOrder, getNotifyUrl(), getReturnUrl());
        //云闪付扫一扫支付， 需要传入termInfo参数
        reqParams.put("termInfo", "{\"ip\": \""+StringUtils.defaultIfEmpty(payOrder.getClientIp(), "127.0.0.1")+"\"}");

        //客户端IP
        reqParams.put("customerIp", StringUtils.defaultIfEmpty(payOrder.getClientIp(), "127.0.0.1"));
        // 发送请求并返回订单状态
        JSONObject resJSON = packageParamAndReq("/gateway/api/pay/unifiedorder", reqParams, logPrefix, mchAppConfigContext);
        //请求 & 响应成功， 判断业务逻辑
        String respCode = resJSON.getString("respCode"); //应答码
        String respMsg = resJSON.getString("respMsg"); //应答信息

        try {
            //00-交易成功， 02-用户支付中 , 12-交易重复， 需要发起查询处理    其他认为失败
            if("00".equals(respCode)){
                //付款信息
                res.setPayData(resJSON.getString("payData"));
                channelRetMsg.setChannelState(ChannelRetMsg.ChannelState.WAITING);
            }else{
                channelRetMsg.setChannelState(ChannelRetMsg.ChannelState.CONFIRM_FAIL);
                channelRetMsg.setChannelErrCode(respCode);
                channelRetMsg.setChannelErrMsg(respMsg);
            }
        }catch (Exception e) {
            channelRetMsg.setChannelErrCode(respCode);
            channelRetMsg.setChannelErrMsg(respMsg);
        }
        return res;
    }

}
