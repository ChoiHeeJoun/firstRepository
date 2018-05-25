package com.kh.jsp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.member.model.vo.Member;

@WebServlet("/insert.bo")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardInsertServlet() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		
		Member m = (Member)session.getAttribute("member");
		String writer = m.getUserId();
		
		Board b = new Board();
		b.setBtitle(title);
		b.setBcontent(content);
		b.setBwriter(writer);
		
		int result = new BoardService().insertBoard(b);
		
		if (result > 0 ){
			response.sendRedirect(request.getContextPath()+"/selectList.bo"); // 다른 서블릿 호출
		} else {
			request.setAttribute("msg", "게시판 작성 실패!!");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
