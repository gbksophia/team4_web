package webtoon.episode;

public class PrevNextEpVO {
	private int ClN; //cl_num (���� ���Ǽҵ� ������ȣ)
	private String ClT; //cl_title (���� ���Ǽҵ� ����)
	private int clTid; //cl_title_id (���� ������ȣ)
	private int prevClN; //prev_cl_num (���� ���� ���Ǽҵ� ������ȣ )
	private String  prevClT; //prev_cl_title (���� ���� ���Ǽҵ� ����)
	private int nextClN; //next_cl_num (���� ���� ���Ǽҵ� ������ȣ)
	private String nextClT; //next_cl_title (���� ���� ���Ǽҵ� ����)

	public int getClN() {
		return ClN;
	}
	public void setClN(int clN) {
		ClN = clN;
	}
	public String getClT() {
		return ClT;
	}
	public void setClT(String clT) {
		ClT = clT;
	}
	public int getClTid() {
		return clTid;
	}
	public void setClTid(int clTid) {
		this.clTid = clTid;
	}
	public int getPrevClN() {
		return prevClN;
	}
	public void setPrevClN(int prevClN) {
		this.prevClN = prevClN;
	}
	public String getPrevClT() {
		return prevClT;
	}
	public void setPrevClT(String prevClT) {
		this.prevClT = prevClT;
	}
	public int getNextClN() {
		return nextClN;
	}
	public void setNextClN(int nextClN) {
		this.nextClN = nextClN;
	}
	public String getNextClT() {
		return nextClT;
	}
	public void setNextClT(String nextClT) {
		this.nextClT = nextClT;
	}
}
