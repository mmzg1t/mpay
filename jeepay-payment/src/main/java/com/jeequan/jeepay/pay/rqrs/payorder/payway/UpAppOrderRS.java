package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.CommonPayDataRS;
import lombok.Data;

/*
 * 支付方式： UPACP_APP
 *
 */
@Data
public class UpAppOrderRS extends CommonPayDataRS {

    private String payData;

    @Override
    public String buildPayDataType(){
        return CS.PAY_DATA_TYPE.YSF_APP;
    }

    @Override
    public String buildPayData(){
        return payData;
    }

}
