
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<body>
	<form:form method="post" modelAttribute="bean">
		<table>
			<tr>
				<td>Enter Name:</td>
				<td><form:input path="name"/></td>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td><input type="submit"/></td>
			</tr>
		</table>
	</form:form>
</body>
</html>