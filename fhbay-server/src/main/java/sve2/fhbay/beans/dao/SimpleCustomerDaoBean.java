package sve2.fhbay.beans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import sve2.fhbay.domain.Customer;
import sve2.fhbay.interfaces.dao.SimpleCustomerDao;
import sve2.util.JdbcUtil;

@Stateless
public class SimpleCustomerDaoBean implements SimpleCustomerDao {

	private DataSource dataSource;

	private DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Customer findById(Long id) {
		return null;
	}

	@Override
	public Collection<Customer> findAll() {
		return null;
	}

	@Override
	public void persist(Customer cust) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getDataSource().getConnection();
			stmt = conn
					.prepareStatement(
							"INSERT INTO SimpleCustomer (firstname, lastname, username, password, email) VALUES (?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cust.getFirstName());
			stmt.setString(2, cust.getLastName());
			stmt.setString(3, cust.getUserName());
			stmt.setString(4, cust.getPassword());
			stmt.setString(5, cust.getEmail());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected != 1)
				throw new EJBException(
						"Insert into table SimpleCustomer failed.");

			cust.setId(JdbcUtil.getUniqueKey(Long.class, stmt));
		} catch (SQLException e) {
			throw new EJBException(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new EJBException(e);
			}
		}
	}
}
