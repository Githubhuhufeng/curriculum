<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>
    <jsp:attribute name="mycontent">
		<h1>新增課程</h1>
		<form action="classController" method="POST">
			<table align="center" cellpadding="5px">
				<tr>
					<td>Name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Teacher:</td>
					<td>
						<select name="teacher">
							<c:forEach items="${teacherList}" var="eachTeacher">
								<option value="${eachTeacher.teacherID}">${ eachTeacher.teacher_Name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Location:</td>
					<td><input type="text" name="location"></td>
				</tr>
				<tr>
					<td>Credit:</td>
					<td><input type="text" name="credit"></td>
				</tr>
				<tr>
					<td>Department:</td>
					<td><input type="text" name="dept"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" name="Submit"></td>
				</tr>
			</table>
			</form>
    </jsp:attribute>
</t:genericpage>
