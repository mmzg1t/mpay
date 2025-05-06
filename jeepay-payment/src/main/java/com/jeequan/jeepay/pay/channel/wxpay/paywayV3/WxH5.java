package com.jeequan.jeepay.pay.channel.wxpay.paywayV3;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.net.URLEncodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.entity.PayOrder;
import com.jeequan.jeepay.pay.channel.wxpay.WxpayPaymentService;
import com.jeequan.jeepay.pay.channel.wxpay.kits.WxpayKit;
import com.jeequan.jeepay.pay.channel.wxpay.kits.WxpayV3Util;
import com.jeequan.jeepay.pay.channel.wxpay.model.WxpayV3OrderRequestModel;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.model.WxServiceWrapper;
import com.jeequan.jeepay.pay.rqrs.AbstractRS;
import com.jeequan.jeepay.pay.rqrs.msg.ChannelRetMsg;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.WxH5OrderRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.payway.WxH5OrderRS;
import com.jeequan.jeepay.pay.util.ApiResBuilder;
import org.springframework.stereotype.Service;

/*
 * 微信 H5支付
 *
 */
@Service("wxpayPaymentByH5V3Service") //Service Name需保持全局唯一性
public class WxH5 extends WxpayPaymentService {

    @Override
    public String preCheck(UnifiedOrderRQ rq, PayOrder payOrder) {
        return null;
    }

    @Override
    public AbstractRS pay(UnifiedOrderRQ rq, PayOrder payOrder, MchAppConfigContext mchAppConfigContext) {

        WxH5OrderRQ bizRQ = (WxH5OrderRQ) rq;

        WxServiceWrapper wxServiceWrapper = configContextQueryService.getWxServiceWrapper(mchAppConfigContext);
        WxPayService wxPayService = wxServiceWrapper.getWxPayService();

        // 构造请求数据
        WxpayV3OrderRequestModel wxpayV3OrderRequestModel = buildV3OrderRequestModel(payOrder, mchAppConfigContext);

        // 场景信息
        wxpayV3OrderRequestModel.getSceneInfo().setH5Info(new WxpayV3OrderRequestModel.SceneInfo.H5Info().setType("iOS, Android, Wap"));

        // 构造函数响应数据
        WxH5OrderRS res = ApiResBuilder.buildSuccess(WxH5OrderRS.class);
        ChannelRetMsg channelRetMsg = new ChannelRetMsg();
        res.setChannelRetMsg(channelRetMsg);

        // 调起上游接口：
        try {
            String payInfo = WxpayV3Util.commonReqWx(wxpayV3OrderRequestModel, wxPayService, mchAppConfigContext.isIsvsubMch(), WxPayConstants.TradeType.MWEB, null);

            JSONObject resJSON = JSONObject.parseObject(payInfo);

            // 拼接returnUrl
            String payUrl = String.format("%s&redirect_url=%s", resJSON.getString("h5_url"), URLEncodeUtil.encode(getReturnUrlOnlyJump(payOrder.getPayOrderId())));

            payUrl = String.format("%s/api/common/payUrl/%s", sysConfigService.getDBApplicationConfig().getPaySiteUrl(), Base64.encode(payUrl));

            if (CS.PAY_DATA_TYPE.CODE_IMG_URL.equals(bizRQ.getPayDataType())){ //二维码图片地址
                res.setCodeImgUrl(sysConfigService.getDBApplicationConfig().genScanImgUrl(payUrl));
            }else{ // 默认都为 payUrl方式
                res.setPayUrl(payUrl);
            }

            // 支付中
            channelRetMsg.setChannelState(ChannelRetMsg.ChannelState.WAITING);

        } catch (WxPayException e) {
            //明确失败
            channelRetMsg.setChannelState(ChannelRetMsg.ChannelState.CONFIRM_FAIL);
            WxpayKit.commonSetErrInfo(channelRetMsg, e);
        }

        return res;
    }

}
