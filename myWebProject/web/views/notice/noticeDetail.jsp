<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.jsp.notice.model.vo.*" %>
<%
    Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 상세 보기</title>
<style>
	.outer{
		width: 800px;
		height: 500px;
		background: black;
		color : white;
		margin-left: auto;
		margin-right: auto;
		margin-top : 50px;
	}
	table{
		width: 650px;
		padding: 20px;
		color: black;
		border: 1px solid white;
	}
	.tableArea{
		background: white;
		width: 650px;
		height: 350px;
		margin : auto;
	}
	span {
		font-size : 21pt;
		background: black;
		color : white;
	}
</style>
</head>
<body>
	<%@ include file="../common/header.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지 상세 내용</h2>
		<div class="tableArea">
			<table>
				<tr>
					<td>제목</td>
					<td colspan="3">
						<%=n.getNtitle() %>
					</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><%= n.getNwriter() %></td>
					<td>작성일</td>
					<td><%=n.getNdate() %></td>
				</tr>
				<tr>
					<td>내용</td>
				</tr>
				<tr>
					<td colspan="4">
					<br>
					<span><%=n.getNcontent().charAt(0) %></span>
					<%=n.getNcontent().substring(1) %>
					</td>
				</tr>
			</table>
			<br>
			<div align="center">
				<button onclick=
				"location.href='<%=request.getContextPath()%>/selectList.no'">
				목록으로 돌아가기
				</button>
				<% if( m != null &&
				      m.getUserId().equals("admin")) { %>
				<button onclick="location.href='<%=request.getContextPath()%>/nUpView.no?num=<%=n.getNno()%>'">
				수정하기
				</button>      
				<% } %>
			</div>
		</div>
	</div>
	
	<%@ include file="../common/footer.jsp" %>

</body>
</html>













