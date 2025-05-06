package com.jeequan.jeepay.pay.channel;


import com.jeequan.jeepay.pay.service.ConfigContextQueryService;
import com.jeequan.jeepay.pay.util.ChannelCertConfigKitBean;
import com.jeequan.jeepay.service.impl.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;

/*
* 退款接口抽象类
*
*/
public abstract class AbstractRefundService implements IRefundService{

    @Autowired protected SysConfigService sysConfigService;
    @Autowired protected ChannelCertConfigKitBean channelCertConfigKitBean;
    @Autowired protected ConfigContextQueryService configContextQueryService;

    protected String getNotifyUrl(){
        return sysConfigService.getDBApplicationConfig().getPaySiteUrl() + "/api/refund/notify/" + getIfCode();
    }

    protected String getNotifyUrl(String refundOrderId){
        return sysConfigService.getDBApplicationConfig().getPaySiteUrl() + "/api/refund/notify/" + getIfCode() + "/" + refundOrderId;
    }

}
