package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

/**
 * 支付方式 订单码
 *
 */
@Data
public class AliOcOrderRQ extends CommonPayDataRQ {

    public AliOcOrderRQ() {
        this.setWayCode(CS.PAY_WAY_CODE.ALI_OC);
    }
}
