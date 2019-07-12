
package com.coedelsur;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;

public class AdminBootMain {
	
	final static Logger logger = Logger.getLogger(AdminBootMain.class);
	
	protected AdminBootMain() {
	}

	public static void main(String[] args) {
		SpringApplication.run(AdminBootApplication.class, args);
		logger.info( "Aplicacion COE del SUR - INICIADA OK!");
	}
	
}



