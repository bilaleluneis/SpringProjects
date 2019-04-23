<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<style type="text/css">
	.btn-link {
    	border: none;
    	outline: none;
    	background: none;
    	cursor: pointer;
    	color: #0000EE;
    	padding: 0;
    	text-decoration: underline;
    	font-family: inherit;
    	font-size: inherit;
	}
</style>
<body>

	<form:form method="POST" action="${controller}" modelAttribute="personModelToView">
		<h1>You are in Page One of The Form !</h1>
		<table>
			<tr>
				<td>Enter Name:</td>
				<td><form:input path="name"/></td>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td><button name="saveLink" type="submit" class="btn-link">Save</button></td>
				<td><button name="next" type="submit">Next</button></td>
				<td><button name="back" type="submit">Back</button></td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>