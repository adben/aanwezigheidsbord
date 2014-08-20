import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dao implements UserDao {
	public static final String DB_URL = "jdbc:sqlite:aanwezigheid.db";
	public static final String CREATE_TABLE =
			"create table aanwezigheid (\n" +
			"  enterDate date,\n" +
			"  username text\n" +
			")";

	public Dao() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DB_URL);
			if (conn != null) {

				statement = conn.createStatement();
				statement.execute(CREATE_TABLE);
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// yes yes
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// go away
				}
			}
		}
	}

	@Override
	public void checkInUser(String userName) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DB_URL);
			if (conn != null) {

				statement = conn.prepareStatement("insert into aanwezigheid (enterDate, userName) values (?, ?)");
				statement.setTimestamp(1, new Timestamp(new Date().getTime()));
				statement.setString(2, userName);
				statement.execute();
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// yes yes
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// go away
				}
			}
		}
	}

	@Override
	public void checkOutUser(String userName) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DB_URL);
			if (conn != null) {

				statement = conn.prepareStatement("delete from aanwezigheid where userName = ?");
				statement.setString(1, userName);
				statement.execute();
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// yes yes
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// go away
				}
			}
		}
	}

	@Override
	public List<String> getCheckedInUsers() {
		Connection conn = null;
		PreparedStatement statement = null;
		List<String> result = new ArrayList<String>();
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(DB_URL);
			if (conn != null) {

				statement = conn.prepareStatement("select username from aanwezigheid order by userName");
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					result.add(resultSet.getString(1));
				}
				conn.close();
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// yes yes
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// go away
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Dao dao = new Dao();
		dao.checkInUser("username");
		assert(dao.getCheckedInUsers().size() == 1);
		dao.checkOutUser("username");
		assert(dao.getCheckedInUsers().size() == 0);
	}
}
