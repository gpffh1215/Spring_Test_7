package com.iu.aop.transfer;

import org.springframework.stereotype.Component;

@Component
public class Transport {
	
	public void bus(){
		System.out.println("=====BUS=====");
		System.out.println("음악 듣기");
		
	}
	
	
	public void subway(){
		System.out.println("====SUBWAY====");
		System.out.println("가만히 있기");
	}
	
	
	
}
