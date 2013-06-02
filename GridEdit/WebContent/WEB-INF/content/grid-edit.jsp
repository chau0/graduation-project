<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<h2>Grid (Editable)</h2>

<p class="text">
	
	
	
</p>
<s:url var="remoteurl" action="grid-data-provider" />
<s:url var="editurl" action="edit-grid-entry" />
<sjg:grid
		dataType="json"
		href="%{remoteurl}"
		pager="true"
		navigator="true"
		navigatorAdd="true"
		navigatorEdit="true"
		navigatorView="false"
		navigatorDelete="true"
		navigatorRefresh="false"
		navigatorSearch="false"
		gridModel="gridModel"
		rowNum="50"
		editurl="%{editurl}"
		editinline="false"
		shrinkToFit="false"
		viewrecords="true"
		>
	<sjg:gridColumn name="id" frozen="true" index="id" title="ID" width="30" formatter="integer" editable="false"
	                sortable="false" search="true" />
	<sjg:gridColumn name="name" index="name" title="Name" width="550" editable="true" edittype="text" sortable="true"
	                search="false"/>
	
</sjg:grid>




