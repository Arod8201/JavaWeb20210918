<%@page import="entity.Drink"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Drink Form</title>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
	
	<script>
	
	function updateAmount(id, amount){
		var updateAmount = prompt("修改 "+ id + " 號訂單的數量", amount);
		console.log(updateAmount);
		var updateUrl = '${pageContext.request.contextPath}/servlet/drink?updateRowId=' + id + '&updateAmount='+updateAmount;
		console.log(updateUrl);
		// 傳送
		location.href = updateUrl;
	}
	
	</script>
	
</head>
<body style="padding: 15px">

	<form class="pure-form" method="post"
		action="${pageContext.request.contextPath}/servlet/drink">
		<fieldset>
			<legend>Drink 訂單</legend>
			品名 : 
			<select name="id">
				<% if (request.getAttribute("drinks") != null) { %>
					<% for(Object item : (List)request.getAttribute("drinks")) { %>
						<% Drink drink =(Drink)item; %>
					<option value="<%=drink.getId() %>">
						<%=drink.getName() %>
						$<%=drink.getPrice() %>
						(庫存 : <%=drink.getStock() %>)			
					</option>
					<% } %>
				<% } %>
			</select>
			<p />
			數量 : <input type="number" name="amount">
			<p />
			<button type="submit" class="pure-button pure-button-primary">訂購</button>
			<button type="reset" class="pure-button pure-button-primary">清除</button>
		</fieldset>
	</form>

	${ list }
	
	<p />
	訂單明細 : 
	<table class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>row id(訂單編號)</th>
				<th>product id(商品編號)</th>
				<th>name</th>
				<th>amount</th>
				<th>subtotal</th>
				<th>memo</th>
				<th>update</th>
				<th>delete</th>
			</tr>
		</thead>
		<tbody>
			<% int rowId = 0; %>
			<% if (request.getAttribute("list") != null) { %>
				<% for (Object item : (List) request.getAttribute("list")) { %>
					<% Map map = (Map) item; %>
					<tr>
						<td><%=rowId %></td>
						<td><%=map.get("id")%></td>
						<td><%=map.get("name")%></td>
						<td><%=map.get("amount")%></td>
						<td><%=map.get("subtotal")%></td>
						<td><%=map.get("memo")%></td>
						<td>
							<% if (Boolean.parseBoolean(map.get("flag").toString())) { %>
								<span   style="cursor: pointer;text-decoration-color:bule;text-decoration: underline;"
										title="按我一下可以修改數量";
										onclick="updateAmount(<%=rowId%>, <%=map.get("amount")%>)">
									<%=map.get("amount") %>
							    </span>								
							<% } else{
									out.print(map.get("amount"));
							   } %>
						</td>
						<td><%=map.get("subtotal") %></td>
						<td><%=map.get("memo") %></td>
						<td>
							<% if (Boolean.parseBoolean(map.get("flag").toString())) { %>
								<button type="button"
										onclick="updateAmount(<%=rowId%>, <%=map.get("amount")%>)"
										class="pure-button">修改數量</button>
							<% } %>
						</td>
						<td>
							<% if (Boolean.parseBoolean(map.get("flag").toString())) { %>
								<button type="button"
									onclick="location.href='${pageContext.request.contextPath}/servlet/drink?deleteRowId=<%=rowId %>';"
									class="pure-button">刪除</button>								
							<% } %>
						</td>
					</tr>
					<% rowId++; %>
				<% } %>
			<% } %>
		</tbody>
	</table>

</body>
</html>