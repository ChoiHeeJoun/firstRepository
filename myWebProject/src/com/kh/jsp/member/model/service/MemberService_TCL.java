package com.kh.jsp.member.model.service;

import java.sql.*;
import static com.kh.jsp.common.JDBCTemplate.*;
import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;

public class MemberService_TCL {
	
	public MemberService_TCL() {}
	
	public Member selectMember(Member m) {
		Connection con = getConnection();
		MemberDao mDao = new MemberDao();
		
		Member member = mDao.selectMember(con, m);
		
		close(con);
		
		return member;
	}

	public int insertMember(Member m) {
		Connection con = getConnection();
		MemberDao mDao = new MemberDao();
		
		int result = mDao.insertMember(con, m);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int updateMember(Member m) {
		Connection con = getConnection();
		MemberDao mDao = new MemberDao();
		
		int result = mDao.updateMember(con, m);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId) {
		Connection con = getConnection();
		MemberDao mDao = new MemberDao();
		
		int result = mDao.deleteMember(con, userId);
		
		if(result>0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

}
