package webtoon.comment;

import java.sql.Timestamp;


public class cmtVO {
	
	private int num;			//�ڸ�Ʈ ��ȣ
	private int like;			//���ƿ� ����
	private int hate;			//�Ⱦ�� ����
	private Timestamp reg;		//���� ��¥
	private String id;			//�ۼ��� ID
	private int state;			//���迩�� ����, 0�� ����/1�� ����
	private int mw_num;			//�ڸ�Ʈ�� �ۼ��� ���� ��ȣ
	private int cl_num;			//�ڸ�Ʈ�� �ۼ��� ���� ȸ����ȣ(0�Ͻ� �ش� ���� ���� ������)
	private String content;		//�ڸ�Ʈ ����
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getHate() {
		return hate;
	}
	public void setHate(int hate) {
		this.hate = hate;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getMw_num() {
		return mw_num;
	}
	public void setMw_num(int mw_num) {
		this.mw_num = mw_num;
	}
	public int getCl_num() {
		return cl_num;
	}
	public void setCl_num(int cl_num) {
		this.cl_num = cl_num;
	}
	
	
	
	
}
