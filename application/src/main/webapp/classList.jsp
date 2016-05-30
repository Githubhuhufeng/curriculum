<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
    <jsp:attribute name="mycontent">
    <h1>老師開設課程列表</h1>
      <form action="teachers" method="POST">

      <table align="center" cellpadding="5px" border="1">
		  <tr>
			  <td>課程名稱</td>
			  <td>上課地點</td>
			  <td>課程學分</td>
			  <td>所屬科系</td>
			  <td>授課老師</td>
		  </tr>
		  <c:forEach items="${classList}" var="eachClass">
	      	<tr>
	      		<td>${ eachClass.className }</td>
				<td>${ eachClass.location }</td>
				<td>${ eachClass.credit }</td>
				<td>${ eachClass.dept }</td>
				<td>${ eachClass.teacher_Name }</td>
	      	</tr>
		  </c:forEach>
      	</table>
		</form>
	</jsp:attribute>
</t:genericpage>
