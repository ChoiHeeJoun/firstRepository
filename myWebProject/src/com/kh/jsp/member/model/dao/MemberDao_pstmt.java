package com.kh.jsp.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kh.jsp.member.model.vo.Member;

public class MemberDao_pstmt {

	public Member selectMember(Member m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try { // JDBC 관련 로직을 작성 시 SQLException이 발생할 수 있기 때문에
			  // 반드시 try-catch로 감싸 핸들링 해주어야 한다. 
			
			// 1. jdbc 드라이버 설정을 위한 Class 등록
			// ClassNotFoundException이 발생할 수 있으므로 try-catch로 핸들링한다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Database 연결을 위한 Connection 객체 생성
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"jh_jsp",
					"jh_jsp");
			
			// 2-1. 실행하는 트랜잭션이 자동을 커밋되는 것을 막고 직접 트랜잭션을 관리하기 위해
			// AutoCommit() 메소드를 통해 자동 커밋 기능을 해제한다.
			con.setAutoCommit(false);

			// 3. 실행하고자 하는 쿼리 생성
			// 아직 값이 정해지지 않은 부분은 '?'로 표기한다.
			String query = "SELECT * FROM MEMBER WHERE USERID = ?"
					+ " AND PASSWORD = ?";
					
			// 4. PreparedStatement을 생성하고 '?'로 선언된 부분을
			// 1부터 순서에 따라 값을 하나씩 채운다.
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());

			// 5. 쿼리를 실행하고 결과를 ResultSet에 기입
			rset = pstmt.executeQuery();
			
			// 6. ResultSet에 담긴 값을 받아 VO/DTO 객체에 기록한다.
			// ResultSet의 첫 행은 머릿글이므로, ResultSet.next() 메소드로
			// 다음 행에 기록될 실제 Row 값이 존재하는 지 확인하고 찾아와야 한다.
			if(rset.next()){
				member = new Member();
				
				// 6-1. 값을 순서대로 받아 오는 방법과 실제 컬럼 명을 통해 받아오는 두가지 방법이 있는데,
				// 순서대로 받아올 경우 첫번째 컬럼의 위치는 1이며,
				// 컬럼 명으로 받아올 경우 대소문자를 가리지 않는다.
				member.setUserId(m.getUserId());
				member.setPassword(m.getPassword());
				member.setUserName(rset.getString("USERNAME"));
				member.setGender(rset.getString("GENDER"));	
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLLDATE"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}finally{  // 7. Database 연관 객체들은 사용이 끝났을 때 반드시 모두 close하여,
			       // 이후에 다른 메소드에서 실행될 수 있는 query와 트랜잭션이 충돌하지 않도록 해야 한다.
				   // 이것은 에러가 발생할 경우에도 실행되어야 하기 때문에 finally에 기술한다.
			try {
				rset.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	public int insertMember(Member m) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"jh_jsp",
					"jh_jsp");
			
			
			// insert, update 사용 시 쿼리 문의 띄어쓰기에 반드시 주의하자.
			String query = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
			// 결과가 정상적으로 수행 되었다면 1 이상의 행의 갯수를 리턴한다.
			if (result > 0) con.commit();
			else con.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int updateMember(Member m) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"jh_jsp",
					"jh_jsp");
			
			String query = "UPDATE MEMBER SET PASSWORD = ?"
					+ ", EMAIL = ?, AGE = ?, PHONE = ?, ADDRESS = ?"
					+ ", HOBBY = ? WHERE USERID = ?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getEmail());
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getHobby());
			pstmt.setString(7, m.getUserId());
			
			result = pstmt.executeUpdate();
			
			if (result > 0) con.commit();
			else con.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteMember(String userId) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"jh_jsp",
					"jh_jsp");
			
			// insert 사용 시 쿼리 문의 띄어쓰기에 반드시 주의하자.
			String query = "DELETE FROM MEMBER WHERE USERID = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
			if (result > 0) con.commit();
			else con.rollback();
			
		} catch (Exception e) {
			
		}finally{
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
