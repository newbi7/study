package liverary.vo;

public class LoanByAccountVO {
	private int ano;
	private String aname;
	private String abirth;
	private String acreatedAt;
	private String aphone;
	private String aemail;
	private String aaddr;
	private int apoint;
	private int alevel;
	private String ausername;
	private int lno;
	private String bisbn;
	private String lcreatedat;
	private String lduedate;
	private String lreturnedAt;
	private String status_kor;
	
	public LoanByAccountVO() {
	}

	public LoanByAccountVO(int ano, String aname, String abirth, String acreatedAt, String aphone, String aemail,
			String aaddr, int apoint, int alevel, String ausername, int lno, String bisbn, String lcreatedat,
			String lduedate, String lreturnedAt) {
		super();
		this.ano = ano;
		this.aname = aname;
		this.abirth = abirth;
		this.acreatedAt = acreatedAt;
		this.aphone = aphone;
		this.aemail = aemail;
		this.aaddr = aaddr;
		this.apoint = apoint;
		this.alevel = alevel;
		this.ausername = ausername;
		this.lno = lno;
		this.bisbn = bisbn;
		this.lcreatedat = lcreatedat;
		this.lduedate = lduedate;
		this.lreturnedAt = lreturnedAt;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getAbirth() {
		return abirth;
	}

	public void setAbirth(String abirth) {
		this.abirth = abirth;
	}

	public String getAcreatedAt() {
		return acreatedAt;
	}

	public void setAcreatedAt(String acreatedAt) {
		this.acreatedAt = acreatedAt;
	}

	public String getAphone() {
		return aphone;
	}

	public void setAphone(String aphone) {
		this.aphone = aphone;
	}

	public String getAemail() {
		return aemail;
	}

	public void setAemail(String aemail) {
		this.aemail = aemail;
	}

	public String getAaddr() {
		return aaddr;
	}

	public void setAaddr(String aaddr) {
		this.aaddr = aaddr;
	}

	public int getApoint() {
		return apoint;
	}

	public void setApoint(int apoint) {
		this.apoint = apoint;
	}

	public int getAlevel() {
		return alevel;
	}

	public void setAlevel(int alevel) {
		this.alevel = alevel;
	}

	public String getAusername() {
		return ausername;
	}

	public void setAusername(String ausername) {
		this.ausername = ausername;
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public String getBisbn() {
		return bisbn;
	}

	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
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

	public String getStatus_kor() {
		return status_kor;
	}

	public void setStatus_kor(String status_kor) {
		this.status_kor = status_kor;
	}
	
	
}
