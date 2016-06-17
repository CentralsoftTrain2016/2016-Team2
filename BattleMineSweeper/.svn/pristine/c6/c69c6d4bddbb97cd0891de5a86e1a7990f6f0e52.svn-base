package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class Dao
{
	 protected Connection con;

	 public Dao(Connection con) {
		super();
		this.con = con;
	}

	 public static Connection getConnection2() throws SQLException
		{
			 InitialContext context;
			 DataSource ds =null;
			try {
				context = new InitialContext();
				System.out.println("あー");
				ds = (DataSource) context.lookup("java:comp/env/jdbc/myapp");
				System.out.println("うー");
			} catch (NamingException e) {

				e.printStackTrace();
				throw new RuntimeException(e);
			}

			 Connection con = ds.getConnection();

			return con;
		 }

	public static Connection getConnection() throws ClassNotFoundException, SQLException
	 {
		/* ユーザ名 */
		String user = "train2016";
		/* パスワード */
		//String pass = "orapass";
		String pass = "train2016";

		/* サーバ名 */
		String servername = "52.196.216.225";


//		/* ユーザ名 */
//		String user = "train2016_team2";
//		/* パスワード */
//		//String pass = "orapass";
//		String pass = "train2016_team2";
//
//		/* サーバ名 */
		//String servername = "localhost";

		/* SID */
		String sid = "xe";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection c = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + servername + ":1521:"
							+ sid,user,pass);

		return c;
	 }

}
