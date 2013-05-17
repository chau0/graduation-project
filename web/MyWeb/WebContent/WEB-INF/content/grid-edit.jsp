<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<h2>Grid (Editable)</h2>

<p class="text">
	A editable Grid with pager and navigator. Entries are editable when a cell is selected. This Grid is sortable by
	name column and searchable by id. The first two Columns are frozen.
</p>
<s:url var="remoteurl" action="grid-data-provider" />
<s:url var="editlink" action="edit-grid-entry" />
<sjg:grid
		id="gridmultitable"
		caption="Customers Examples (Editable)"
		dataType="json"
		href="%{remoteurl}"
		pager="true"
		navigator="true"
		navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
		navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
		navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
		navigatorEdit="false"
		navigatorView="false"
		navigatorDelete="true"
		navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
		navigatorInlineEditButtons="true"
		navigatorExtraButtons="{
    		seperator: { 
    			title : 'seperator'  
    		}, 
    		hide : { 
	    		title : 'Show/Hide', 
	    		icon: 'ui-icon-wrench', 
	    		topic: 'showcolumns'
    		},
    		alert : { 
	    		title : 'Alert', 
	    		onclick: function(){ alert('Grid Button clicked!') }
    		}
    	}"
		gridModel="gridModel"
		rowList="10,15,20"
		rowNum="15"
		editurl="%{editlink}"
		editinline="true"
		onSelectRowTopics="rowselect"
		onEditInlineSuccessTopics="oneditsuccess"
		viewrecords="true"
		width="700"
		shrinkToFit="false"
		>
	<sjg:gridColumn name="id" frozen="true" index="id" title="ID" width="60" formatter="integer" editable="false"
	                sortable="false" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"/>
	<sjg:gridColumn name="name" index="name" title="Name" width="250" editable="true" edittype="text" sortable="true"
	                search="false"/>
</sjg:grid>
