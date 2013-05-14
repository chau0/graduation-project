<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>


<s:url var="remoteurl" action="json-table" />
<s:url var="editurl" action="edit-customer" />
<s:url var="selectcountrysurl" action="customer-countrys" />
<s:url var="selectemployeesurl" action="employees" />
<s:url var="empurl" action="employees-detail" />
<sj:dialog id="employees_details" title="Employee Details"
	autoOpen="false" modal="true" width="400">

</sj:dialog>
<h2>Grid</h2>

<sjg:grid id="gridloadtable" loadonce="true"
  href="%{remoteurl}"
	gridModel="gridModel" rowNum="-1" hidegrid="true" scroll="true"
	cellEdit="true" cellurl="%{editcellurl}" altRows="true" sortable="true"
	sortableOpacity="0.8" sortablePlaceholder="ui-state-highlight"
	sortableForcePlaceholderSize="true">
	<sjg:gridColumn name="id" index="id" key="true" title="ID" width="30"
		formatter="integer" sortable="true" sorttype="int" />
	
	<sjg:gridColumn name="name" index="name"
		title="Name" editable="true"
		 />
</sjg:grid>
