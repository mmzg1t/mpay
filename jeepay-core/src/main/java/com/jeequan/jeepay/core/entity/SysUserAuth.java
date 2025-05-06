package com.jeequan.jeepay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 系统用户认证表
 * </p>

 */
@Schema(description = "系统用户认证表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user_auth")
public class SysUserAuth implements Serializable {

    //gw
    public static final LambdaQueryWrapper<SysUserAuth> gw(){
        return new LambdaQueryWrapper<>();
    }

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @Schema(title = "authId", description = "ID")
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Long authId;

    /**
     * user_id
     */
    @Schema(title = "userId", description = "用户ID")
    private Long userId;

    /**
     * 登录类型  1-昵称 2-手机号 3-邮箱  10-微信  11-QQ 12-支付宝 13-微博
     */
    @Schema(title = "identityType", description = "登录类型  1-昵称 2-手机号 3-邮箱  10-微信  11-QQ 12-支付宝 13-微博")
    private Byte identityType;

    /**
     * 认证标识 ( 用户名 | open_id )
     */
    @Schema(title = "identifier", description = "认证标识 ( 用户名 | open_id )")
    private String identifier;

    /**
     * 密码凭证
     */
    @Schema(title = "credential", description = "密码凭证")
    private String credential;

    /**
     * salt
     */
    @Schema(title = "salt", description = "salt")
    private String salt;

    /**
     * 所属系统： MGR-运营平台, MCH-商户中心
     */
    @Schema(title = "sysType", description = "所属系统： MGR-运营平台, MCH-商户中心")
    private String sysType;



}
