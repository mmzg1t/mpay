package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

/*
 * 支付方式： UPACP_APP
 *
 */
@Data
public class UpAppOrderRQ extends CommonPayDataRQ {

    /** 构造函数 **/
    public UpAppOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.UP_APP);
    }

}
