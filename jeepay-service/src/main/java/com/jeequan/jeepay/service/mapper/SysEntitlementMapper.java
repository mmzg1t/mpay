package com.jeequan.jeepay.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeequan.jeepay.core.entity.SysEntitlement;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统权限表 Mapper 接口
 * </p>
 */
public interface SysEntitlementMapper extends BaseMapper<SysEntitlement> {

    Integer userHasLeftMenu(@Param("userId") Long userId, @Param("sysType") String sysType);

}
