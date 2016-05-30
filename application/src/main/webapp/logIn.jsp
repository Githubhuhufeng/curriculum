<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="myscript">
	<script>
      //確認有輸入帳號密碼，且輸入為英文與數字
	 function chkinput()
	 {
		 var id=window.document.form1.id.value;
		 re = /\W/;
		 if(id=="")
		 {
			 alert("請輸入帳號!!");
			 document.form1.id.focus();
			 return false;
		 }
		 
		 else if(re.test(id))
		 {
			 alert("只允許輸入英文及數字");
			 document.form1.id.focus();
			 return false;
		 }
		 
		 var pwd=window.document.form1.password.value;
		 re = /\W/;
		 if(pwd=="")
		 {
			 alert("請輸入密碼!!");
			 document.form1.password.focus();
			 return false;
		 }
		 
		 else if(re.test(pwd))
		 {
			 alert("只允許輸入英文及數字");
			 document.form1.password.focus();
			 return false;
		 }
		 
		 return true;
	 }
	 </script>
    </jsp:attribute>
    <jsp:attribute name="mycontent">
		<h1>登入</h1>
       <form name="form1" method="post" action="login" onsubmit="return chkinput()">
       <table align="center" cellpadding="5px">
		<tr>
			<td>User Name:</td>
	   		<td><input name="id" type="text"></td>
		</tr>
		<tr>
			<td>Password:</td>
		   	<td><input name="password" type="password" size="20"></td>
		</tr>
	   	<tr>
		   	<td colspan="2" align="right"> <input type="submit" name="Submit" value="login"></td>
	   	</tr>
	  </table>
	 </form>
    </jsp:attribute>
    <jsp:attribute name="myendscript">
      <script>
	$(document).ready(function()
	{
		document.form1.id.focus();
	});
	</script>
    </jsp:attribute>
</t:genericpage>