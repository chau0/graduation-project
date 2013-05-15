<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<h2>Grid (Editable)</h2>

<p class="text">A editable Grid with pager and navigator. Entries
	are editable when a cell is selected. This Grid is sortable by name
	column and searchable by id. The first two Columns are frozen.</p>
<s:url var="remoteurl" action="grid-data-provider" namespace="/grid" />
<s:url var="editurl" action="edit-grid-entry" namespace="/grid" />
<sjg:grid id="gridedittable" caption="Customers Examples (Editable)"
	dataType="json" href="%{remoteurl}" pager="true" navigator="true"
	navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
 navigatorAddOptions="{height:280,reloadAfterSubmit:true}" 
	navigatorEditOptions="{height:280,reloadAfterSubmit:false}"
	navigatorEdit="false" navigatorView="false" navigatorDelete="true"
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
	gridModel="gridModel" rowList="10,15,20" rowNum="15"
	editurl="%{editurl}" editinline="true" onSelectRowTopics="rowselect"
	onEditInlineSuccessTopics="oneditsuccess" viewrecords="true"
	width="700" shrinkToFit="false">
	<sjg:gridColumn name="id" frozen="true" index="id" title="ID"
		width="60" formatter="integer" editable="false" sortable="false"
		search="true" searchoptions="{sopt:['eq','ne','lt','gt']}" />
	<sjg:gridColumn name="name" index="name" title="Name" width="250"
		editable="true" edittype="text" sortable="true" search="false" />
	<sjg:gridColumn name="lastName" index="lastName" title="Last Name"
		sortable="false" editable="true" edittype="text" />
	<sjg:gridColumn name="firstName" index="firstName" title="First Name"
		sortable="false" editable="true" edittype="text" />
	<sjg:gridColumn name="addressLine1" index="addressLine1" title="Adress"
		sortable="false" editable="true" edittype="text" />
	<sjg:gridColumn name="country" index="country" title="Country"
		editable="true" edittype="select"
		editoptions="{value:'France:France;USA:USA;Australia:Australia;Norway:Norway;Poland:Poland;Germany:Germany;Spain:Spain'}"
		sortable="false" search="false" />
	<sjg:gridColumn name="city" index="city" title="City" editable="true"
		edittype="text" sortable="false" search="false" />
	<sjg:gridColumn name="creditLimit" index="creditLimit"
		title="Credit Limit" align="right" formatter="currency"
		editable="true" edittype="text" sortable="false" search="false" />
</sjg:grid>
<br />
<%-- <sj:submit id="grid_edit_addbutton" value="Add Row"
	onClickTopics="rowadd" button="true" />
<sj:submit id="grid_edit_searchbutton" value="Search"
	onClickTopics="searchgrid" button="true" />
<sj:submit id="grid_edit_colsbutton" value="Show/Hide Columns"
	onClickTopics="showcolumns" button="true" /> --%>
<br />
<br />

<div id="gridinfo" class="ui-widget-content ui-corner-all">
	<p>Edit Mode for Row :</p>
</div>

<br />

