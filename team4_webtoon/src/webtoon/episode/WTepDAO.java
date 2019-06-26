package webtoon.episode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import webtoon.list.MWdetailVO;
import webtoon.content.contentVO;

public class WTepDAO {
	private static WTepDAO instance=new WTepDAO();
	public static WTepDAO getInstance() {
		return instance;
	}
	private WTepDAO() {}
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/xe");
		return ds.getConnection();
	}
	
	public int getEPCount(int mw_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*)from (select cl_num,cl_title,cl_title_id,mw_num,cl_reg,cl_star, wt_ep_img from content,main_webtoon where cl_title_id=mw_num and mw_num=?)");
			pstmt.setInt(1, mw_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x= rs.getInt(1);  
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return x; 
	} //������ �� ���Ǽҵ� ������ ����
	
	public MWdetailVO getDetail(int mw_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MWdetailVO wtDetail=new MWdetailVO();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select mw.mw_num, mw.mw_title, mw.mw_writer,ww.value week, mw.mw_sum, mw.mw_tag,mw.mw_star,wg.value ger" 
					+" from main_webtoon mw, web_ger wg , web_week ww where  wg.web_st=mw.mw_gen and ww.num=mw.mw_week and mw_num=?");
					pstmt.setInt(1, mw_num);
					rs = pstmt.executeQuery();				
					if (rs.next()) {
						wtDetail.setmNum(rs.getInt("mw_num"));
						wtDetail.setWtTitle(rs.getString("mw_title"));
						wtDetail.setWtAuthor(rs.getString("mw_writer"));
						wtDetail.setDay(rs.getString("week"));
						wtDetail.setWtGenre(rs.getString("ger"));
						wtDetail.setWtTag(rs.getString("mw_tag"));
						wtDetail.setWtSumm(rs.getString("mw_sum"));
						wtDetail.setWtStar(rs.getInt("mw_star"));	
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
		return wtDetail;
	} //���� ������(�±�,�帣,�ٰŸ� ��)�� �����ϴ� �޼ҵ�
	
	
	public List getEpisodes(int mw_num,int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List webtoonEP=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * "+ 
					"from (select cl_num,cl_title,cl_title_id,mw_num,cl_reg,cl_star, cl_star_p,cl_star_sum,wt_ep_img, rowNum r "+
					"from (select cl_num,cl_title,cl_title_id,mw_num,cl_reg,cl_star,cl_star_p,cl_star_sum,wt_ep_img "+
					"from content,main_webtoon where cl_title_id=mw_num and mw_num=? order by cl_reg desc) order by cl_reg desc) where r >=? and r<=? ");
					pstmt.setInt(1, mw_num);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						webtoonEP = new ArrayList(); 
						do{ 
							contentVO  episode=new contentVO();
							episode.setWt_ep_img(rs.getString("wt_ep_img"));
							episode.setCl_title(rs.getString("cl_title"));
							episode.setMw_star(rs.getInt("cl_star"));
							episode.setCl_reg(rs.getTimestamp("cl_reg"));
							episode.setCl_num(rs.getInt("cl_num"));
							episode.setMw_star_p(rs.getInt("cl_star_p"));
							episode.setMw_star_sum(rs.getInt("cl_star_sum"));
							webtoonEP.add(episode); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return webtoonEP;
	} //������ ���Ǽҵ� ����Ʈ�� �����ϴ� �޼ҵ�
	
	public int getFirstEP(int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int fEP=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select *from (select cl_num,cl_title,cl_title_id,cl_reg,rowNum r "+
					"from (select cl_num,cl_title,cl_title_id,cl_reg from content where cl_title_id=? order by cl_reg asc) order by cl_reg asc) where r=1");
					pstmt.setInt(1, mw_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						fEP=rs.getInt("cl_num");
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return fEP;
	}
	
	//���� ���ƿ� ���� ��������
	public int getLoveWT(int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int love=0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select *from main_webtoon where mw_num=?");
					pstmt.setInt(1, mw_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						love=rs.getInt("mw_view");
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return love;
	} //���� ���ƿ� ���� ��������
	
	// love_check(SQL)���� "���� ���ƿ�" ���� ���� �d���ϱ�
	public int checkLovech(String id, int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int yOn=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("select * from love_check where u_id=? and mw_num=? and love_ch=1");
			pstmt.setString(1, id);
			pstmt.setInt(2, mw_num);
			rs=pstmt.executeQuery();
			//System.out.println("love_ch ���� ����");
			if(rs.next()) {
				yOn=rs.getInt("love_ch");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return yOn;
	} // love_check(SQL)���� "���� ���ƿ�" ���� ���� �d���ϱ�
	
	
	//  love_check(SQL)���� "���� ���ƿ�" ������ ��(1)���� ������Ʈ
	public void addLove(String id, int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("insert into love_check values(?,?,1)");
			pstmt.setString(1, id);
			pstmt.setInt(2, mw_num);
			pstmt.executeUpdate();
			//System.out.println("love_ch=1�� �߰�");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	} //  love_check(SQL)���� "���� ���ƿ�" ������ ��(1)���� ������Ʈ
	
	
	// ���� ���ƿ� ���� 1 ����
	public void addMWview(int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("update main_webtoon set mw_view=mw_view+1 where mw_num=?");
			pstmt.setInt(1, mw_num);
			pstmt.executeUpdate();	
			//System.out.println("main_webtoon�� mw_view+1 ����");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	} // ���� ���ƿ� ���� 1 ����
	
	
	
	// love_check(SQL)���� "���� ���ƿ�" ������ ����� ����
	public void deleteLove(String id, int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("delete love_check where u_id=? and mw_num=? and love_ch=1");
			pstmt.setString(1, id);
			pstmt.setInt(2, mw_num);
			pstmt.executeUpdate();	
			System.out.println("love_check���� �ƿ� ����");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}// love_check(SQL)���� "���� ���ƿ�" ������ ����� ����
	
	// ���� ���ƿ� ���� 1 ����
	public void deleteMWview (int mw_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement("update main_webtoon set mw_view=mw_view-1 where mw_num=?");
			pstmt.setInt(1, mw_num);
			pstmt.executeUpdate();	
			System.out.println("main_webtoon�� mw_view-1 ����");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	} // ���� ���ƿ� ���� 1 ����
	
	
	public WTepVO getWTContent(int cl_num, int mw_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WTepVO wtEP=new WTepVO();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select mw.mw_num,mw.mw_title,c.cl_title,c.cl_num,c.cl_content,c.cl_writer from content c,main_webtoon mw where mw.mw_num=c.cl_title_id and cl_num=? and mw_num=?");
					pstmt.setInt(1, cl_num);
					pstmt.setInt(2, mw_num);
					rs = pstmt.executeQuery();				
					if (rs.next()) {
						wtEP.setMw_title_id(rs.getInt("mw_num"));
						wtEP.setMwTitle(rs.getString("mw_title"));
						wtEP.setClTitle(rs.getString("cl_title"));
						wtEP.setClNO(rs.getInt("cl_num"));
						wtEP.setClContent(rs.getString("cl_content"));
						wtEP.setClWriter(rs.getString("cl_writer"));
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}		
		return wtEP;
	} //���� ���Ǽҵ� ������ �����ϴ� �޼ҵ�
	

	private MWdetailVO setWtStar(int int1) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<contentVO> getEpisodesForAdmin(int mw_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<contentVO> webtoonEP=new ArrayList<contentVO>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from content where cl_title_id=?"); 
					pstmt.setInt(1, mw_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {						
						do{ 
							contentVO  episode=new contentVO();
							episode.setCl_num(rs.getInt("cl_num"));
							episode.setCl_title(rs.getString("cl_title"));
							episode.setCl_reg(rs.getTimestamp("cl_reg"));
							webtoonEP.add(episode); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return webtoonEP;
	} //������ ���Ǽҵ� ����Ʈ�� �����ϴ� �޼ҵ�
	
	
	
	//���� ������ ������ ����� ���� �޼ҵ�
	public PrevNextEpVO getprevnextEP(int mw_num, int cl_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrevNextEpVO pEPn=new PrevNextEpVO();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * "+
				"from (select cl_num,cl_title,cl_title_id, lag(cl_num,1,0) over(order by cl_num) as prev_cl_num,lag(cl_title,1,'����') over(order by cl_num) as prev_cl_title,lead(cl_num,1,0) over(order by cl_num) as next_cl_num,lead(cl_title,1,'����') over(order by cl_num) as next_cl_title from content where cl_title_id=?) where cl_num=?");
			pstmt.setInt(1, mw_num);
			pstmt.setInt(2, cl_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				/*
				System.out.println(rs.getInt("cl_num"));
				System.out.println(rs.getString("cl_title"));
				System.out.println(rs.getInt("cl_title_id"));
				System.out.println(rs.getInt("prev_cl_num"));
				System.out.println(rs.getString("prev_cl_title"));
				System.out.println(rs.getInt("next_cl_num"));
				System.out.println(rs.getString("next_cl_title"));
				*/
				
				pEPn.setClN(rs.getInt("cl_num"));
				pEPn.setClT(rs.getString("cl_title"));
				pEPn.setClTid(rs.getInt("cl_title_id"));
				pEPn.setPrevClN(rs.getInt("prev_cl_num"));
				pEPn.setPrevClT(rs.getString("prev_cl_title"));
				pEPn.setNextClN(rs.getInt("next_cl_num"));
				pEPn.setNextClT(rs.getString("next_cl_title"));
	
	
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return pEPn;	
	}
	
	public List getEPtitles(int mw_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List EPtitle=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select cl_num,cl_title,cl_title_id,cl_reg,wt_ep_img from content where cl_title_id=? order by cl_reg desc");
					pstmt.setInt(1, mw_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						EPtitle = new ArrayList(); 
						do{ 
							contentVO  episode=new contentVO();
							episode.setWt_ep_img(rs.getString("wt_ep_img"));
							episode.setCl_title(rs.getString("cl_title"));
							episode.setCl_reg(rs.getTimestamp("cl_reg"));
							episode.setCl_num(rs.getInt("cl_num"));
							episode.setCl_title_id(rs.getInt("cl_title_id"));
							EPtitle.add(episode); 
						}while(rs.next());
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return EPtitle;
	} //������ ���Ǽҵ� ����Ʈ�� �����ϴ� �޼ҵ�
	
	
	public String getWriterEmail(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String emailA=null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from user_info i,user_state s where i.state=s.member_st and i.id=?");
					pstmt.setString(1, id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						emailA=rs.getString("email");
					}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return emailA;
	}
	
	
	
	
	
	
	
	
}
