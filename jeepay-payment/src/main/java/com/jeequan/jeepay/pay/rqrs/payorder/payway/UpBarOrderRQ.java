package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/*
 * 支付方式： UPACP_BAR
 *
 */
@Data
public class UpBarOrderRQ extends CommonPayDataRQ {

    /** 用户 支付条码 **/
    @NotBlank(message = "支付条码不能为空")
    private String authCode;

    /** 构造函数 **/
    public UpBarOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.UP_BAR);
    }

}
