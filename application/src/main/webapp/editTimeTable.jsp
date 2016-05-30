<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>
    <jsp:attribute name="mycontent">
		<h1>已選修課程列表</h1>
		<form action="teachers" method="POST">

			<table align="center" cellpadding="5px" border="1">
				<tr>
					<td>課程名稱</td>
					<td>上課地點</td>
					<td>課程學分</td>
					<td>所屬科系</td>
					<td>授課老師</td>
					<td>退選</td>
				</tr>
				<c:forEach items="${myClassList}" var="eachClass">
					<tr>
						<td>${ eachClass.className }</td>
						<td>${ eachClass.location }</td>
						<td>${ eachClass.credit }</td>
						<td>${ eachClass.dept }</td>
						<td>${ eachClass.teacher_Name }</td>
						<td><a onclick="delClass(${ eachClass.classId })">退出</a></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<h1>開設課程列表</h1>
		<form action="teachers" method="POST">

		<table align="center" cellpadding="5px" border="1">
			<tr>
				<td>課程名稱</td>
				<td>上課地點</td>
				<td>課程學分</td>
				<td>所屬科系</td>
				<td>授課老師</td>
				<td>選修</td>
			</tr>
			<c:forEach items="${classList}" var="eachClass">
			<tr>
				<td>${ eachClass.className }</td>
				<td>${ eachClass.location }</td>
				<td>${ eachClass.credit }</td>
				<td>${ eachClass.dept }</td>
				<td>${ eachClass.teacher_Name }</td>
				<td><a onclick="addClass(${ eachClass.classId })">加入</a></td>
			</tr>
			</c:forEach>
		</table>
		</form>
	</jsp:attribute>
	<jsp:attribute name="myendscript">
       <script>
			function addClass(id)
			{
				submitPostForm({"id":id,"type":"add"})
			}

			function delClass(id)
			{
				submitPostForm({"id":id,"type":"del"})
			}

			function submitPostForm(params)
			{
				var form = document.createElement("form");
				form.setAttribute("method", "post");
				form.setAttribute("action", "");

				for(var key in params)
				{
					if(params.hasOwnProperty(key))
					{
						var hiddenField = document.createElement("input");
						hiddenField.setAttribute("type", "hidden");
						hiddenField.setAttribute("name", key);
						hiddenField.setAttribute("value", params[key]);

						form.appendChild(hiddenField);
					}
				}

				document.body.appendChild(form);
				form.submit();
			}
	   </script>
</jsp:attribute>
</t:genericpage>


