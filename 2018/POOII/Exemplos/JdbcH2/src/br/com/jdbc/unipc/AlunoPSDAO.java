package br.com.jdbc.unipc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlunoPSDAO {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_CONNECTION = "jdbc:h2:mem:aluno;DB_CLOSE_DELAY=-1";
	private static final String DB_USER = "";
	private static final String DB_PASSWORD = "";

	public static void main(String[] args) throws Exception {
		try {

			deleteWithStatement();
			createTabledStatement();
			insertWithStatement();
			selectWithStatement();
			deleteWithStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createTabledStatement() throws SQLException {
		Connection connection = getDBConnection();

		Statement create = null;
		String createQuery = "CREATE TABLE EMPLOYEE (id int primary key, name varchar(255))";

		connection.setAutoCommit(false);
		create = connection.createStatement();
		create.execute(createQuery);

		create.close();

	}

	private static void insertWithStatement() throws SQLException {
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);

			stmt = connection.createStatement();

			stmt.execute("INSERT INTO EMPLOYEE (id, name) VALUES(1, 'Anju')");
			stmt.execute("INSERT INTO EMPLOYEE (id, name) VALUES(2, 'Sonia')");
			stmt.execute("INSERT INTO EMPLOYEE (id, name) VALUES(3, 'Asha')");

			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	private static void selectWithStatement() throws SQLException {
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("select * from EMPLOYEE");
			System.out.println("H2 In-Memory Database inserted through Statement");
			while (rs.next()) {
				System.out.println("Id " + rs.getInt("id") + " Name " + rs.getString("name"));
			}

			stmt.close();
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
	}

	private static void deleteWithStatement() throws SQLException {
		Connection connection = getDBConnection();
		Statement stmt = null;
		try {
			connection.setAutoCommit(false);
			stmt = connection.createStatement();
			stmt.execute("DROP TABLE EMPLOYEE");
			stmt.close();
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
