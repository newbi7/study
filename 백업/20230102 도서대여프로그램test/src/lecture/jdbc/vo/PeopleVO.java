package lecture.jdbc.vo;

public class PeopleVO {

	private String pid;
	private String ppw;
	private String pname;
	private String pssnumber;
	private String pphone;
	private int pbnumber;
	private int ppoint;
	private String ptier;
	
	public PeopleVO() {
	}


	public PeopleVO(String pid, String ppw, String pname, String pssnumber) {
		super();
		this.pid = pid;
		this.ppw = ppw;
		this.pname = pname;
		this.pssnumber = pssnumber;
	}
	
	public PeopleVO(String pid, String ppw, String pname, String pssnumber, String pphone) {
		super();
		this.pid = pid;
		this.ppw = ppw;
		this.pname = pname;
		this.pssnumber = pssnumber;
		this.pphone = pphone;
	}

	public PeopleVO(String pid, String ppw, String pname, String pssnumber, String pphone, int pbnumber, int ppoint) {
		super();
		this.pid = pid;
		this.ppw = ppw;
		this.pname = pname;
		this.pssnumber = pssnumber;
		this.pphone = pphone;
		this.pbnumber = pbnumber;
		this.ppoint = ppoint;
	}
	
	public PeopleVO(String pid, String ppw, String pname, String pssnumber, String pphone, int pbnumber, int ppoint, String ptier) {
		super();
		this.pid = pid;
		this.ppw = ppw;
		this.pname = pname;
		this.pssnumber = pssnumber;
		this.pphone = pphone;
		this.pbnumber = pbnumber;
		this.ppoint = ppoint;
		this.ptier = ptier;
	}



	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPpw() {
		return ppw;
	}

	public void setPpw(String ppw) {
		this.ppw = ppw;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPssnumber() {
		return pssnumber;
	}

	public void setPssnumber(String pssnumber) {
		this.pssnumber = pssnumber;
	}

	public String getPphone() {
		return pphone;
	}

	public void setPphone(String pphone) {
		this.pphone = pphone;
	}

	public int getPbnumber() {
		return pbnumber;
	}

	public void setPbnumber(int pbnumber) {
		this.pbnumber = pbnumber;
	}

	public int getPpoint() {
		return ppoint;
	}

	public void setPpoint(int ppoint) {
		this.ppoint = ppoint;
	}


	public String getPtier() {
		return ptier;
	}


	public void setPtier(String ptier) {
		this.ptier = ptier;
	}
	

	
}
