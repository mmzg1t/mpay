package com.jeequan.jeepay.pay.model;

import com.jeequan.jeepay.core.entity.MchApp;
import com.jeequan.jeepay.core.entity.MchInfo;
import lombok.Data;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/*
* 商户配置信息
* 放置到内存， 避免多次查询操作
*
*/
@Data
public class MchInfoConfigContext {


    /** 商户信息缓存 */
    private String mchNo;
    private Byte mchType;
    private MchInfo mchInfo;
    private Map<String, MchApp> appMap = new ConcurrentHashMap<>();

    /** 重置商户APP **/
    public void putMchApp(MchApp mchApp){
        appMap.put(mchApp.getAppId(), mchApp);
    }

    /** get商户APP **/
    public MchApp getMchApp(String appId){
        return appMap.get(appId);
    }

}
