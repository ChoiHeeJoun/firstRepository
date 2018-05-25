package com.kh.jsp.member.model.service;

import com.kh.jsp.member.model.dao.MemberDao;
import com.kh.jsp.member.model.vo.Member;
import java.sql.*;
import static com.kh.jsp.common.JDBCTemplate.*;

public class MemberService {
	private MemberDao mDao;
	
	public MemberService() {
		mDao = new MemberDao();
	}
	
	public Member selectMember(Member m) {
		Connection con = getConnection();
		
		Member member = mDao.selectMember(con, m);
		
		close(con);
		
		return member;
	}

	public int insertMember(Member m) {
		Connection con = getConnection();
	
		int result = mDao.insertMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int updateMember(Member m) {
		Connection con = getConnection();
		
		int result = mDao.updateMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId) {
		Connection con = getConnection();
		
		int result = mDao.deleteMember(con, userId);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

}
