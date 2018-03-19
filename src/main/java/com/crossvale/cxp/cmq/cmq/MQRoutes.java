package com.crossvale.cxp.cmq.cmq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


@Component
public class MQRoutes extends RouteBuilder {
	

    @Override
    public void configure() throws Exception {
    	
        from("activemq:customers")
        	.choice()
        		.when(method(Evaluator.class, "evaluateStatus")).log("Customer validated.")
        		.otherwise().to("bean:notifyCustomerSupport").log("Customer support has been notified");
        
        restConfiguration().component("restlet")
		   .port(9090)
		   ;
		
    }
}