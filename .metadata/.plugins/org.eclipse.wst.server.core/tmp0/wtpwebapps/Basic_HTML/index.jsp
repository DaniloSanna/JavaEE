<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Basic Java HTML</title>
</head>
<body>
	<form action="ServLetLogin" method="post">
		<table border="0">
			<tr>
				<td>Login:</td>
				<td><input name="login" type="text"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="pass" type="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="enviar"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><h4>${msg}</h4></td>
			</tr>
		</table>
	</form>
</body>
</html>