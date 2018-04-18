package io.spring2go.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan({ "io.spring2go.web.controller" })
public class ResourceServerWebConfig extends WebMvcConfigurerAdapter {
    //
}
