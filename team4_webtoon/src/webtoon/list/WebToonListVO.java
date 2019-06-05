package webtoon.list;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class WebToonListVO {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH-mm");
	
	private int num;	//���� ���� ��ȣ(���� ���̵�)
	private String title;	// ���� ����
	private String sub_title; //���� ������
	private Timestamp reg;	//���� ��� ��¥
	private String writer; 	//�۰�
	private String gen;		//�帣
	private int week;		//������Ʈ ����
	private int like;		//���ƿ� ����
	private int mag;		//Ư������
	private String tag;		//�±�
	private int star;		//�� ����
	private int start_p;	//���� ���� �ο�
	private String sum; //���� �ٰŸ�
	private int view;	//��ȸ��	
	private String ep_img; //ȸ���� �����

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getMag() {
		return mag;
	}
	public void setMag(int mag) {
		this.mag = mag;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getStart_p() {
		return start_p;
	}
	public void setStart_p(int start_p) {
		this.start_p = start_p;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public String getEp_img() {
		return ep_img;
	}
	public void setEp_img(String ep_img) {
		this.ep_img = ep_img;
	}
	
	
	

}
