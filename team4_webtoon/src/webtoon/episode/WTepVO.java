package webtoon.episode;

import java.sql.Timestamp;

public class WTepVO {
	private int clNO;  //ȸ����ȣ
	private String clTitle; //������(���Ǽҵ� ����)
	private String mwTitle ;//���� ����
	private int mw_title_id; //���� ������ȣ
	private String clContent; //���� ����(�����̸�, ���)
	private Timestamp clReg; //�������
	private int clLike; //���ƿ� ��
	private String clWriter; //�۰�
	private int ep_view; //��ȸ��
	private int ep_star; //����
	private int ep_sp; //���� ������
	private int ep_ssum; //���� ������ �� ���� �հ�
	
	public int getClNO() {
		return clNO;
	}
	public void setClNO(int clNO) {
		this.clNO = clNO;
	}
	public String getClTitle() {
		return clTitle;
	}
	public void setClTitle(String clTitle) {
		this.clTitle = clTitle;
	}
	public String getMwTitle() {
		return mwTitle;
	}
	public void setMwTitle(String mwTitle) {
		this.mwTitle = mwTitle;
	}
	public int getMw_title_id() {
		return mw_title_id;
	}
	public void setMw_title_id(int mw_title_id) {
		this.mw_title_id = mw_title_id;
	}
	public String getClContent() {
		return clContent;
	}
	public void setClContent(String clContent) {
		this.clContent = clContent;
	}
	public Timestamp getClReg() {
		return clReg;
	}
	public void setClReg(Timestamp clReg) {
		this.clReg = clReg;
	}
	public int getClLike() {
		return clLike;
	}
	public void setClLike(int clLike) {
		this.clLike = clLike;
	}
	public String getClWriter() {
		return clWriter;
	}
	public void setClWriter(String clWriter) {
		this.clWriter = clWriter;
	}
	public int getEp_view() {
		return ep_view;
	}
	public void setEp_view(int ep_view) {
		this.ep_view = ep_view;
	}
	public int getEp_star() {
		return ep_star;
	}
	public void setEp_star(int ep_star) {
		this.ep_star = ep_star;
	}
	public int getEp_sp() {
		return ep_sp;
	}
	public void setEp_sp(int ep_sp) {
		this.ep_sp = ep_sp;
	}
	public int getEp_ssum() {
		return ep_ssum;
	}
	public void setEp_ssum(int ep_ssum) {
		this.ep_ssum = ep_ssum;
	}

}
