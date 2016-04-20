<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="java.util.Vector" %>

<t:genericpage>
    <jsp:attribute name="mycontent">
      <table border="1">
      	<tr>
      		<td>&nbsp;</td>
      		<td>星期一</td>
      		<td>星期二</td>
      		<td>星期三</td>
      		<td>星期四</td>
      		<td>星期五</td>
      		<td>星期六</td>
      		<td>星期日</td>
      	</tr>
      	<c:forEach begin="1" end="14" var="col">
      	<tr>
      		<td>第 ${col}節</td>
	      	<c:forEach begin="1" end="7" var="row">
	      		<td>
	      		 ${timeTable[col][row].get(ClassName)}
	      		 ${timeTable[col][row].get(ClassLocation)}
	      		</td>
	      	</c:forEach>
      	</tr>
      	</c:forEach>
      </table>
    </jsp:attribute>
</t:genericpage>