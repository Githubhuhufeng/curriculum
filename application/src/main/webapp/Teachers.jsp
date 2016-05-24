<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="mycontent">
    <h1>老師註冊:</h1>
      <form action="Teachers" method="POST">
		TeacherID: <input type="text" name="id">
		<br />
		TeacherName: <input type="text" name="name">
		<br />
		Email: <input type="text" name="email" />
		<input type="submit" value="Submit" />
		</form>
    </jsp:attribute>
</t:genericpage>a