package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class SearchDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public SearchDAO() throws SQLException{
		conn = ConnectionUtil.getConnection();
	}
	
	public ArrayList<SearchVO> getAddrs(String search, String select) throws SQLException{
		ArrayList<SearchVO> list = new ArrayList<SearchVO>();

		if(select.equals("0")) {	//select �� 0 �� �� = ������ ��
		StringBuffer sql = new StringBuffer();
		sql.append("select mw_title, mw_writer, mw_gen, mw_tag from main_webtoon where mw_title like '%' || :search || '%' ");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1,search);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			SearchVO vo = new SearchVO();
			vo.setTitle(rs.getString("mw_title"));
			vo.setWriter(rs.getString("mw_writer"));
			vo.setGen(rs.getString("mw_gen"));
			vo.setTag(rs.getString("mw_tag"));
			list.add(vo);
			
			}
		}
		
		else if(select.equals("1")) {	//select�� 1 �� �� = �۰��� ��
		StringBuffer sql = new StringBuffer();
		sql.append("select mw_title, mw_writer, mw_gen, mw_tag from main_webtoon where mw_writer like '%' || :search || '%' ");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		pstmt.setString(1,search);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			SearchVO vo = new SearchVO();
			vo.setTitle(rs.getString("mw_title"));
			vo.setWriter(rs.getString("mw_writer"));
			vo.setGen(rs.getString("mw_gen"));
			vo.setTag(rs.getString("mw_tag"));
			list.add(vo);
			
			}
		}
		else {		//select �� �������� ��(2) = �±�, �� �߰� �ϰ� ������ equals("2")�� �ΰ� �߰��Ѵ�. 
			StringBuffer sql = new StringBuffer();
			sql.append("select mw_title, mw_writer, mw_gen, mw_tag from main_webtoon where mw_tag like '%' || :search || '%' ");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,search);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				SearchVO vo = new SearchVO();
				vo.setTitle(rs.getString("mw_title"));
				vo.setWriter(rs.getString("mw_writer"));
				vo.setGen(rs.getString("mw_gen"));
				vo.setTag(rs.getString("mw_tag"));
				list.add(vo);
			}
		}
		if(rs != null) rs.close();
		if (pstmt != null) pstmt.close();
		if (conn != null) conn.close();
		
		return list;
	}

}
