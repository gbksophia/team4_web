package webtoon.comment;

public class CmtCheckVO {
	private String id;		//���ƿ� �ۼ��� ID
	private int like_ch;	//���ƿ� ���� 0�Ͻ� ������, 1�Ͻ� ���ƿ�, 2�Ͻ� �Ⱦ��
	private int star_ch;	//���� ���� ����
	private int mw_num;		//������ ���� ��ȣ
	private int cl_num;		//������ 
	private int cmt_num; 	//������ �ڸ�Ʈ ��ȣ
	
	public int getCmt_num() {
		return cmt_num;
	}
	public void setCmt_num(int cmt_num) {
		this.cmt_num = cmt_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLike_ch() {
		return like_ch;
	}
	public void setLike_ch(int like_ch) {
		this.like_ch = like_ch;
	}
	public int getStar_ch() {
		return star_ch;
	}
	public void setStar_ch(int star_ch) {
		this.star_ch = star_ch;
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
