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

package web.shedule.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

import web.shedule.dao.SlotDao;
import web.shedule.model.Slot;
import web.shedule.util.Debug;

import com.opensymphony.xwork2.ActionSupport;

@Result(name = "success", type = "json")
public class SlotNameProvider extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 5078264277068533593L;
	private static final Log log = LogFactory.getLog(SlotNameProvider.class);
	private List<Slot> listSlots;
	private Map<String, Object> session;
	private SlotDao slotDao = new SlotDao();

	@SuppressWarnings("unchecked")
	public String execute() {
		Debug.d("execute list slot");
		listSlots = slotDao.getAll();
		return SUCCESS;
	}

	public String getJSON() {
		return execute();
	}

	public List<Slot> getListSlots() {
		return listSlots;
	}

	public void setListSlots(List<Slot> listSlots) {
		this.listSlots = listSlots;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
