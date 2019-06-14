package webtoon.list;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class WebToonListDAO {
	private WebToonListDAO() {}
	
	static private WebToonListDAO instance = new WebToonListDAO();
	
	public static WebToonListDAO getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	Statement st;
	SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd HH:mm");
	
	
	private Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			Context env = (Context)ctx.lookup("java:comp/env");
			DataSource ds = (DataSource)env.lookup("jdbc/xe");
			conn = ds.getConnection();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}//connection Ǯ�� �����ϴ� �޼ҵ�
	
	public int getCount(int week) {
		conn = getConnection(); 
		int result=0;
		try {
			String sql = "select count(*) from main_webtoon where MW_WEEK=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, week);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return result;
		
	}//���Ϻ� ���� ������ ����
	
	
	public int getCount() {
		conn = getConnection(); 
		int result=0;
		try {
			String sql = "select count(*) from main_webtoon";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return result;
		
	}//��ä ���� ���� ���� ������ ����
	
	public ArrayList<WebToonListVO> getWeeklyWebtoon(int week){
		conn = getConnection();
		ArrayList<WebToonListVO> list = new ArrayList<WebToonListVO>();
		try {
			String sql = "select rownum r, mw_num, mw_title, mw_sub_title, mw_reg, mw_writer, value gen, mw_week, mw_like, "
					+ "mw_mag,mw_tag, mw_star, mw_star_p from (select rownum r, mw_num, mw_title, mw_sub_title, mw_reg, mw_writer, mw_gen, mw_week, mw_like, "
					+ "mw_mag,mw_tag, mw_star, mw_star_p from (select * from main_webtoon where MW_WEEK=? order by MW_LIKE asc)), WEB_GER where web_st = mw_gen";
			// ������ �������� ��������, ���Ϻ� ������ ã�� sql��
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, week);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WebToonListVO vo = new WebToonListVO();
				vo.setNum(rs.getInt("mw_num"));
				vo.setTitle(rs.getString("mw_title"));
				vo.setSub_title(rs.getString("mw_sub_title"));
				vo.setReg(rs.getTimestamp("mw_reg"));
				vo.setWriter(rs.getString("mw_writer"));
				vo.setGen(rs.getString("gen"));
				vo.setWeek(rs.getInt("mw_week"));
				vo.setLike(rs.getInt("mw_like"));
				vo.setMag(rs.getInt("mw_mag"));
				vo.setTag(rs.getString("mw_tag"));
				vo.setStar(rs.getInt("mw_star"));
				vo.setStart_p(rs.getInt("mw_star_p"));
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return list;

	}//���Ϻ� ���� ����Ʈ�� �����ϴ� �޼ҵ�
	
	public void insertWebtoon(WebToonListVO main_webtoon) throws SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(
					"insert into main_webtoon values(main_webtoon_seq.nextval,?,?,sysdate,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, main_webtoon.getTitle());
			pstmt.setString(2, main_webtoon.getSub_title());
			pstmt.setString(3, main_webtoon.getWriter());
			pstmt.setString(4, main_webtoon.getGen());
			pstmt.setInt(5, main_webtoon.getWeek());
			pstmt.setInt(6, main_webtoon.getLike());
			pstmt.setInt(7, main_webtoon.getMag());
			pstmt.setString(8, main_webtoon.getTag());
			pstmt.setInt(9, main_webtoon.getStar());
			pstmt.setInt(10, main_webtoon.getStart_p());
			pstmt.setString(11, main_webtoon.getSum());
			pstmt.setInt(12, main_webtoon.getView());

			pstmt.executeUpdate();
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(pstmt != null)try {pstmt.close();}catch(SQLException ex) {}
			if(conn != null) try {conn.close();}catch(SQLException ex) {}
		}
	}
	

	public void setTodayrecom(String today) {
		int count = 0; //�������� ����
		HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
		//����Ʈ ��ȣ�� ��ȭ ��ȣ�� ���� �Է��� ���� ���� map�� ��� key : ����Ʈ ��ȣ(rownum), value : ���� ��ȣ
		try {
		conn = getConnection();
		HashSet<Integer> hs = new HashSet<Integer>();
		//�ߺ��Ǿ� ������ ���� �����ϱ� ���� hashmap ���
		
		String sql = "select count(*) from main_webtoon where mw_week!=0";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		rs.next();
		count = rs.getInt(1);
		// ���� ������ �� ������ ����
		
		sql = "select rownum r, mw_num from (select * from main_webtoon where mw_week!=0)";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			mp.put(rs.getInt("r"), rs.getInt("mw_num"));
		}
		//map ����Ʈ�� ������ mw_num�� �Է��Ѵ�.
		
		
		for(;hs.size()<5;) {
			int recom = (int)(Math.random()*count)+1;
			//��ü ���� �������� ������ �����Ͽ� recom�� ����, (rownum���� ������ ���ڴ� 1���� �̹Ƿ� +1�� �Ͽ� ����)
			hs.add(mp.get(recom));
			//�������� �޾� hashset�� hs ����Ʈ�� ���� ��ȣ�� ����
			
		}//hashset�� ������ 5���� �ɶ����� �ݺ��Ͽ� 
		
		Iterator<Integer> it = hs.iterator();
		//���ʴ�� hs�� ����ϱ� ���� iterator ���
		sql = "insert into today_recom values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, today);
		int co = 2;//�̹� 1��°�� �ذ�����Ƿ� 2���� �ֱ� ���� ����
		while(it.hasNext()) {	//it�� 5���� ��������Ƿ� 5���� ��µ�
			pstmt.setInt(co, it.next());
			//it �ȿ� �ִ� ���� �Է�
			co++;
			//co���� ������Ų��.
		}
		pstmt.executeUpdate();
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
			
	}//������ ��õ ���� ����Ʈ ���� 
	
	public ArrayList<WebToonListVO> getTodayrecom(String today) {
		ArrayList<WebToonListVO> list = new ArrayList<WebToonListVO>();
		try {
		conn = getConnection();
		String sql = "select * from today_recom where today=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, today);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			for(int i =1; i<6; i++) {
				sql = "select  mw_num, mw_title, mw_sub_title, mw_reg, mw_writer, value gen, mw_week, mw_like, mw_mag,mw_tag, mw_star, mw_star_p from "
						+ "(select * from main_webtoon where mw_num=?), WEB_GER where web_st = mw_gen";
				pstmt = conn.prepareStatement(sql);
				int mw_num = rs.getInt("wb"+i);
				//rs���� ���� ��ȣ�� Ȯ���Ͽ� mw_num�� ����
				pstmt.setInt(1, mw_num);
				ResultSet rs2 = pstmt.executeQuery();
				//rs1�� ���� ��� ����ؾ��ϹǷ� rs2�� ����
				
				if(rs2.next()) {
					WebToonListVO vo = new WebToonListVO();
					vo.setNum(rs2.getInt("mw_num"));
					vo.setTitle(rs2.getString("mw_title"));
					vo.setSub_title(rs2.getString("mw_sub_title"));
					vo.setReg(rs2.getTimestamp("mw_reg")); 
					vo.setWriter(rs2.getString("mw_writer"));
					vo.setGen(rs2.getString("gen"));
					vo.setWeek(rs2.getInt("mw_week"));
					vo.setLike(rs2.getInt("mw_like"));
					vo.setMag(rs2.getInt("mw_mag"));
					vo.setTag(rs2.getString("mw_tag"));
					vo.setStar(rs2.getInt("mw_star"));
					vo.setStart_p(rs2.getInt("mw_star_p"));
					list.add(vo);
					//��õ ���� ������ list�� ����
				}//if�� ����
			}//for�� ����
		}//if�� ����
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
			return list;
	}//������ ��õ ����Ʈ ����
	
	public int getGenView(int gen) {
		conn = getConnection(); 
		int result=0;
		try {
			String sql = "select * from main_webtoon where mw_gen = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gen);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result += rs.getInt("mw_view");
				//result�� ��ȸ���� ����
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return result;
	}//�帣�� �� ��ȸ���� ����
	
	public ArrayList<WebtoonListForAdminVO> getListForAdmin(){
		conn = getConnection();
		ArrayList<WebtoonListForAdminVO> list = new ArrayList<WebtoonListForAdminVO>();
		try {
			String sql = "select mw_num num, mw_title title, gen, week, writer, value mag from"
					+ " (select mw_num, mw_title, gen, value week, writer, mw_mag from"
					+ " (select mw_num, mw_title, value gen, mw_week, mw_writer writer, mw_mag from"
					+ " main_webtoon, web_ger where mw_gen=web_st), web_week where mw_week = web_week.num), web_mag where mw_mag = mag_st";
			// num, title, gen, week, writer, mag�� ã�� sql��
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WebtoonListForAdminVO vo = new WebtoonListForAdminVO();
				vo.setNum(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setGen(rs.getString(3));
				vo.setWeek(rs.getString(4));
				vo.setWriter(rs.getString(5));
				vo.setMag(rs.getString(6));
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}//�����ڿ� ���� ��� �������� ���� �޼ҵ�
	
	public ArrayList<String> getGen(){
		conn = getConnection();
		ArrayList<String> list = new ArrayList<String>();
		try {
			String sql = "select value from web_ger";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}//���� �帣 ���� �׸��� �ҷ����� �޼ҵ�
	
	public ArrayList<String> getMag(){
		conn = getConnection();
		ArrayList<String> list = new ArrayList<String>();
		try {
			String sql = "select value from web_mag";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
		
		return list;
	}//���� �帣 ���� �׸��� �ҷ����� �޼ҵ�
	
	public void updateGer(int num, int ger) {
		conn = getConnection();
		//int state = changeGer(ger);
		try {
			String sql = "update main_webtoon set mw_gen=? where mw_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ger);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
	}//main_webtoon�� �帣�� �������ִ� �޼ҵ�
	
	public void updateWeek(int num, int week) {
		conn = getConnection();
		try {
			String sql = "update main_webtoon set mw_week=? where mw_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, week);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
	}//main_webtoon�� mw_week �׸��� �������ִ� �޼ҵ�
	
	
	public void updateMag(int num, int mag) {
		conn = getConnection();
		try {
			String sql = "update main_webtoon set mw_mag=? where mw_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mag);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs !=null) { try {rs.close();}catch(SQLException e) {e.printStackTrace();}}
			if(pstmt != null) {try{pstmt.close();}catch(SQLException e) {e.printStackTrace();}}
			if(conn !=null) {try{conn.close();}catch(SQLException e) {e.printStackTrace();}}
		}
	}//main_webtoon�� mw_mag �׸��� �������ִ� �޼ҵ�
	
	
}

	
	

