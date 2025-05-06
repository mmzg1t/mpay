package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;


@Data
public class PPPcOrderRQ extends CommonPayDataRQ {

    /**
     * 商品描述信息
     **/
    @NotBlank(message = "取消支付返回站点")
    private String cancelUrl;

    public PPPcOrderRQ() {
        this.setWayCode(CS.PAY_WAY_CODE.PP_PC);
    }
}
