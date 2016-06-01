<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:genericpage>
    <jsp:attribute name="mycontent">
		<h1>課程時間調整</h1>
		<form action="updateTime" method="POST">
			<table align="center" cellpadding="5px">
				<tr>
					<td>Class:</td>
					<td>
						<select name="classInfo">
							<c:forEach items="${classInfo}" var="eachClass">
								<option value="${eachClass.id}">${eachClass.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Time:</td>
					<td>
						<select name="time">
							<c:forEach items="${timeList}" var="time">
								<option value="${time.time_id}">${time.week}_${time.start_time}-${time.end_time}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" name="Submit"></td>
				</tr>
			</table>
			</form>
    </jsp:attribute>
</t:genericpage>
