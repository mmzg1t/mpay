package com.jeequan.jeepay.pay.rqrs.payorder.payway;

import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.pay.rqrs.payorder.UnifiedOrderRS;
import lombok.Data;

/*
 * 支付方式： ALI_BAR
 *
 */
@Data
public class AliBarOrderRS extends UnifiedOrderRS {

    @Override
    public String buildPayDataType(){
        return CS.PAY_DATA_TYPE.NONE;
    }

    @Override
    public String buildPayData(){
        return "";
    }

}
