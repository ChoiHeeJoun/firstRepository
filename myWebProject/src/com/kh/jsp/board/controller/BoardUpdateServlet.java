package com.kh.jsp.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Board;

@WebServlet("/update.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardUpdateServlet() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Btitle = request.getParameter("Btitle");
		String Bcontent = request.getParameter("Bcontent");
		int Bno = Integer.parseInt(request.getParameter("Bno"));
		
		Board b = new Board();
		b.setBtitle(Btitle);
		b.setBcontent(Bcontent);
		b.setBno(Bno);
		
		int result = new BoardService().updateBoard(b);
		
		String page = "";
		if(result > 0){
			page="views/board/boardDetail.jsp";
			request.setAttribute("b", new BoardService().selectOne(Integer.valueOf(Bno)));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
