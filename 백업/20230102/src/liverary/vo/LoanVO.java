package liverary.vo;

public class LoanVO {
	private int lno;
	private String lcreatedat;
	private String lduedate;
	private String lreturnedAt;
	private String bisbn;
	private String btitle;
	private String bdate;
	private int bpage;
	private int bprice;
	private String bauthor;
	private String btranslator;
	private String bsupplement;
	private String bpublisher;
	private boolean available;
	private int ano;
	private String available_kor;
	
	public int getAno() {
		return ano;
	}

	
	public void setAno(int ano) {
		this.ano = ano;
	}



	public LoanVO() {
	}

	

	public String getAvailable_kor() {
		return available_kor;
	}



	public void setAvailable_kor(String available_kor) {
		this.available_kor = available_kor;
	}
	


	public LoanVO(int lno, String lcreatedat, String lduedate, String lreturnedAt, String bisbn, String btitle,
			String bdate, int bpage, int bprice, String bauthor, String btranslator, String bsupplement,
			String bpublisher, boolean available, int ano, String available_kor) {
		super();
		this.lno = lno;
		this.lcreatedat = lcreatedat;
		this.lduedate = lduedate;
		this.lreturnedAt = lreturnedAt;
		this.bisbn = bisbn;
		this.btitle = btitle;
		this.bdate = bdate;
		this.bpage = bpage;
		this.bprice = bprice;
		this.bauthor = bauthor;
		this.btranslator = btranslator;
		this.bsupplement = bsupplement;
		this.bpublisher = bpublisher;
		this.available = available;
		this.ano = ano;
		this.available_kor = available_kor;
	}


	public int getLno() {
		return lno;
	}



	public void setLno(int lno) {
		this.lno = lno;
	}



	public String getLcreatedat() {
		return lcreatedat;
	}



	public void setLcreatedat(String lcreatedat) {
		this.lcreatedat = lcreatedat;
	}



	public String getLduedate() {
		return lduedate;
	}



	public void setLduedate(String lduedate) {
		this.lduedate = lduedate;
	}



	public String getLreturnedAt() {
		return lreturnedAt;
	}



	public void setLreturnedAt(String lreturnedAt) {
		this.lreturnedAt = lreturnedAt;
	}



	public String getBisbn() {
		return bisbn;
	}



	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}



	public String getBtitle() {
		return btitle;
	}



	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}



	public String getBdate() {
		return bdate;
	}



	public void setBdate(String bdate) {
		this.bdate = bdate;
	}



	public int getBpage() {
		return bpage;
	}



	public void setBpage(int bpage) {
		this.bpage = bpage;
	}



	public int getBprice() {
		return bprice;
	}



	public void setBprice(int bprice) {
		this.bprice = bprice;
	}



	public String getBauthor() {
		return bauthor;
	}



	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}



	public String getBtranslator() {
		return btranslator;
	}



	public void setBtranslator(String btranslator) {
		this.btranslator = btranslator;
	}



	public String getBsupplement() {
		return bsupplement;
	}



	public void setBsupplement(String bsupplement) {
		this.bsupplement = bsupplement;
	}



	public String getBpublisher() {
		return bpublisher;
	}



	public void setBpublisher(String bpublisher) {
		this.bpublisher = bpublisher;
	}



	public boolean isAvailable() {
		return available;
	}



	public void setAvailable(boolean available) {
		this.available = available;
	}
}
