package domain;

import java.sql.Connection;
import java.sql.SQLException;

import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.MASSID;
import service.bean.ClickResultBean;

public interface ClickActionInterface {
	  /**
     * クリック時の動作を返すメソッドを定義する
	 * @throws ClassNotFoundException
	 * @throws SQLException
     */
    public ClickResultBean clickAction(MASSID massID, GAMEID gameID, ClickPlayerID clickPlayerID,Connection con) throws SQLException, ClassNotFoundException;

}
