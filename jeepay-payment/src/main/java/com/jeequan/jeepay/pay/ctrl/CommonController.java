package com.jeequan.jeepay.pay.ctrl;

import cn.hutool.core.codec.Base64;
import com.jeequan.jeepay.core.ctrls.AbstractCtrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/*
* 通用处理
*
*/
@Slf4j
@Controller
@RequestMapping("/api/common")
public class CommonController extends AbstractCtrl {

    /**
     * 跳转到支付页面(适合网关支付form表单输出)
     * @param payData
     * @return
     */
    @RequestMapping(value = "/payForm/{payData}")
    private String toPayForm(@PathVariable("payData") String payData){
        request.setAttribute("payHtml", Base64.decodeStr(payData));
        return "common/toPay";
    }

    /**
     * 跳转到支付页面(适合微信H5跳转与referer一致)
     * @param payData
     * @return
     */
    @RequestMapping(value = "/payUrl/{payData}")
    private String toPayUrl(@PathVariable("payData") String payData) {
        String payUrl = Base64.decodeStr(payData);
        request.setAttribute("payHtml", "<script>window.location.href = '"+payUrl+"';</script>");
        return "common/toPay";
    }

}
