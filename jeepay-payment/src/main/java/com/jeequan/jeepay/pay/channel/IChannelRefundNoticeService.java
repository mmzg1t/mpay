package com.jeequan.jeepay.pay.channel;

import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.core.entity.RefundOrder;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

/*
* 渠道侧的退款订单通知解析实现 【分为同步跳转（doReturn）和异步回调(doNotify) 】
*
*/
public interface IChannelRefundNoticeService {

    /** 通知类型 **/
    enum NoticeTypeEnum {
        DO_NOTIFY //异步回调
    }

    /** 获取到接口code **/
    String getIfCode();

    /** 解析参数： 订单号 和 请求参数
     *  异常需要自行捕捉，并返回null , 表示已响应数据。
     * **/
    MutablePair<String, Object> parseParams(HttpServletRequest request, String urlOrderId, NoticeTypeEnum noticeTypeEnum);

    /** 返回需要更新的订单状态 和响应数据 **/
    ChannelRetMsg doNotice(HttpServletRequest request,
                           Object params, RefundOrder refundOrder, MchAppConfigContext mchAppConfigContext, NoticeTypeEnum noticeTypeEnum);

    /** 数据库订单 状态更新异常 (仅异步通知使用) **/
    ResponseEntity doNotifyOrderStateUpdateFail(HttpServletRequest request);

    /** 数据库订单数据不存在  (仅异步通知使用) **/
    ResponseEntity doNotifyOrderNotExists(HttpServletRequest request);

}
