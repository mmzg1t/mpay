package com.jeequan.jeepay.mgr.ctrl.sysuser;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jeequan.jeepay.core.entity.SysRoleEntRela;
import com.jeequan.jeepay.core.model.ApiPageRes;
import com.jeequan.jeepay.mgr.ctrl.CommonCtrl;
import com.jeequan.jeepay.service.impl.SysRoleEntRelaService;
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

/*
* 角色 权限管理
*
*/
@Tag(name = "系统管理（用户-角色-权限关联信息）")
@RestController
@RequestMapping("api/sysRoleEntRelas")
public class SysRoleEntRelaController extends CommonCtrl {

	@Autowired private SysRoleEntRelaService sysRoleEntRelaService;

	/** list */
	@Operation(summary = "关联关系--角色-权限关联信息列表")
	@Parameters({
			@Parameter(name = "iToken", description = "用户身份凭证", required = true, in = ParameterIn.HEADER),
			@Parameter(name = "pageNumber", description = "分页页码"),
			@Parameter(name = "pageSize", description = "分页条数（-1时查全部数据）"),
			@Parameter(name = "roleId", description = "角色ID, ROLE_开头")
	})
	@PreAuthorize("hasAnyAuthority( 'ENT_UR_ROLE_ADD', 'ENT_UR_ROLE_DIST' )")
	@RequestMapping(value="", method = RequestMethod.GET)
	public ApiPageRes<SysRoleEntRela> list() {

		SysRoleEntRela queryObject = getObject(SysRoleEntRela.class);

		LambdaQueryWrapper<SysRoleEntRela> condition = SysRoleEntRela.gw();

		if(queryObject.getRoleId() != null){
			condition.eq(SysRoleEntRela::getRoleId, queryObject.getRoleId());
		}

		IPage<SysRoleEntRela> pages = sysRoleEntRelaService.page(getIPage(true), condition);

		return ApiPageRes.pages(pages);
	}

}
