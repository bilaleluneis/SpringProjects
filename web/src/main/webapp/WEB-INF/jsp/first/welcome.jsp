<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<h1>Welcome page from FIRST folder !</h1>
<body>

	<form:form method="POST" action="next">
		<button type="submit">Next</button>
	</form:form>
	
	<form:form method="POST" action="back">
		<button type="submit">Back</button>
	</form:form>
	
	<form:form method="POST" action="link">
		<a href="javascript:;" onclick="parentNode.submit();">a link to click</a>
	</form:form>
	
</body>
</html>