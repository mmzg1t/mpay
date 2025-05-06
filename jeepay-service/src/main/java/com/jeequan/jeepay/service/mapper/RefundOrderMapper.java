package com.jeequan.jeepay.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeequan.jeepay.core.entity.RefundOrder;

/**
 * <p>
 * 退款订单表 Mapper 接口
 * </p>
 */
public interface RefundOrderMapper extends BaseMapper<RefundOrder> {

    /** 查询全部退成功金额 **/
    Long sumSuccessRefundAmount(String payOrderId);

}
