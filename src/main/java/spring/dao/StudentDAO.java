package spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import spring.bean.Student;
import spring.bean.Verify;

@Repository
public class StudentDAO {


	@Autowired
	SessionFactory sessionFactory;

	Session session;


	public Boolean insertStudent(Student s) {

		session = sessionFactory.getCurrentSession();
		session.persist(s);
		return true;
	}

	public List<Student> selectStudent() {
		return null;

	}

	public Boolean updateStudent(Student student) {
		session = sessionFactory.getCurrentSession();
		session.merge(student);
		return true;
	}

	public Boolean deleteStudent(String sno) {
		return null;
	}

	public Student queryStudent(String sno) {
		session = sessionFactory.getCurrentSession();
		Student s = session.get(Student.class, sno);
		return s;
	}

	public Boolean loginStudent(String acc, String pass) {
		String hql = "from Student where sno like :sno and spwd like :spwd and active = 1";
		List<Object> l = null;
		session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("sno", acc);
		query.setParameter("spwd", pass);
		l = query.getResultList();
		if (l.isEmpty() || l == null) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean writeVerify(String sno, String verify) {
		session=sessionFactory.getCurrentSession();
		Verify v = new Verify(sno,verify);
		System.out.println(v.toString());
		session.merge(v);
		return true;

	}

	public Boolean deleteVerify(String sno) {
		session = sessionFactory.getCurrentSession();
		Verify v = new Verify(sno,null);
		session.delete(v);
		return true;
	}

	public String queryVerify(String sno) {
		session = sessionFactory.getCurrentSession();
		return session.get(Verify.class, sno).getVerify();

	}

	public Boolean activeAccount(String sno) {
		session = sessionFactory.getCurrentSession();
		Student s = queryStudent(sno);
		s.setActive(1);
		session.merge(s);
		return true;
	}

	@Deprecated
	public Boolean forgetPassword(String sno, String smail) {
		// TODO Auto-generated method stub
		return null;
	}

}
