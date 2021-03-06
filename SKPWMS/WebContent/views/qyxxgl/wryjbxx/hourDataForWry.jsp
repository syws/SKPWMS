<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/frame/js/easyui/themes/icon.css">
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/frame/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	
	//格式化easyui datebox时间格式
	$("#ctime").datebox({
		panelHeight:260,
        formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); }
    });
	
	//给easyui datebox设置初始值
	var date = new Date();
	var nowtime = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
	$("#ctime").datebox("setValue",nowtime); 
        
	//双击tree，刷新datagrid
	$("#ctree").tree({
		onDblClick:function(node){
			var time = $("#ctime").datebox("getValue");
			var cId = node.id;
			//刷新Datagrid
			$('#datagrid').datagrid({  
			    url:'<%=path %>/hourDataController/findByList',  
			    queryParams:{  
			        "cId":cId,  
			        "time":time  
			    }  
			}); 
		}
	});
});

</script>

<style type="text/css">
.Fname{
	width: 175px;
	text-align: right;
}
.Fcontent{

}
tr{
	height: 35px;
	border:1px solid black;
	border-bottom: 1px solid red;
}
input{
	height: 25px;
	width: 220px;
}

</style>
</head>
<body class="easyui-layout">
	 <div data-options="region:'center'">
		<table id="datagrid" class="easyui-datagrid" style="width: 100%; height: 100%"
			toolbar="#toolbar" pagination="false" fit="true" rownumbers="true" fitColumns="true" singleSelect="true" striped="true">
			<thead>
				<tr>
					<th rowspan="2" data-options="field:'time'" width="10px" align="center"><a>时间</a></th>
					<th colspan="2">COD</th>
					<th colspan="2">氨氮</th>
					<th rowspan="2" data-options="field:'flow'" width="10px" align="center">总流量<br>(t)</th>
				</tr>
				<tr>
					<th data-options="field:'codC'" width="10px" align="center">浓度<br>(mg/L)</th>
					<th data-options="field:'codF'" width="10px" align="center">流量<br>(kg)</th>
					<th data-options="field:'nh3C'" width="10px" align="center">浓度<br>(mg/L)</th>
					<th data-options="field:'nh3F'" width="10px" align="center">流量<br>(kg)</th>
				</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'east',iconCls:'icon-reload',split:false" style="width:200px;">
		<div height="80%" style="padding-top: 10px;">
		<span style="font-weight: bold;padding-bottom: 10px;">总量控制器列表：</span>
			<ul id="ctree" data-options="url:'<%=path%>/tTcControlerController/findTTcControlersOfWry?id=${param.wry_id}'"  style="height:400px; overflow: auto; border: 1px solid black;"></ul>  
			
		</div>
		<div style="margin: 5px 0px;">
			日期：<input id="ctime" class="easyui-datebox" editable="false" style="width: 150px;">
		</div>
	</div>
</body>
</html>