package com.jeequan.jeepay.mgr.web;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

/*
* 读取servletContext 上下文工具类
*
*/
@Service
public class ApplicationContextKit implements ServletContextAware,InitializingBean{

	private ServletContext servletContext ;
	@Override
	public void setServletContext(ServletContext servletContext) {

		this.servletContext = servletContext;
	}

	/**
	 * afterPropertiesSet 是在什么之后执行？ 启动顺序是？
	 * 调用PropKit（SpringBeansUtil.getBean获取方式） 会不会出现找不到bean的问题？
	 *
	 * */
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	/**
	 * 仅在项目启动完成，并且在req请求中使用！！
	 * @param key
	 * @return
	 */
	public static Object getReqSession(String key){

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request.getSession().getAttribute(key);

	}

	/**
	 * 仅在项目启动完成，并且在req请求中使用！！
	 * @param key
	 * @return
	 */
	public static void clearSession(String key){

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		request.getSession().removeAttribute(key);

	}


}
