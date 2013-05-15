<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<h2>Grid (Local Data)</h2>

<p class="text">
</p>
<s:url var="remoteurl" action="professor-data-provider" >
	<s:param name="loadonce" value="%{true}"/>
</s:url>

<s:url var="editcellurl" action="edit-cell-professor" />
<sjg:grid
		id="gridloadtable"
		loadonce="true"
		href="%{remoteurl}"
		gridModel="gridModel"
		rowNum="-1"
		hidegrid="true"
		scroll="true"
		cellEdit="true"
		cellurl="%{editcellurl}"
		altRows="true"
		sortable="true"
		sortableOpacity="0"
		sortablePlaceholder="ui-state-highlight"
		sortableForcePlaceholderSize="true"
		height="450"
		>
	<sjg:gridColumn name="id" index="id" key="true" title="ID" width="30" formatter="integer" sortable="true"
	                sorttype="int"/>
	<sjg:gridColumn name="name" index="name" title="Name" width="550" sortable="true" editable="true"/>

</sjg:grid>

