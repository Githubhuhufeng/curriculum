<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="mycontent">
      <form action="register" method="POST">
		Name: <input type="text" name="name">
		<br />
		Email: <input type="text" name="email" />
		<br />
		Pasword: <input type="password" name="pass" />
		<input type="submit" value="Submit" />
		</form>
    </jsp:attribute>
</t:genericpage>