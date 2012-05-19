<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Phages</title>
	</head>
	<body>
	    <canvas id="screen" width="400" height="300"></canvas>
		<div id="controller-list" role="navigation">
			<ul>
				<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
					<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
				</g:each>
			</ul>
		</div>
	</body>
</html>