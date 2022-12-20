package spring.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@Repository
public class StudentDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Autowired
	SessionFactory sessionFactory;

	Session session;
	
	Transaction tr;

//	static Session getSession()
//	{
////		Configuration conn=new Configuration().configure();
//		SessionFactory sf=sessionFactory;
//		Session session=sf.openSession();
//		return session;
//	}

	public Boolean insertStudent(Student s) {
//		String sql = "insert into student (sno,sname,sbday,ssex,smail,spwd,sid) values(?,?,?,?,?,?,?)";
//		return template.execute(sql, new PreparedStatementCallback<Boolean>() {
//			@Override
//			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//
//				ps.setString(1, s.getSno());
//				ps.setString(2, s.getSname());
//				ps.setString(3, s.getSbday());
//				ps.setInt(4, s.getSsex());
//				ps.setString(5, s.getSmail());
//				ps.setString(6, s.getSpwd());
//				ps.setString(7, s.getSid());
//
//				return ps.executeUpdate()==1?true:false;
//
//			}
//		});

//			Query query = session.createNativeQuery("insert into student (sno,sname,sbday,ssex,smail,spwd,sid) values(:sno,:sname,:sbday,:ssex,:smail,:spwd,:sid)");
//			query.setParameter("sno", s.getSno());
//			query.setParameter("sname", s.getSname());
//			query.setParameter("sbday", s.getSbday());
//			query.setParameter("ssex", s.getSsex());
//			query.setParameter("smail", s.getSmail());
//			query.setParameter("spwd", s.getSpwd());
//			query.setParameter("sid", s.getSid());
//			session.beginTransaction();
		session = sessionFactory.getCurrentSession();
//		tr=session.getTransaction();
//		tr.begin();
		session.persist(s);
//		tr.rollback();
//			session.getTransaction().commit();
//			session.close();
		return true;
	}

	public List<Student> selectStudent() {
		return null;

	}

	public Boolean updateStudent(Student student) {
//		Student s = queryStudent(student.getSno());
//		String sql = "update student set sno=?,sname=?,sbday=?,ssex=?,smail=?,spwd=?,sid=? WHERE sno=?;";
//		return template.execute(sql, new PreparedStatementCallback<Boolean>() {
//			@Override
//			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//				ps.setString(1, student.getSno());
//				ps.setString(2, student.getSname() == null ? s.getSname() : student.getSname());
//				ps.setString(3, student.getSbday() == null ? s.getSbday() : student.getSbday());
//				ps.setInt(4, student.getSsex() == -1 ? s.getSsex() : student.getSsex());
//				ps.setString(5, student.getSmail() == null ? s.getSmail() : student.getSmail());
//				ps.setString(6, student.getSpwd() == null ? s.getSpwd() : student.getSpwd());
//				ps.setString(7, student.getSid() == null ? s.getSid() : student.getSid());
//				ps.setString(8, student.getSno());
//
//				int r = ps.executeUpdate();
//				if (r == 1) {
//					return true;
//				} else {
//					return false;
//				}
//
//			}
//		});
		session = sessionFactory.getCurrentSession();
		Student s = session.get(Student.class, student.getSno());
		s.setSno((student.getSno()==null)?s.getSno():student.getSno());
		s.setSname((student.getSname()==null)?s.getSname():student.getSname());
		s.setSbday((student.getSbday()==null)?s.getSbday():student.getSbday());
		s.setSmail((student.getSmail()==null)?s.getSmail():student.getSmail());
		s.setSpwd((student.getSpwd()==null)?s.getSpwd():student.getSpwd());
		s.setSid((student.getSid()==null)?s.getSid():student.getSid());
		s.setSsex((student.getSsex()==-1)?s.getSsex():student.getSsex());
		s.setActive((student.getActive()==-1)?s.getActive():student.getActive());
		session.clear();
		session.merge(s);
		return true;
	}

	public Boolean deleteStudent(String sno) {
		return null;
	}

	public Student queryStudent(String sno) {
//		String sql = "select * from student where sno like ?";
//		return template.execute(sql, new PreparedStatementCallback<Student>() {
//			@Override
//			public Student doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//				Student s = new Student();
//				ps.setString(1, sno);
//				ResultSet rs = ps.executeQuery();
//				while (rs.next()) {
//					s.setSno(rs.getString(1));
//					s.setSname(rs.getString(2));
//					s.setSbday(rs.getString(3));
//					s.setSsex(rs.getInt(4));
//					s.setSmail(rs.getString(5));
//					s.setSpwd(rs.getString(6));
//					s.setSid(rs.getString(7));
//				}
//
//				return s;
//
//			}
//		});

		return sessionFactory.getCurrentSession().get(Student.class, sno);
	}

	public Boolean loginStudent(String acc, String pass) {
		String sql = "select * from student where sno like ? and spwd like ? and active = 1";
		return template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, acc);
				ps.setString(2, pass);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					return true;
				}

				return false;

			}
		});
	}

	public Boolean writeVerify(String sno, String verify) {
		String sql = "replace into verify (sno,verify) values(?,?)";
		return template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, sno);
				ps.setString(2, verify);

				return ps.executeUpdate() == 1 ? true : false;

			}
		});

	}

	public Boolean deleteVerify(String sno) {
		String sql = "delete from verify where sno = ?";
		return template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, sno);

				return ps.executeUpdate() == 1 ? true : false;

			}
		});
	}

	public String queryVerify(String sno) {
		String sql = "select verify from verify where sno = ?";
		return template.execute(sql, new PreparedStatementCallback<String>() {
			@Override
			public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, sno);
				ResultSet rs = ps.executeQuery();
				String verify = "";
				while (rs.next()) {
					verify = rs.getString(1);
				}
				return verify;

			}
		});

	}

	public Boolean activeAccount(String sno) {
		String sql = "update student set active=? where sno=?";
		return template.execute(sql, new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setInt(1, 1);
				ps.setString(2, sno);

				return ps.executeUpdate() == 1 ? true : false;

			}
		});
	}

	@Deprecated
	public Boolean forgetPassword(String sno, String smail) {
		// TODO Auto-generated method stub
		return null;
	}

}
