package com.ytn.cctvdisaster.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqMessageListener {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitMqMessageListener.class);
    
	/*
	private YtnUtilServiceImpl ytnUtilServiceImpl;
	
	private static StringBuffer stringBuffer = new StringBuffer();
	
	public RabbitMqMessageListener(CaptionsDataService captionsDataService, YtnUtilServiceImpl ytnUtilServiceImpl) {
		this.captionsDataService = captionsDataService;
		this.ytnUtilServiceImpl = ytnUtilServiceImpl;
	}
	*/
	
	/*
	@RabbitListener(bindings = @QueueBinding(
		    value = @Queue(value = "cctv_disaster"),
		    exchange = @Exchange(value = "MKTEST"),
		    key = "ytn.cctv.disaster"
		))
	*/
	/*
	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void processMessage1(RabbitMqResponseDataVo rabbitMqResponseDataVo) {
		
		logger.info("[RabbitMqMessageListener] [processMessage] cctv_disaster1 : {}", rabbitMqResponseDataVo);
		
	}
	
	@RabbitListener(queues = "cctv_disaster2")
	public void processMessage2(RabbitMqResponseDataVo rabbitMqResponseDataVo) {
		
		logger.info("[RabbitMqMessageListener] [processMessage] cctv_disaster2 : {}", rabbitMqResponseDataVo);
		
	}
	
	@RabbitListener(queues = "cctv_disaster3")
	public void processMessage3(RabbitMqResponseDataVo rabbitMqResponseDataVo) {
		
		logger.info("[RabbitMqMessageListener] [processMessage] cctv_disaster3 : {}", rabbitMqResponseDataVo);
		
	}
	*/
}
