<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="mycontent">
    <form action="ClassController" method="POST">
		Name: <input type="text" name="name">
		<br />
		Teacher: <input type="text" name="teacher" />
		<br />
		Location: <input type="text" name="location" />
    <br />
		Credit: <input type="text" name="credit" />
    <br />
    Department: <input type="text" name="dept" />
    <br />
		<input type="submit" value="Submit" />
		</form>
    </jsp:attribute>
</t:genericpage>
