<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page import="java.util.Vector" %>

<t:genericpage>
	<jsp:attribute name="mystyle">
      <style>
		  .custom-event 
		  {
			  color: #fff;
			  text-shadow: 0 1px 0 #000;
		  }
		  .custom-all-day-event 
		  {
			  text-align: center;
			  text-transform: uppercase
		  }
		.k-scheduler-toolbar
		{
			display:none;
		}
		.k-scheduler-footer
		{
			display:none;
		}
		.k-scheduler
		{
		//height:100% !important;
		}
	  </style>
    </jsp:attribute>
	<jsp:attribute name="myscript">
       	<title></title>
		<link rel="stylesheet" href="kendoui/styles/kendo.common.min.css" />
		<link rel="stylesheet" href="kendoui/styles/kendo.default.min.css" />

		<script src="kendoui/js/jquery.min.js"></script>
		<script src="kendoui/js/angular.min.js"></script>
		<script src="kendoui/js/kendo.all.min.js"></script>
		<script src="kendoui/js/kendo.timezones.min.js"></script>
    </jsp:attribute>
    <jsp:attribute name="mycontent">
      <div id="example" ng-app="KendoDemos">
		  <div ng-controller="MyCtrl">
			  <div kendo-scheduler k-options="schedulerOptions">
				  <span k-event-template class='custom-event'>{{dataItem.title}}</span>
				  <div k-all-day-event-template class='custom-all-day-event'>{{dataItem.title}}</div>
			  </div>
		  </div>
	  </div>

    </jsp:attribute>

	<jsp:attribute name="myendscript">
       <script>
		   angular.module("KendoDemos", [ "kendo.directives" ])
				   .controller("MyCtrl", function($scope){
					   $scope.schedulerOptions = {
						   //date: new Date("2013/6/13"),
						   //startTime: new Date("2013/6/13 07:00 AM"),
						   //height: 100%
						   views: [
							   { type: "week", selected: true }
						   ],
						   timezone: "Asia/Taipei",
						   dataSource: {
							   batch: true,
							   transport: {
								   read: {
									   url: "viewTimeTable",
									   dataType: "json"
								   },
								   update: {
									   url: "updateTimeTable",
									   dataType: "json"
								   },
								   create: {
									   url: "data/data.json",
									   dataType: "json"
								   },
								   destroy: {
									   url: "data/data.json",
									   dataType: "json"
								   },
								   parameterMap: function(options, operation) {
									   if (operation !== "read" && options.models) {
										   return {models: kendo.stringify(options.models)};
									   }
								   }
							   },
							   schema: {
								   model: {
									   id: "taskId",
									   fields: {
										   taskId: { from: "TaskID", type: "number" },
										   title: { from: "Title", defaultValue: "No title", validation: { required: true } },
										   start: { type: "date", from: "Start" },
										   end: { type: "date", from: "End" },
										   startTimezone: { from: "StartTimezone" },
										   endTimezone: { from: "EndTimezone" },
										   description: { from: "Description" },
										   recurrenceId: { from: "RecurrenceID" },
										   recurrenceRule: { from: "RecurrenceRule" },
										   recurrenceException: { from: "RecurrenceException" },
										   ownerId: { from: "OwnerID", defaultValue: 1 },
										   isAllDay: { type: "boolean", from: "IsAllDay" }
									   }
								   }
							   },
							   filter: {
								   logic: "or",
								   filters: [
									   { field: "ownerId", operator: "eq", value: 1 },
									   { field: "ownerId", operator: "eq", value: 2 }
								   ]
							   }
						   },
						   resources: [
							   {
								   field: "ownerId",
								   title: "Owner",
								   dataSource: [
									   { text: "Alex", value: 1, color: "#f8a398" },
									   { text: "Bob", value: 2, color: "#51a0ed" },
									   { text: "Charlie", value: 3, color: "#56ca85" }
								   ]
							   }
						   ]
					   };
				   })
	   </script>
    </jsp:attribute>
</t:genericpage>