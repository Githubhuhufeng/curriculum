<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="mycontent">
		<h1>註冊新帳號</h1>
      <form action="register" method="POST">
      	<table align="center" cellpadding="5px">
	      	<tr>
	      		<td>Name: </td>
	      		<td><input type="text" name="name"></td>
	      	</tr>
	      	<tr>
	      		<td>Email: </td>
	      		<td><input type="text" name="email" /></td>
	      	</tr>
	      	<tr>
	      		<td>Pasword: </td>
	      		<td><input type="password" name="pass" /></td>
	      	</tr>
	      	<tr>
	      		<td colspan="2" align="right"><input type="submit" value="Submit" /></td>
	      	</tr>
      	</table>
		</form>
    </jsp:attribute>
</t:genericpage>