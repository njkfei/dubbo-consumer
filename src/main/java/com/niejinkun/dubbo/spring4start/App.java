package com.niejinkun.dubbo.spring4start;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.niejinkun.dubbo.spring4start.config.OrderRefConfig;
import com.sanhao.tech.sevice.model.Order;
import com.sanhao.tech.sevice.model.OrderCT;
import com.sanhao.tech.sevice.service.OrderService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(OrderRefConfig.class);
    	
    	OrderService orderService = (OrderService) context.getBean("orderService");
    	
    	Order order = orderService.getOrder(4);
    	List<OrderCT> octs = orderService.getOrderCTList(4);
    	if(order != null){
    		System.out.println(order.toString());
    	}
    	
    	if(octs != null){
    		for(OrderCT item : octs){
    			System.out.println(item.toString());
    		}
    	}
    	context.start();
    	

    	
    }
    			
}
