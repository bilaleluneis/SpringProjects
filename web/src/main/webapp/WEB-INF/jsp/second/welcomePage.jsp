<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<body>

	<form:form method="POST" action="${controller}" modelAttribute="personModelToView">
		<h1> Welcome Page</h1>
		<table>
			<tr>
				<td><button name="start" type="submit">Begin Wizard !</button></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>