package com.jeequan.jeepay.core.model.params.ysf;

import com.jeequan.jeepay.core.model.params.IsvsubMchParams;
import lombok.Data;

/*
 * 云闪付 配置信息
 *
 */
@Data
public class YsfpayIsvsubMchParams extends IsvsubMchParams {

    private String merId;   // 商户编号

}
