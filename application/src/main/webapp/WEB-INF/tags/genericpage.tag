<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="myscript" fragment="true" %>
<%@attribute name="mystyle" fragment="true" %>
<%@attribute name="mycontent" fragment="true" %>
<%@attribute name="myendscript" fragment="true" %>
<html>
	<head>
	<meta charset="utf-8">
	 <title>Curriculum-SPM</title>
    <link rel="shortcut icon" href="/image/favicon.ico" />
    <link href="kendoui/content/shared/styles/examples-offline.css" rel="stylesheet" />
    <link href="kendoui/styles/kendo.common.min.css" rel="stylesheet" />
    <link href="kendoui/styles/kendo.default.min.css" rel="stylesheet" />
    <link href="kendoui/content/shared/styles/examples.css" rel="stylesheet" />
    <script src="kendoui/content/shared/js/prettify.js"></script>
    <script src="kendoui/js/jquery.min.js"></script>
    <script src="kendoui/js/kendo.all.min.js"></script>
    <jsp:invoke fragment="myscript"/>
   	<jsp:invoke fragment="mystyle"/>
   	<style>
        body
        {
            background-image: url('kendoui/content/shared/images/patterns/pattern8.png');
        }
        .demo-section:not(.wide), #exampleWrap .box:not(.wide)
        {
            max-width:1000px;
        }
        .k-menu .k-item
        {
        	background-color:#991e21;
        }
        .k-menu-horizontal
        {
        	background-color:#991e21;
        }
        .k-menu .k-item>.k-link
        {
            color:white;
        }
    </style>
	</head>
  <body>
<div>
        <img src="image/header.png" style="width: 100%;height: auto;"/>
        <ul id="menu-images"></ul>
    </div>
    <div class="demo-section k-content">
        <jsp:invoke fragment="mycontent"/>
    </div>
    <script>
        $("#menu-images").kendoMenu({
            dataSource: [
                {
                    text: "Home", url:"/application/index.jsp"
                },
                {
                    text: "課表",
                    items: [
                        { text: "我的課表", url:"/application/viewTimeTable.jsp" },
                        { text: "編輯我的課表", url:"/application/editTimeTable" }
                    ]
                },
                {
                    text: "課程",
                    items: [
												{ text: "開課時間調整", url:"/application/editClassTime" },
												{ text: "新增開課老師", url:"/application/Teachers.jsp" },
                        { text: "新增課程", url:"/application/classForm" },
                        { text: "開設課程列表", url:"/application/classList" }
                    ]
                },
                {
                    text: "會員",
                    items: [
                        { text: "登入", url:"/application/logIn.jsp" },
                        { text: "註冊", url:"/application/register.jsp" }
                    ]
                }
            ]
        });
    </script>
	<jsp:invoke fragment="myendscript"/>
  </body>
</html>