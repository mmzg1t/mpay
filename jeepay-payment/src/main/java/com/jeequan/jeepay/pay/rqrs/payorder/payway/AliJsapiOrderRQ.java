package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/*
 * 支付方式： ALI_JSAPI
 *
 */
@Data
public class AliJsapiOrderRQ extends UnifiedOrderRQ {

    /** 支付宝用户ID **/
    @NotBlank(message = "用户ID不能为空")
    private String buyerUserId;

    /** 构造函数 **/
    public AliJsapiOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.ALI_JSAPI);
    }

    @Override
    public String getChannelUserId(){
        return this.buyerUserId;
    }

}
