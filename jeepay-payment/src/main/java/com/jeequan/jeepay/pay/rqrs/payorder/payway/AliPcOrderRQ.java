package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRQ;
import lombok.Data;

/*
 * 支付方式： ALI_PC
 *
 */
@Data
public class AliPcOrderRQ extends CommonPayDataRQ {

    /** 构造函数 **/
    public AliPcOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.ALI_PC);
    }

}
