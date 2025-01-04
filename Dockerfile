# 使用官方 OpenJDK 基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录为 /app
WORKDIR /app

# 将 Maven 项目文件复制到容器
COPY pom.xml /app

# 下载依赖（利用缓存机制加快构建速度）
RUN apt-get update && apt-get install -y maven && mvn dependency:resolve

# 将项目代码复制到容器
COPY src /app/src

# 构建项目（生成可执行的 JAR 文件）
RUN mvn clean package

# 暴露应用程序运行的端口
EXPOSE 8080

# 设置启动命令
CMD ["java", "-jar", "target/your-application.jar"]
