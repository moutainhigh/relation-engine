package com.yingu.relationengine.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.yingu.relationengine")
public class FeignConfiguration {

}
