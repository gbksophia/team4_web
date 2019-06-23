package webtoon.bookmark;

import java.sql.Timestamp;

public class BookmarkVO {
    private int bmNum; //�ϸ�ũ ��ȣ
    private String bmID; //�ϸ�ũ�� ���� ���̵�
    private int bmWNum; //�ϸ�ũ�� ���� ��ȣ
    private String bmWTitle; //�ϸ�ũ�� ���� ����
    private int bmCNum; //�ϸ�ũ�� ���� ȸ�� ��ȣ
    private String bmCTitle; //�ϸ�ũ�� ���� ȸ�� ����
    private String bmWriter; //�ϸ�ũ�� ���� ȸ�� ����
    private Timestamp bmReg; //�ϸ�ũ�� ��¥
    private String bmImg; //�ϸ�ũ�� ���Ǽҵ� ��ǥ �̹���
	
    public int getBmNum() {
		return bmNum;
	}
	public void setBmNum(int bmNum) {
		this.bmNum = bmNum;
	}
	public String getBmID() {
		return bmID;
	}
	public void setBmID(String bmID) {
		this.bmID = bmID;
	}
	public int getBmWNum() {
		return bmWNum;
	}
	public void setBmWNum(int bmWNum) {
		this.bmWNum = bmWNum;
	}
	public String getBmWTitle() {
		return bmWTitle;
	}
	public void setBmWTitle(String bmWTitle) {
		this.bmWTitle = bmWTitle;
	}
	public int getBmCNum() {
		return bmCNum;
	}
	public void setBmCNum(int bmCNum) {
		this.bmCNum = bmCNum;
	}
	public String getBmCTitle() {
		return bmCTitle;
	}
	public void setBmCTitle(String bmCTitle) {
		this.bmCTitle = bmCTitle;
	}
	public String getBmWriter() {
		return bmWriter;
	}
	public void setBmWriter(String bmWriter) {
		this.bmWriter = bmWriter;
	}
	public Timestamp getBmReg() {
		return bmReg;
	}
	public void setBmReg(Timestamp bmReg) {
		this.bmReg = bmReg;
	}
	public String getBmImg() {
		return bmImg;
	}
	public void setBmImg(String bmImg) {
		this.bmImg = bmImg;
	}    
	
}
