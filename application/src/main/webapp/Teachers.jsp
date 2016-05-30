<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="mycontent">
    <h1>新增開課老師</h1>
      <form action="teachers" method="POST">
      
      <table align="center" cellpadding="5px">
	      	<tr>
	      		<td>TeacherID(Exp:T00000): </td>
	      		<td><input type="text" name="id"></td>
	      	</tr>
	      	<tr>
	      		<td>TeacherName: </td>
	      		<td><input type="text" name="name"></td>
	      	</tr>
	      	<tr>
	      		<td>Email: </td>
	      		<td><input type="text" name="email" /></td>
	      	</tr>
	      	<tr>
	      		<td colspan="2" align="right"><input type="submit" value="Submit" /></td>
	      	</tr>
      	</table>
		</form>
    </jsp:attribute>
</t:genericpage>a