<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<h2>Grid (Editable)</h2>

<p class="text">
	A editable Grid with pager and navigator. Entries are editable when a cell is selected. This Grid is sortable by
	name column and searchable by id. The first two Columns are frozen.
</p>
<s:url var="remoteurl" action="professor-data-provider" />
<s:url var="editlink" action="edit-grid-professor" />
<sjg:grid

		id="gridmultitable"
		loadonce="true"
		dataType="json"
		href="%{remoteurl}"
		pager="true"
		navigator="true"
	    navigatorSearch="false"
		navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
		navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
		navigatorEdit="false"
		navigatorView="false"
		navigatorDelete="true"
		navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
		navigatorInlineEditButtons="true"
		gridModel="gridModel"
		rowNum="20"
		editurl="%{editlink}"
		editinline="true"
		onSelectRowTopics="rowselect"
		onEditInlineSuccessTopics="oneditsuccess"
		viewrecords="true"
		shrinkToFit="false"
	
		>
	<sjg:gridColumn name="id" frozen="true" index="id" title="ID" width="60" formatter="integer" editable="false"
	                sortable="false" />
	<sjg:gridColumn name="name" index="name" title="Name" width="450" editable="true" edittype="text" sortable="false"
	                search="false"/>

</sjg:grid>
