package com.atomrockets.marketpredictor.beans;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.atomrockets.marketpredictor.analyzer.MarketIndicesAnalyzer;

@Component
public class MarketAnalyzerBean {

	@Async
	static public void doSomething() { 
		MarketIndicesAnalyzer.main();
	}
}
