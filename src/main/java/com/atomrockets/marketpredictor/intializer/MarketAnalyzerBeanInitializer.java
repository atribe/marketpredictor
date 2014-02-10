package com.atomrockets.marketpredictor.intializer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.atomrockets.marketpredictor.beans.MarketAnalyzerBean;

@Component
public class MarketAnalyzerBeanInitializer {
	
	@Async
	public void initialize() {
		MarketAnalyzerBean.doSomething();
	}
}
