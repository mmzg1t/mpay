#
#   jeepay-merchant（商户平台） java程序镜像构建脚本   .Power by terrfly
#

# ------   START   ------

# 基础镜像来自： https://hub.docker.com/ 官方OFFICIAL
FROM openjdk:17

MAINTAINER Terrfly

# 配置环境变量，支持中文。
ENV LANG=C.UTF-8

# 设置时区 东八区， 解决日志时间不正确的问题。
ENV TZ=Asia/Shanghai

# 对外映射的端口 ( 不明确EXPOSE 也不影响映射  )
EXPOSE 9218

# 挂载目录 "/jeepayhomes/service/app/application.yml 这个文件不能写到VOLUME中， 否则将映射成为了目录导致启动异常。
VOLUME ["/jeepayhomes/service/logs", "/jeepayhomes/service/uploads"]



# 创建目录
RUN mkdir /jeepayhomes/service/app -p

# 安装
COPY ./target/jeepay-merchant.jar /jeepayhomes/service/app/jeepay-merchant.jar

# 设置工作目录
WORKDIR /jeepayhomes/service/app

CMD ["java", "-jar", "jeepay-merchant.jar"]

# ------   END   ------




