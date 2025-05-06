package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

/*
 * 支付方式： UPACP_PC
 *
 */
@Data
public class UpPcOrderRQ extends CommonPayDataRQ {

    /** 构造函数 **/
    public UpPcOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.UP_PC);
    }

}
