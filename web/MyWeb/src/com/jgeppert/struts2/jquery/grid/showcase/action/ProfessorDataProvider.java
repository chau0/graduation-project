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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.jgeppert.struts2.jquery.grid.showcase.dao.ProfessorsDao;
import com.jgeppert.struts2.jquery.grid.showcase.model.Customer;
import com.jgeppert.struts2.jquery.grid.showcase.model.CustomerDAO;
import com.jgeppert.struts2.jquery.grid.showcase.model.Professors;
import com.opensymphony.xwork2.ActionSupport;

@Result(name = "success", type = "json")
public class ProfessorDataProvider extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 5078264277068533593L;
	private static final Log log = LogFactory.getLog(GridDataProvider.class);

	// Your result List
	private List<Professors> gridModel;

	// All Records
	private Integer records = 0;

	private ProfessorsDao professorsDao = new ProfessorsDao();

	@SuppressWarnings("unchecked")
	public String execute() {
		// Criteria to Build SQL
		DetachedCriteria criteria = DetachedCriteria.forClass(Professors.class);

		// Count Customers
		// records = professorsDao.countByCriteria(criteria);

		// Reset count Projection
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);

		// Get Customers by Criteria
		gridModel = professorsDao.getAll();
		return SUCCESS;
	}

	public String getJSON() {
		return execute();
	}

	public Integer getRecords() {
		return records;
	}

	/**
	 * @return an collection that contains the actual data
	 */
	public List<Professors> getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel
	 *            an collection that contains the actual data
	 */
	public void setGridModel(List<Professors> gridModel) {
		this.gridModel = gridModel;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

}
