/*
 * Copyright 2016-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coedelsur;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.coedelsur.model.Car;
import com.coedelsur.util.Utils;

@SpringBootApplication
public class AdminBootApplication {
	
	final static Logger logger = Logger.getLogger(AdminBootApplication.class);
	
	@Inject
	private Utils utils;
	
    @Bean
    public List<Car> getCars() {
    	logger.info( "***************************** getCars");
        return utils.getCars();
    }

}
