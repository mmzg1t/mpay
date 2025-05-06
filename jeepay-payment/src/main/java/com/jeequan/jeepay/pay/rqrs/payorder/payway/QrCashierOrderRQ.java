package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRQ;
import lombok.Data;

/*
 * 支付方式： QR_CASHIER
 *
 */
@Data
public class QrCashierOrderRQ extends CommonPayDataRQ {

    /** 构造函数 **/
    public QrCashierOrderRQ(){
        this.setWayCode(CS.PAY_WAY_CODE.QR_CASHIER);
    }

}
