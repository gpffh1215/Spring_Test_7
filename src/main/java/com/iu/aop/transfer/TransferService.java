package com.iu.aop.transfer;

import javax.inject.Inject;

public class TransferService {
	
	@Inject
	private Transport transport;
	
	@Inject
	private CardCheck cardCheck;
	
	
	public void start(){
		
		cardCheck.check();
		transport.subway();
		cardCheck.check();
		cardCheck.check();
		transport.bus();
		cardCheck.check();
		
	}
	
	
}
