package spring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "verify", uniqueConstraints = { @UniqueConstraint(columnNames = { "sno" }) })
@Component

public class Verify {

	@Id
	@Column
	private String sno;
	@Column
	private String verify;

	public Verify() {
	}

	public Verify(String sno, String verify) {
		this.sno = sno;
		this.verify = verify;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	@Override
	public String toString() {
		return "Verify [sno=" + sno + ", verify=" + verify + "]";
	}

}
