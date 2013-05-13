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

package shedule.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.criterion.DetachedCriteria;

import shedule.web.dao.ProfessorsDao;
import shedule.web.data.Professors;

import com.opensymphony.xwork2.ActionSupport;

@Result(name = "success", type = "json")
public class ProfessorDataProvider extends ActionSupport implements
		SessionAware {

	private static final long serialVersionUID = 5078264277068533593L;
	private static final Log log = LogFactory
			.getLog(ProfessorDataProvider.class);
	// Your result List
	private List<Professors> gridModel;

	// get how many rows we want to have into the grid - rowNum attribute in the
	// grid
	private Integer rows = 0;

	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;

	// sorting order - asc or desc
	private String sord;

	// get index row - i.e. user click to sort.
	private String sidx;

	// Search Field
	private String searchField;

	// The Search String
	private String searchString;

	// Limit the result when using local data, value form attribute rowTotal
	private Integer totalrows;

	// he Search Operation
	// ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;

	// Your Total Pages
	private Integer total = 0;

	// All Records
	private Integer records = 0;

	private boolean loadonce = false;
	private Map<String, Object> session;
	private List<Professors> myProfessors;

	@SuppressWarnings("unchecked")
	public String execute() {
	    // Calcalate until rows ware selected
	    int to = (rows * page);

	    // Calculate the first row to read
	    int from = to - rows;

	    // Criteria to Build SQL
	    DetachedCriteria criteria = DetachedCriteria.forClass(Professors.class);

		Object list = session.get("mylist");
		if (list != null) {
			myProfessors = (List<Professors>) list;
		} else {
			log.debug("Build new List");
			ProfessorsDao professorsDao = new ProfessorsDao();
			myProfessors = professorsDao.findByCriteria(criteria, from, to);

			// Count all record (select count(*) from your_custumers)
			records = ProfessorsDAO.getProfessorssCount(myProfessorss);

			if (totalrows != null) {
				records = totalrows;
			}

			// Calucalate until rows ware selected
			int to = (rows * page);

			// Calculate the first row to read
			int from = to - rows;

			// Set to = max rows
			if (to > records)
				to = records;
			setGridModel(myProfessors);
			// Calculate total Pages
			total = (int) Math.ceil((double) records / (double) rows);

			// only for showcase functionality, don't do this in production
			session.put("mylist", myProfessorss);

		}

		return SUCCESS;
	}

	public String getJSON() {
		return execute();
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
	 * @return total number of records for the query. e.g. select count(*) from
	 *         table
	 */
	public Integer getRecords() {
		return records;
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

	/**
	 * @return sorting order
	 */
	public String getSord() {
		return sord;
	}

	/**
	 * @param sord
	 *            sorting order
	 */
	public void setSord(String sord) {
		this.sord = sord;
	}

	/**
	 * @return get index row - i.e. user click to sort.
	 */
	public String getSidx() {
		return sidx;
	}

	/**
	 * @param sidx
	 *            get index row - i.e. user click to sort.
	 */
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	public void setLoadonce(boolean loadonce) {
		this.loadonce = loadonce;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setTotalrows(Integer totalrows) {
		this.totalrows = totalrows;
	}

}
