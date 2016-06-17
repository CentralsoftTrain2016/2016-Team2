package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.Dao;

public abstract class Service
{
	protected Connection con =null;

	public Service() {
		start();
	}

	public Service(Service connectService) {
		start(connectService);
	}

	public Service(Connection connection) throws SQLException, ClassNotFoundException{
		start(connection);
	}

	public void start(Service connectService)
	{
		con = connectService.con;
//		prePare();
	}

	public void start(Connection connection)
	{
		con = connection;
//		prePare();
	}

	public void start()
	{
		try {
			con = Dao.getConnection();
			System.out.println("ゲットコネクション！");
			con.setAutoCommit(true);
//			prePare();
		} catch ( SQLException | ClassNotFoundException e)
		//} catch ( SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

//	//サブクラスでサービスの準備を行う。（Daoの参照を取得する）
//	abstract void prePare();

	public void rollebackEnd()
	{
		try {
			if( con != null && con.isClosed())
			{
				throw new RuntimeException("すでにコネクションが閉じています。ロールバックができません。");
			}

			if( con != null )
				con.rollback();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally
		{
			try {
				if( con != null )
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void end()
	{
		try {
			if( con != null && !con.isClosed())
			{
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public boolean isConnectionClosed(){
		try {
			if( con != null )
			{
				return con.isClosed();
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
