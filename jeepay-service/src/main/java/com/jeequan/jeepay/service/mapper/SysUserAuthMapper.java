package com.jeequan.jeepay.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.jeequan.jeepay.core.entity.SysUserAuth;

/**
 * <p>
 * 操作员认证表 Mapper 接口
 * </p>
 */
public interface SysUserAuthMapper extends BaseMapper<SysUserAuth> {

    SysUserAuth selectByLogin(@Param("identifier")String identifier,
                              @Param("identityType")Byte identityType, @Param("sysType")String sysType);

}
