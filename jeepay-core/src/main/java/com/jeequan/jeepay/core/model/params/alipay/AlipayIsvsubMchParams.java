package com.jeequan.jeepay.core.model.params.alipay;

import com.jeequan.jeepay.core.model.params.IsvsubMchParams;
import lombok.Data;

/*
 * 支付宝 特约商户参数定义
 *
 */
@Data
public class AlipayIsvsubMchParams  extends IsvsubMchParams {

    private String appAuthToken;


}
