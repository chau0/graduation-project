<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

    <meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="struts2, jquery, jquery-ui, plugin, showcase, jqgrid" />
	<meta http-equiv="description" content="A Showcase for the Struts2 jQuery Plugin" />

	<title>New action <s:text name="showcase.version"/></title>

  <s:if test="%{theme == 'showcase' || theme == null}">
      <sj:head debug="true" compressed="true" jquerytheme="showcase" customBasepath="themes" loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Please wait ..."/>
  </s:if>
  <s:else>
      <sj:head debug="true" compressed="true" jquerytheme="%{theme}" loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Please wait ..."/>
  </s:else>


	<link href="<s:url value="/styles/flexible-grids.css" />" rel="stylesheet" type="text/css" />
	<!--[if lte IE 7]>
	<link href="<s:url value="/yaml/core/iehacks.min.css" />" rel="stylesheet" type="text/css" />
	<![endif]-->

	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- This files are needed for AJAX Validation of XHTML Forms -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/struts/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js"></script>


	<!-- This file includes necessary functions/topics for validation and all topic examples -->
	<script type="text/javascript" src="<s:url value="/js/showcase.js" />"></script>
	<!-- Extend the Struts2 jQuery Plugin with an richtext editor -->
	<script type="text/javascript" src="<s:url value="/js/extendplugin.js" />"></script>
</head>
<body>

<header class="ui-widget-header">
	<div class="ym-wrapper">
		<div class="ym-wbox" style="padding: 5px 0 0 0;">
			<div class="ym-grid linearize-level-1">
				<div class="ym-g75 ym-gl">
					<h1 class="ui-state-default" style="background: none; border: none; margin: 0;"></h1>
					<h4 class="ui-state-default" style="background: none; border: none;"></h4>
				</div>
			
			</div>
		</div>
	</div>
</header>

<nav id="nav" class="ui-widget-header">
	<div class="ym-wrapper">
		<div class="ym-hlist ui-widget-header">
			<ul id="navlist">
				<li><s:url var="mainurl" action="list-professors" /><sj:a id="main" href="%{mainurl}" targets="main_content">Grid with Subgrid</sj:a></li>
				<li><s:url var="dataurl" action="list-data-sets" /><sj:a id="data" href="%{dataurl}" targets="main_content">data</sj:a></li>
			</ul>
		</div>
	</div>
</nav>

<div id="main">
	<div class="ym-wrapper">
		<sj:div id="main_content" href="%{mainurl}" cssClass="ym-wbox">
			<img id="indicator" src="images/indicator.gif" alt="Loading..."/>
		</sj:div>
	</div>
</div>

<footer>
	<div class="ym-wrapper">
		<div class="ym-wbox">
			<p style="text-align: center;">
			
				Layout based on <a href="http://www.yaml.de/" title="OpenSource CSS Layout">YAML</a>
			</p>
		</div>
	</div>
</footer>

</body>
</html>
