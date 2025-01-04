package com.example.coincalculator;

import com.example.coincalculator.resources.CoinCalculatorResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class CoinCalculatorApplication extends Application<CoinCalculatorConfiguration> {

    public static void main(String[] args) throws Exception {
        new CoinCalculatorApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CoinCalculatorConfiguration> bootstrap) {
        // 初始化逻辑
    }

    @Override
    public void run(CoinCalculatorConfiguration configuration, Environment environment) {
        // 启用 CORS
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:3000"); // 允许前端访问的来源
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Authorization,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,POST,PUT,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true"); // 允许带 Cookie
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // 注册资源类
        environment.jersey().register(new CoinCalculatorResource());
    }
}
