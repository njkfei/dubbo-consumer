package com.niejinkun.dubbo.spring4start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.niejinkun.dubbo.spring4start.service.DemoService;

@Configuration
public class DubboConfig {

	// 当前应用配置
	@Bean
	public ApplicationConfig application() {
		ApplicationConfig application = new ApplicationConfig();
		application.setName("demoService-comsumer");

		return application;
	}

	// 连接注册中心
	@Bean
	public RegistryConfig registry() {
		RegistryConfig registry = new RegistryConfig();
		registry.setProtocol("zookeeper");
		registry.setAddress("zookeeper://127.0.0.1:2181");
/*		registry.setUsername("aaa");
		registry.setPassword("bbb");*/
		return registry;
	}
	
	// 服务提供者协议配置
	@Bean
	public ProtocolConfig protocol(){
	ProtocolConfig protocol = new ProtocolConfig();
	protocol.setName("dubbo");
	protocol.setPort(20880);
	protocol.setThreads(200);
	
	return protocol;
	}
}
