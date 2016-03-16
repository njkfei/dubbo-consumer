package com.niejinkun.dubbo.spring4start.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.niejinkun.dubbo.spring4start.service.DemoService;
import com.sanhao.tech.sevice.service.OrderService;

@Configuration
@Import(DubboConfig.class)
public class OrderRefConfig {
	@Autowired
	private DubboConfig dubboConfig;
	// 服务引用　配置,需要与服务提供者版本保持一致
	@Bean
	public ReferenceConfig<OrderService> reference(){
		ReferenceConfig<OrderService> reference = new ReferenceConfig<OrderService>();
		reference.setApplication(dubboConfig.application());
		reference.setRegistry(dubboConfig.registry());
		reference.setProtocol("dubbo");
		reference.setInterface(OrderService.class);
		reference.setId("orderService");
		reference.setVersion("1.0.0");   // 这行必须要有，当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。
		reference.setLoadbalance("roundrobin"); // 设置负载均衡为轮循，也可以设置为随机，一致笥hash等
		
		// 注意
		// 为了避免复杂化线上环境，不要在线上使用这个功能，只应在测试阶段使用。
		reference.setUrl("dubbo://localhost:20880"); // 直连服务提供者，此时，不需要注册中心
		
		// 设置分组,解决接口重名问题
		// reference.setGroup("XXX");
		
		// 粘滞连接用于有状态服务，尽可能让客户端总是向同一提供者发起调用，除非该提供者挂了，再连另一台。
		//reference.setSticky(true);
		
		return reference;
		
	}
	
	@Bean
	public OrderService orderService(){
		return reference().get();
	}
	 

}
