package br.com.jdbc.unipc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:aluno;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public static void main(String[] args) throws Exception {
		try {
			createTablePreparedStatement();
			insertWithPreparedStatement();
			selectWithPreparedStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTablePreparedStatement() throws SQLException {
		Connection connection = getDBConnection();

		PreparedStatement create = null;
		String createQuery = "CREATE TABLE PERSON (id int primary key, name varchar(255))";

		create = connection.prepareStatement(createQuery);
		create.executeUpdate();
		create.close();

	}

	private static void insertWithPreparedStatement() throws SQLException {
		Connection connection = getDBConnection();

		PreparedStatement insert = null;

		String InsertQuery = "INSERT INTO PERSON" + "(id, name) values" + "(?,?)";

		try {
			connection.setAutoCommit(false);

			insert = connection.prepareStatement(InsertQuery);
			insert.setInt(1, 1);
			insert.setString(2, "Jose");
			insert.executeUpdate();
			insert.close();

			insert.close();

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	private static void selectWithPreparedStatement() throws SQLException {
		Connection connection = getDBConnection();

		PreparedStatement select = null;

		String SelectQuery = "select * from PERSON";

		try {
			connection.setAutoCommit(false);

			select = connection.prepareStatement(SelectQuery);
			ResultSet rs = select.executeQuery();
			System.out.println("H2 In-Memory Database inserted through PreparedStatement");
			while (rs.next()) {
				System.out.println("Id " + rs.getInt("id"));
				System.out.println(" Name " + rs.getString("name"));
			}
			select.close();

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}
	
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

}
