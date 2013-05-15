/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.jgeppert.struts2.jquery.grid.showcase.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class SimpleEcho extends ActionSupport {

    private static final long serialVersionUID = 6999864671102333041L;
    private String echo;
    private boolean escape = true;
    private static final Log log = LogFactory.getLog(SimpleEcho.class);
    @Action(value = "/simpleecho", results = { @Result(location = "simpleecho.jsp", name = "success") })
    public String execute() throws Exception {
    	log.debug("simple echo");
        
	return SUCCESS;
    }

    public String getEcho() {
	return echo;
    }

    public void setEcho(String echo) {
	this.echo = echo;
    }

    public boolean isEscape() {
	return escape;
    }

    public void setEscape(boolean escape) {
	this.escape = escape;
    }
}
