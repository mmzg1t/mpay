package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/*
 * 支付方式： YSF_BAR
 *
 */
@Data
public class YsfBarOrderRQ extends UnifiedOrderRQ {

    /** 用户 支付条码 **/
    @NotBlank(message = "支付条码不能为空")
    private String authCode;

    /** 构造函数 **/
    public YsfBarOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.YSF_BAR);
    }

}
