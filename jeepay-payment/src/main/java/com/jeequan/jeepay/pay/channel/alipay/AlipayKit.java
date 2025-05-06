package com.jeequan.jeepay.pay.channel.alipay;

import cn.hutool.core.text.CharSequenceUtil;
import com.alipay.api.AlipayObject;
import com.alipay.api.AlipayRequest;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.model.params.alipay.AlipayIsvParams;
import com.jeequan.jeepay.core.model.params.alipay.AlipayIsvsubMchParams;
import com.jeequan.jeepay.core.utils.SpringBeansUtil;
import com.jeequan.jeepay.pay.model.MchAppConfigContext;
import com.jeequan.jeepay.pay.service.ConfigContextQueryService;
import org.apache.commons.lang3.StringUtils;

/*
* 【支付宝】支付通道工具包
*
*/
public class AlipayKit {


    /** 放置 isv特殊信息 **/
    public static void putApiIsvInfo(MchAppConfigContext mchAppConfigContext, AlipayRequest req, AlipayObject model){

        //不是特约商户， 无需放置此值
        if(!mchAppConfigContext.isIsvsubMch()){
            return ;
        }

        ConfigContextQueryService configContextQueryService = SpringBeansUtil.getBean(ConfigContextQueryService.class);

        // 获取支付参数
        AlipayIsvParams isvParams = (AlipayIsvParams)configContextQueryService.queryIsvParams(mchAppConfigContext.getMchInfo().getIsvNo(), CS.IF_CODE.ALIPAY);
        AlipayIsvsubMchParams isvsubMchParams = (AlipayIsvsubMchParams)configContextQueryService.queryIsvsubMchParams(mchAppConfigContext.getMchNo(), mchAppConfigContext.getAppId(), CS.IF_CODE.ALIPAY);

        // 子商户信息
        if(req instanceof AlipayTradePayRequest) {
            ((AlipayTradePayRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradeAppPayRequest) {
            ((AlipayTradeAppPayRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradeCreateRequest) {
            ((AlipayTradeCreateRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradePagePayRequest) {
            ((AlipayTradePagePayRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradePrecreateRequest) {
            ((AlipayTradePrecreateRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradeWapPayRequest) {
            ((AlipayTradeWapPayRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradeQueryRequest) {
            ((AlipayTradeQueryRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradeRefundRequest) {
            ((AlipayTradeRefundRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayTradeFastpayRefundQueryRequest) {
            ((AlipayTradeFastpayRefundQueryRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof AlipayFundTransToaccountTransferRequest) {
            ((AlipayFundTransToaccountTransferRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof  AlipayTradeRoyaltyRelationBindRequest) {
            ((AlipayTradeRoyaltyRelationBindRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof  AlipayTradeOrderSettleRequest) {
            ((AlipayTradeOrderSettleRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof  AlipayTradeCloseRequest) {
            ((AlipayTradeCloseRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        } else if(req instanceof  AlipayTradeOrderSettleQueryRequest) {
            ((AlipayTradeOrderSettleQueryRequest)req).putOtherTextParam("app_auth_token", isvsubMchParams.getAppAuthToken());
        }


        // 服务商信息
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId(isvParams.getPid());

        if(model instanceof AlipayTradePayModel) {
            ((AlipayTradePayModel)model).setExtendParams(extendParams);
        } else if(model instanceof AlipayTradeAppPayModel) {
            ((AlipayTradeAppPayModel)model).setExtendParams(extendParams);
        } else if(model instanceof AlipayTradeCreateModel) {
            ((AlipayTradeCreateModel)model).setExtendParams(extendParams);
        } else if(model instanceof AlipayTradePagePayModel) {
            ((AlipayTradePagePayModel)model).setExtendParams(extendParams);
        } else if(model instanceof AlipayTradePrecreateModel) {
            ((AlipayTradePrecreateModel)model).setExtendParams(extendParams);
        } else if(model instanceof AlipayTradeWapPayModel) {
            ((AlipayTradeWapPayModel)model).setExtendParams(extendParams);
        }
    }


    public static String appendErrCode(String code, String subCode){
        return StringUtils.defaultIfEmpty(subCode, code); //优先： subCode
    }

    public static String appendErrMsg(String msg, String subMsg){

        String result = null;
        if(StringUtils.isNotEmpty(msg) && StringUtils.isNotEmpty(subMsg) ){
            result = msg + "【" + subMsg + "】";
        }else{
            result = StringUtils.defaultIfEmpty(subMsg, msg);
        }
        return CharSequenceUtil.maxLength(result, 253);
    }

}
