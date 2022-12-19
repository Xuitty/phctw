package spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component

public class Student {
	private String sno, sname, sbday, smail, spwd, sid;
	private int ssex = -1;

	public Student() {
	}

	public Student(String sno, String spwd) {
		this.sno = sno;
		this.spwd = spwd;
	}

	public Student(String sno, String sname, String sbday, int ssex, String smail, String spwd, String sid) {
		this.sno = sno;
		this.sname = sname;
		this.sbday = sbday;
		this.ssex = ssex;
		this.smail = smail;
		this.spwd = spwd;
		this.sid = sid;
	}

	public Student(String sno) {
		this.sno = sno;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSbday() {
		return sbday;
	}

	public void setSbday(String sbday) {
		this.sbday = sbday;
	}

	public int getSsex() {
		return ssex;
	}

	public void setSsex(int ssex) {
		this.ssex = ssex;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", sbday=" + sbday + ", ssex=" + ssex + ", smail=" + smail
				+ ", spwd=" + spwd + ", sid=" + sid + "]";
	}

}
