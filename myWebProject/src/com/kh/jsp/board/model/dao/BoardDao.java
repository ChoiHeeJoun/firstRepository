package com.kh.jsp.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import static com.kh.jsp.common.JDBCTemplate.*;
import com.kh.jsp.board.model.vo.Board;

public class BoardDao {

	private Properties prop;

	public BoardDao() {
		prop = new Properties();

		String fileName = BoardDao.class.getResource("/config/board/board-query.properties").getPath();

		try {

			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Board> selectList(Connection con, int currentPage, int limit) {
		
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		// Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectList");
		
		
		try {
			// stmt = con.createStatement();
			// rset = stmt.executeQuery(query);
			
			pstmt = con.prepareStatement(query);
			
			// 조회할 숫자  startRow 와 endRow 계산
			int startRow = (currentPage -1) * limit + 1;
			int endRow = startRow + (limit - 1);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			
			while(rset.next()){
				
				
				int bno = rset.getInt(2);
				String btitle = rset.getString(3);
				String bcontent = rset.getString(4);
				String bwriter = rset.getString(5);
				int bcount = rset.getInt(6);
				String bfile = rset.getString(7);
				Date bdate = rset.getDate(8);
				
				list.add(new Board(bno, btitle, bcontent, bwriter, bcount, bfile, bdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertBoard(Connection con, Board b) {

		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertBoard");

		try {

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, b.getBtitle());
			pstmt.setString(2, b.getBcontent());
			pstmt.setString(3, b.getBwriter());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int getListCount(Connection con) {

		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;

		String query = prop.getProperty("listCount");

		try {

			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				result = rset.getInt(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			close(rset);
			close(stmt);
		}

		return result;
	}

	public Board selectOne(Connection con, int bno) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board b = new Board();
		
		String query = prop.getProperty("selectOne");
		
		try {
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			if( rset.next() ){
				
				b.setBno(bno);
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("USERNAME"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBoardfile(rset.getString("BOARDFILE"));
				b.setBdate(rset.getDate("BDATE"));
			}
			
		} catch (SQLException e){
			
			e.getStackTrace();
		} finally{
			
			close(rset);
			close(pstmt);
		}
		
		return b;
	}

	public int updateCount(Connection con, Board b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try{
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, b.getBno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e){
			
			e.getStackTrace();
		} finally{
			
			close(pstmt);
		}
		
		
		return result;
	}

}
