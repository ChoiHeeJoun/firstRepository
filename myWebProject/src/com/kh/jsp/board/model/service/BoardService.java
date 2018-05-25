package com.kh.jsp.board.model.service;

import java.util.*;
import java.sql.*;
import static com.kh.jsp.common.JDBCTemplate.*;
import com.kh.jsp.board.model.dao.BoardDao;
import com.kh.jsp.board.model.vo.Board;

public class BoardService {

	public ArrayList<Board> selectList(int currentPage, int limit) {

		Connection con = getConnection();
		BoardDao bDao = new BoardDao();
		
		ArrayList<Board> list = bDao.selectList(con, currentPage, limit);
		
		close(con);
		
		return list;
	}

	public int insertBoard(Board b) {
		
		Connection con = getConnection();
		BoardDao bDao = new BoardDao();		
		
		int result = bDao.insertBoard(con, b);
		
		if(result > 0 ){
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int getListCount() {

		Connection con = getConnection();
		int result = new BoardDao().getListCount(con);
		
		close(con);
		
		return result;
		
	}

	public Board selectOne(int bno) {
		
		Connection con = getConnection();
		BoardDao bDao = new BoardDao();
		
		Board b = bDao.selectOne(con, bno);
		int result = 0;
		
		// SelectOne 서블릿이 호출 했을 경우에만 조회수를 증가시키는 방법
		StackTraceElement[] caller = new Throwable().getStackTrace();
		
		
		if(b != null && caller[1].getClassName().contains("SelectOne")){
			
			result = bDao.updateCount(con, b);
			
			if( result > 0 ) {
				
				commit(con);
			} else{
				
				rollback(con);
			}
		}
		close(con);
		
		return b;
		
	}

	public int updateBoard(Board b) {
		
		return 0;
	}

}
