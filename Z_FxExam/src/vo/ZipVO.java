package vo;

public class ZipVO {
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String bunji;
	private String ri;
	private String bldg;
	private int seq;
	
	// ibatis는 내부에서 기본생성자로 생성을 하므로 기본생성자를 만들어줘야한다.
	public ZipVO() {}
	
	public ZipVO(String zipcode, String sido, String gugun, String dong, String bunji, String ri, String bldg,
			int seq) {
		super();
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.bunji = bunji;
		this.ri = ri;
		this.bldg = bldg;
		this.seq = seq;
	}


	public String getZipcode() {
		return zipcode;
	}


	public String getSido() {
		return sido;
	}


	public String getGugun() {
		return gugun;
	}


	public String getDong() {
		return dong;
	}


	public String getBunji() {
		return bunji;
	}


	public String getRi() {
		return ri;
	}


	public String getBldg() {
		return bldg;
	}


	public int getSeq() {
		return seq;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public void setSido(String sido) {
		this.sido = sido;
	}


	public void setGugun(String gugun) {
		this.gugun = gugun;
	}


	public void setDong(String dong) {
		this.dong = dong;
	}


	public void setBunji(String bunji) {
		this.bunji = bunji;
	}


	public void setRi(String ri) {
		this.ri = ri;
	}


	public void setBldg(String bldg) {
		this.bldg = bldg;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
