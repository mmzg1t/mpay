package com.jeequan.jeepay.core.model.params.pppay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jeequan.jeepay.core.model.params.NormalMchParams;
import com.jeequan.jeepay.core.utils.StringKit;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class PppayNormalMchParams extends NormalMchParams {
    /**
     * 是否沙箱环境
     */
    private Byte sandbox;

    /**
     * clientId
     * 客户端 ID
     */
    private String clientId;

    /**
     * secret
     * 密钥
     */
    private String secret;

    /**
     * 支付 Webhook 通知 ID
     */
    private String notifyWebhook;

    /**
     * 退款 Webhook 通知 ID
     */
    private String refundWebhook;

    @Override
    public String deSenData() {
        PppayNormalMchParams mchParams = this;
        if (StringUtils.isNotBlank(this.secret)) {
            mchParams.setSecret(StringKit.str2Star(this.secret, 6, 6, 6));
        }
        return ((JSONObject) JSON.toJSON(mchParams)).toJSONString();
    }
}
