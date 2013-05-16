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
		href="%{remoteurl}"
		gridModel="gridModel"
		scroll="true"
		cellEdit="true"
		cellurl="%{editcellurl}"
		altRows="true"
		sortable="true"
		sortableOpacity="0"
		sortablePlaceholder="ui-state-highlight"
		sortableForcePlaceholderSize="true"
		loadonce="true"
	    rowNum="-1"
	    pager="true"
	    height="450"
	    navigator="true"
	    navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
		navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
	    navigatorInlineEditButtons="true"
	    navigatorAdd="false"
	    navigatorEdit="false"
	    navigatorRefresh="false"
	    navigatorSearch="false"
	    editurl="editcellurl"
	    onSelectRowTopics="rowselect"
		onEditInlineSuccessTopics="oneditsuccess"
	    editinline="true"
		>
	<sjg:gridColumn name="id" index="id" key="true" title="ID" width="30" formatter="integer" sortable="true"
	                sorttype="int"/>
	<sjg:gridColumn name="name" index="name" title="Name" width="550" sortable="true"  />

</sjg:grid>

