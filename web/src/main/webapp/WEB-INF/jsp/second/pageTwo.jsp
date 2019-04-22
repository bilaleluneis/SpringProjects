<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<body>
	
	<form:form method="POST" action="${controller}" modelAttribute="personModelToView">
		<h1>You are in Page Two of the Form !</h1>
		<table>
			<tr>
				<td><button name="start" type="submit">Start Over</button></td>
				<td><button name="back" type="submit">Back</button></td>
			</tr>
		</table>
	</form:form>
	
	
</body>
</html>