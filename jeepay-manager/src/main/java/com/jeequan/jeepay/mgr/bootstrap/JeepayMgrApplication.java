package com.jeequan.jeepay.mgr.bootstrap;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/*
* spring-boot 主启动程序
*
*/
@SpringBootApplication
@EnableScheduling
@MapperScan("com.jeequan.jeepay.service.mapper")    //Mybatis mapper接口路径
@ComponentScan(basePackages = "com.jeequan.jeepay.*")   //由于MainApplication没有在项目根目录， 需要配置basePackages属性使得成功扫描所有Spring组件；
@Configuration
public class JeepayMgrApplication {

    /** main启动函数 **/
    public static void main(String[] args) {

        //启动项目
        SpringApplication.run(JeepayMgrApplication.class, args);

    }


    /** fastJson 配置信息 **/
    @Bean
    public HttpMessageConverters fastJsonConfig(){

        //新建fast-json转换器
        FastJsonHttpMessageConverterEx converter = new FastJsonHttpMessageConverterEx();

        // 开启 FastJSON 安全模式！
        ParserConfig.getGlobalInstance().setSafeMode(true);

        //fast-json 配置信息
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);

        //设置响应的 Content-Type
        converter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8}));
        return new HttpMessageConverters(converter);
    }

    /** Mybatis plus 分页插件 **/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }



    /**
     * 功能描述:  API访问地址： http://localhost:9217/doc.html
     *
     * @Return: springfox.documentation.spring.web.plugins.Docket
     * @Author: terrfly
     * @Date: 2023/6/13 15:04
     */
    /*@Bean(value = "knife4jDockerBean")
    public Docket knife4jDockerBean() {
        return new Docket(DocumentationType.SWAGGER_2)  //指定使用Swagger2规范
                .apiInfo(new ApiInfoBuilder().version("1.0").build()) //描述字段支持Markdown语法
                .groupName("运营平台") //分组名称
                .select() // 配置： 如何扫描
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)) // 只扫描： ApiOperation 注解文档。 也支持配置包名、 路径等扫描模式。
                .build();
    }*/

}
