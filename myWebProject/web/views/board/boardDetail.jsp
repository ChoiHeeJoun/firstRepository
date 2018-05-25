<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.jsp.board.model.vo.*"%>
<%
	Board b = (Board)request.getAttribute("b");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.outer {
	width: 800px;
	height: 500px;
	background: black;
	color: white;
	margin-left: auto;
	margin-right: auto;
	margin-top: 50px;
}

td {
	border: 1px solid white;
}

.tableArea {
	border: 1px solid white;
	width: 800px;
	height: 350px;
	margin-left: auto;
	margin-right: auto;
}

#content {
	height: 230px;
}

.replyArea {
	width: 800px;
	/* height:300px; */
	color: white;
	background: black;
	margin-left: auto;
	margin-right: auto;
}
</style>
<title>게시글 상세 내용</title>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<div class="outer">
		<br>
		<h2 align="center">게시글 상세 내용</h2>
		<div class="tableArea">
			<table align="center" width="800px">
				<tr>
					<td>제목</td>
					<td colspan="5"><span><%=b.getBtitle()%></span></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><span><%=b.getBwriter()%></span></td>
					<td>작성일</td>
					<td><span><%=b.getBdate()%></span></td>
					<td>조회수</td>
					<td><span><%=b.getBcount()%></span></td>
				</tr>
				<%
					if (b.getBoardfile() != null && b.getBoardfile() != "") {
				%>
				<tr>
					<td>첨부파일</td>
					<td colspan="5"><a href="<%=b.getBoardfile()%>"><%=b.getBoardfile()%></a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td colspan="6">내용</td>
				</tr>
				<tr>
					<td colspan="6">
						<p id="content"><%=b.getBcontent()%>
					</td>
				</tr>
			</table>
			<br>
		</div>
		<div align="center">
			<button
				onclick="location.href='<%=request.getContextPath()%>/selectList.bo'">메뉴로
				돌아가기</button>
			<%
				if (m != null && m.getUserName().equals(b.getBwriter())) {
			%>
			<button
				onclick="location.href='<%=request.getContextPath()%>/bUpView.bo?num='+<%=b.getBno()%>">수정하기</button>
			<%
				}
			%>
		</div>
	</div>
	<div class="replyArea">
		<div class="replyWriteArea">
			<table align="center">
				<tr>
					<td>댓글 작성</td>
					<td><textArea rows="3" cols="80" id="replyContent"></textArea></td>
					<td><button id="addReply">댓글 등록</button></td>
				</tr>
			</table>
		</div>
		<div id="replySelectArea">
			<table id="replySelectTable" border="1" align="center">
			</table>
		</div>
	</div>
	<script>
   </script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>