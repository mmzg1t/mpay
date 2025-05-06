package com.jeequan.jeepay.mch.ctrl.sysuser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jeequan.jeepay.core.constants.CS;
import com.jeequan.jeepay.core.entity.SysEntitlement;
import com.jeequan.jeepay.core.model.ApiRes;
import com.jeequan.jeepay.core.utils.TreeDataBuilder;
import com.jeequan.jeepay.mch.ctrl.CommonCtrl;
import com.jeequan.jeepay.service.impl.SysEntitlementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限菜单管理类
 *
 */
@Tag(name = "系统管理（用户权限）")
@RestController
@RequestMapping("api/sysEnts")
public class SysEntController extends CommonCtrl {

	@Autowired SysEntitlementService sysEntitlementService;

	/** 查询权限集合 */
	@Operation(summary = "查询权限集合")
	@Parameters({
			@Parameter(name = "iToken", description = "用户身份凭证", required = true, in = ParameterIn.HEADER),
	})
	@PreAuthorize("hasAnyAuthority( 'ENT_UR_ROLE_ENT_LIST', 'ENT_UR_ROLE_DIST' )")
	@RequestMapping(value="/showTree", method = RequestMethod.GET)
	public ApiRes<List<JSONObject>> showTree() {

		//查询全部数据
		List<SysEntitlement> list = sysEntitlementService.list(SysEntitlement.gw().eq(SysEntitlement::getSysType, CS.SYS_TYPE.MCH));

		//4. 转换为json树状结构
		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(list);
		List<JSONObject> leftMenuTree = new TreeDataBuilder(jsonArray,
				"entId", "pid", "children", "entSort", true)
				.buildTreeObject();

		return ApiRes.ok(leftMenuTree);
	}


}
