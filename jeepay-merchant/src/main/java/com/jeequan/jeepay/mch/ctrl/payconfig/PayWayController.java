package com.jeequan.jeepay.mch.ctrl.payconfig;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jeequan.jeepay.core.entity.PayWay;
import com.jeequan.jeepay.core.model.ApiPageRes;
import com.jeequan.jeepay.mch.ctrl.CommonCtrl;
import com.jeequan.jeepay.service.impl.MchPayPassageService;
import com.jeequan.jeepay.service.impl.PayOrderService;
import com.jeequan.jeepay.service.impl.PayWayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付方式配置类
 */
@Tag(name = "支付方式配置")
@RestController
@RequestMapping("api/payWays")
public class PayWayController extends CommonCtrl {

	@Autowired PayWayService payWayService;
	@Autowired MchPayPassageService mchPayPassageService;
	@Autowired PayOrderService payOrderService;

	/**
	 * @Author: ZhuXiao
	 * @Description: list
	 * @Date: 15:52 2021/4/27
	*/
	@Operation(summary = "支付方式--列表")
	@Parameters({
			@Parameter(name = "iToken", description = "用户身份凭证", required = true, in = ParameterIn.HEADER),
			@Parameter(name = "pageNumber", description = "分页页码"),
			@Parameter(name = "pageSize", description = "分页条数（-1时查全部数据）"),
			@Parameter(name = "wayCode", description = "支付方式代码"),
			@Parameter(name = "wayName", description = "支付方式名称")
	})
	@PreAuthorize("hasAuthority('ENT_PAY_ORDER_SEARCH_PAY_WAY')")
	@GetMapping
	public ApiPageRes<PayWay> list() {

		PayWay queryObject = getObject(PayWay.class);

		LambdaQueryWrapper<PayWay> condition = PayWay.gw();
		if(StringUtils.isNotEmpty(queryObject.getWayCode())){
			condition.like(PayWay::getWayCode, queryObject.getWayCode());
		}
		if(StringUtils.isNotEmpty(queryObject.getWayName())){
			condition.like(PayWay::getWayName, queryObject.getWayName());
		}
		condition.orderByAsc(PayWay::getWayCode);

		IPage<PayWay> pages = payWayService.page(getIPage(true), condition);

		return ApiPageRes.pages(pages);
	}

}
