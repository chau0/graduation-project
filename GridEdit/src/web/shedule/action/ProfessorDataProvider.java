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
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import web.shedule.dao.ProfessorsDao;
import web.shedule.model.Professors;
import web.shedule.util.Debug;

import com.opensymphony.xwork2.ActionSupport;

@Result(name = "success", type = "json")
public class ProfessorDataProvider extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 5078264277068533593L;
	private static final Log log = LogFactory
			.getLog(ProfessorDataProvider.class);

	// Your result List
	private List<Professors> gridModel;
	private Map<String, Object> session;
	// All Records
	private Integer records = 0;
	// get how many rows we want to have into the grid - rowNum attribute in the
	// grid
	private Integer rows = 0;

	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;

	// Your Total Pages
	private Integer total = 0;

	// All Records
	private ProfessorsDao professorsDao = new ProfessorsDao();

	@SuppressWarnings("unchecked")
	public String execute() {
		Debug.d("Page " + getPage() + " Rows " + getRows());
		// Calcalate until rows ware selected
		int to = (rows * page);

		// Calculate the first row to read
		int from = to - rows;

		// Criteria to Build SQL
		DetachedCriteria criteria = DetachedCriteria.forClass(Professors.class);
		records = professorsDao.countByCriteria(criteria);
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);

		gridModel = professorsDao.findByCriteria(criteria, from + 1, to);
		// Set to = max rows
		if (to > records)
			to = records;
		Debug.d("record:" + records);
		Debug.d("grid mode size:" + gridModel.size());
		// Calculate total Pages
		total = (int) Math.ceil((double) records / (double) rows);

		return SUCCESS;
	}

	public String getJSON() {
		return execute();
	}

	public Integer getRecords() {
		return records;
	}

	public List<Professors> getGridModel() {
		return gridModel;
	}

	public void setGridModel(List<Professors> gridModel) {
		this.gridModel = gridModel;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * @return how many rows we want to have into the grid
	 */
	public Integer getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            how many rows we want to have into the grid
	 */
	public void setRows(Integer rows) {
		this.rows = rows;
	}

	/**
	 * @return current page of the query
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page
	 *            current page of the query
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return total pages for the query
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            total pages for the query
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @param record
	 *            total number of records for the query. e.g. select count(*)
	 *            from table
	 */
	public void setRecords(Integer records) {
		this.records = records;
		if (this.records > 0 && this.rows > 0) {
			this.total = (int) Math.ceil((double) this.records
					/ (double) this.rows);
		} else {
			this.total = 0;
		}
	}

}
