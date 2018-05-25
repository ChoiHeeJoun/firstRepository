package com.kh.jsp.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;

import com.kh.jsp.member.model.service.MemberService;
import com.kh.jsp.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/mInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MemberInsertServlet() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		String name = request.getParameter("userName");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = 
				request.getParameter("tel1")+"-"
				+request.getParameter("tel2")+"-"
				+request.getParameter("tel3");
		String address = 
				request.getParameter("zipCode")+", "
				+request.getParameter("address1")+", "
				+request.getParameter("address2");
		
		String hobby = String.join(", ", request.getParameterValues("hobby"));
		
		// Service 객체를 통한 로그인 정보 확인
		MemberService ms = new MemberService();
		
		Member m = new Member(id, pwd, name, gender, age, email, phone, address, hobby);
		System.out.println("회원 가입 시 전달 받은 값 : "+m);
		
		if(ms.selectMember(m) != null) {
			request.setAttribute("msg", "이미 존재하는 회원입니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		if(ms.insertMember(m) > 0) {
			System.out.println("회원 가입 완료! : "+m);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("msg", "회원 가입 중 에러가 발생하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
