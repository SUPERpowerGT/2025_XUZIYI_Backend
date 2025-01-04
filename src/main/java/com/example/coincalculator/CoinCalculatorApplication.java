package com.example.coincalculator;

import com.example.coincalculator.resources.CoinCalculatorResource;


import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import java.util.EnumSet;

public class CoinCalculatorApplication extends Application<CoinCalculatorConfiguration> {

    public static void main(String[] args) throws Exception {
        new CoinCalculatorApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CoinCalculatorConfiguration> bootstrap) {
    }

    @Override
    public void run(CoinCalculatorConfiguration configuration, Environment environment) {

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Authorization,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,POST,PUT,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        environment.jersey().register(new CoinCalculatorResource());
    }
}
