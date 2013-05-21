<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords"
	content="struts2, jquery, jquery-ui, plugin, showcase, jqgrid" />
<meta http-equiv="description"
	content="A Showcase for the Struts2 jQuery Plugin" />

<title>Web lập lịch <s:text name="showcase.version" /></title>

<s:if test="%{theme == 'showcase' || theme == null}">
	<sj:head debug="true" compressed="true" jquerytheme="showcase"
		customBasepath="themes" loadFromGoogle="%{google}"
		ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator"
		defaultLoadingText="Please wait ..." />
</s:if>

<s:else>
	<sj:head debug="true" compressed="true" jquerytheme="%{theme}"
		loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}"
		defaultIndicator="myDefaultIndicator"
		defaultLoadingText="Please wait ..." />
</s:else>


<link href="<s:url value="/styles/flexible-grids.css" />"
	rel="stylesheet" type="text/css" />
<!--[if lte IE 7]>
	<link href="<s:url value="/yaml/core/iehacks.min.css" />" rel="stylesheet" type="text/css" />
	<![endif]-->

<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

<!-- This files are needed for AJAX Validation of XHTML Forms -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/struts/utils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/struts/xhtml/validation.js"></script>


<!-- This file includes necessary functions/topics for validation and all topic examples -->
<script type="text/javascript" src="<s:url value="/js/showcase.js" />"></script>
<!-- Extend the Struts2 jQuery Plugin with an richtext editor -->
<script type="text/javascript"
	src="<s:url value="/js/extendplugin.js" />"></script>
</head>
<body>


	<header class="ui-widget-header">
		<div class="ym-wrapper">
			<div class="ym-wbox" style="padding: 5px 0 0 0;">
				<div class="ym-grid linearize-level-1">
					<div class="ym-g75 ym-gl">
						<h1 class="ui-state-default"
							style="background: none; border: none; margin: 0;">Lập lịch
							bảo vệ cao học</h1>
						<h4 class="ui-state-default"
							style="background: none; border: none;">
							Version 1.0.0
							<s:text name="showcase.version" />
						</h4>
					</div>

				</div>
			</div>
		</div>
	</header>

	<nav id="nav" class="ui-widget-header">
		<div class="ym-wrapper">
			<div class="ym-hlist ui-widget-header">
				<ul id="navlist">
					<li><a href="list-professors.action">Danh sách giảng viên</a></li>
					<li><a href="list-data-sets.action">Lập lịch</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div id="main">
		<div class="ym-wrapper">
			<h2>Lịch bảo vệ</h2>
			<p class="text"></p>
			<s:url var="editjuryurl" action="edit-jury" />
			<s:url var="selectprofurl" action="professor-names" />
			<s:url var="remoteurl" action="shedule-data-provider" />
			<sjg:grid dataType="json" href="%{remoteurl}" pager="true"
				navigator="true" navigatorAdd="false" navigatorEdit="true"
				navigatorView="false" navigatorDelete="true"
				navigatorRefresh="false" navigatorSearch="false"
				gridModel="gridModel" rowNum="100" editurl="%{editjuryurl}"
				editinline="false" shrinkToFit="false" viewrecords="true"
				width="1000" rownumbers="true"
				caption="Lịch bảo vệ"
				>
				<sjg:gridColumn name="slotDescription" index="slotDescription"
					title="Slot" width="70" editable="true" edittype="text"
					sortable="true" search="false" />
				<sjg:gridColumn name="roomName" index="roomName" title="Room"
					width="100" editable="true" edittype="text" sortable="true"
					search="false" />
				<sjg:gridColumn name="name" index="name" title="Name" width="150"
					editable="true" edittype="text" sortable="true" search="false" />

				<sjg:gridColumn name="presidentName" title="President" width="150"
					editable="true" edittype="select"
					editoptions="{ dataUrl : '%{selectprofurl}' }" search="false" />
				<sjg:gridColumn name="secretaryName" index="secretaryName"
					title="Secretary" width="150" editable="true" edittype="select"
					editoptions="{ dataUrl : '%{selectprofurl}' }" sortable="true"
					search="false" />

				<sjg:gridColumn name="examinerName1" index="examinerName1"
					title="Examiner 1" width="150" editable="true" edittype="select"
					editoptions="{ dataUrl : '%{selectprofurl}' }" sortable="true"
					search="false" />
				<sjg:gridColumn name="examinerName2" index="examinerName2"
					title="Examiner 2" width="150" editable="true" edittype="select"
					editoptions="{ dataUrl : '%{selectprofurl}' }" sortable="true"
					search="false" />
				<sjg:gridColumn name="additionalmemberName"
					index="additionalmemberName" title="AdditionalMember" width="150"
					editable="true" edittype="select"
					editoptions="{ dataUrl : '%{selectprofurl}' }" sortable="true"
					search="false" />
				<sjg:gridColumn name="title" index="title" title="Title" width="500"
					editable="true" edittype="text" sortable="true" search="false" />

			</sjg:grid>

			<br>
			<form action="list-jury.action">
				<input type="submit" value="Tro lai"
					class="ui-button ui-widget ui-state-default ui-corner-all">
			</form>
			<br>
		</div>
	</div>





	<footer>
		<div class="ym-wrapper">
			<div class="ym-wbox">
				<p style="text-align: center;">

					Layout based on <a href="http://www.yaml.de/"
						title="OpenSource CSS Layout">YAML</a>
				</p>
			</div>
		</div>
	</footer>

</body>
</html>
