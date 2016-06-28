package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import domain.ClickObjectTypeEnum;
import domain.ItemEnum;
import domain.Mass;
import domain.Player;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import domain.value.MASSID;
import domain.value.MASSLIST;
import domain.value.PLAYERID;
import net.arnx.jsonic.JSON;
import service.ChangeTurnPlayerService;
import service.bean.BattleClickBean;
import service.bean.ClickResultBean;
import service.bean.ItemBean;
import service.bean.MassStateBean;
import service.bean.MassUpdateBean;
import service.bean.PlayerBean;
import service.bean.QuizBean;

/**
 * Servlet implementation class RevueAjaxServlet
 */
@WebServlet("/BattleClickServlet")
public class BattleClickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BattleClickServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化けのおまじない
		response.setContentType("application/json; charset=UTF-8");

		String battleClickJson = (String) request.getParameter("battleClickBean");
		System.out.println(battleClickJson);

		// デバッグ用にプレイヤとゲームを勝手に入力
		//request.getSession().setAttribute("playerID", 1);
		//request.getSession().setAttribute("gameID", 1);
		// ここまで

		BattleClickBean battleClickBean = JSON.decode(battleClickJson, BattleClickBean.class);
		System.out.println(battleClickBean.getTurnTotalTime());

		// セッションからゲームIDを取得
		GAMEID gameID = new GAMEID((int) request.getSession().getAttribute("GAMEID"));
		// セッションからクリックしたプレイヤーを取得
		ClickPlayerID clickPlayerID = new ClickPlayerID((int) request.getSession().getAttribute("PLAYERID"));
		// マス情報を取得
		MASSID massID = new MASSID(battleClickBean.getClickObjectNumber());
		System.out.println(massID);
		// クリックしたものが何かを判別(マス or アイテム or スキル)
		ClickObjectTypeEnum objectType = battleClickBean.getClickObjectType();
		// GAMEID gameID = new GAMEID(battleClickBean.getGameObjectNumber())
		// ---------------------------------------------------------------------------
		Connection con = null;
		try {
			// objectTypeに応じたクリック時処理へ、@return = ClickResultBean
			con = Dao.getConnection();

			ClickResultBean clickResultBean = objectType.clickAction(massID, gameID, battleClickBean.getTurnTotalTime(), clickPlayerID, con);

			MASSLIST massList = clickResultBean.getMASSLIST();
			List<Mass> list = massList.get();

			MassStateBean massStateBean = new MassStateBean();


			for (Mass ms : list) {
				int massId = ms.getMASSID().get();
				// String url = MassTypeEnum.MASSTYPE0.url;
				String url = ms.getMassState().url;
				MassUpdateBean massUpdateBean = new MassUpdateBean(massId, url);

				// デバック用--------------------------------------------------
				System.out.println(massUpdateBean.getMassNumber());
				System.out.println(massUpdateBean.getUrl());

				massStateBean.addMassUpdateBean(massUpdateBean);
			}



//			Boolean isFinish = clickResultBean.getISFINISH().get();
//			if (isFinish) {
//				String massClickBean = JSON.encode(massStateBean);
//
//				System.out.println(massClickBean);
//				PrintWriter out = response.getWriter();
//				out.println(massClickBean);
//
//			} else {
				massStateBean.setIsFinished(clickResultBean.getISFINISH().get());

				PLAYERID playerID = new PLAYERID( (int)request.getSession().getAttribute("PLAYERID") );
				ChangeTurnPlayerService ctps = new ChangeTurnPlayerService(con);
				massStateBean.setIsMyTurn(ctps.checkTurnPlayer(
															new GAMEID((int)request.getSession().getAttribute("GAMEID")),
															playerID
															).getIsMyTurn());

				//クイズをbeanに追加
				if(clickResultBean.getQuiz() != null){
					QuizBean qbean = new QuizBean();
					qbean.setDescription(clickResultBean.getQuiz().getDESCRIPTION().get());
					qbean.setAnswer(clickResultBean.getQuiz().getANSWER());

					massStateBean.setQuizBean(qbean);
				}

				//アイテムをbeanに追加
				if(clickResultBean.getItem() != null){
					ItemBean ibean = new ItemBean();
					ibean.setItemEnum(clickResultBean.getItem().getITEMENUM());

					ibean.setItemName(clickResultBean.getItem().getITEMNAME().get());
					massStateBean.setItemBean(ibean);
				}

				//プレイヤのアイテム、スキル情報をbeanに追加
				if(clickResultBean.getMyPlayer() != null ){
					Player myPlayer = clickResultBean.getMyPlayer();

					PlayerBean pbean = new PlayerBean();
					pbean.setCoolTime(myPlayer.getCOOLTIME().get());
					//set itemURL

					if(myPlayer.getITEMID().get() != 0) pbean.setItemURL(  ItemEnum.getItemEnum( myPlayer.getITEMID().get() ).getItemURL()  );

					massStateBean.setMyPlayerBean(pbean);
				}
				if(clickResultBean.getEnemyPlayer() != null){
					Player enemyPlayer = clickResultBean.getEnemyPlayer();
					PlayerBean pbean = new PlayerBean();
					pbean.setCoolTime(enemyPlayer.getCOOLTIME().get());
					//set itemURL

					if(enemyPlayer.getITEMID().get() != 0) pbean.setItemURL(  ItemEnum.getItemEnum( enemyPlayer.getITEMID().get() ).getItemURL()  );

					massStateBean.setEnemyPlayerBean(pbean);
				}

				String massClickBean = JSON.encode(massStateBean);

				System.out.println(massClickBean);
				PrintWriter out = response.getWriter();
				out.println(massClickBean);

				return;
//			}


			// int winPlayerId =
			// clickResultBean.getWinPlayerID().get();//勝ったプレイヤーID
			// int losePlayerId =
			// clickResultBean.getLosePlayerID().get();//負けたプレイヤーID
			//
			// Player player = new Player();
			// int playerId = player.getPLAYERID().get();//
			//
			// if (playerId == winPlayerId) {
			// // 勝側のサーブレットに勝ったプレイヤーIDを渡す
			// request.getSession().setAttribute("winPlayerID", winPlayerId);
			// RequestDispatcher disp =
			// request.getRequestDispatcher("/WinPlayerServlet");
			// disp.forward(request, response);
			// } else if (playerId == losePlayerId) {
			// // 負側のサーブレットに負けたプレイヤーIDを渡す
			// request.getSession().setAttribute("losePlayerID", losePlayerId);
			// RequestDispatcher disp =
			// request.getRequestDispatcher("/LosePlayerServlet");
			// disp.forward(request, response);
			// }

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				if (con != null && con.isClosed()) {
					throw new RuntimeException("すでにコネクションが閉じています。ロールバックができません。");
				}
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (con != null && con.isClosed()) {
					throw new RuntimeException("すでにコネクションが閉じています。ロールバックができません。");
				}
				if (con != null)
					con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		}catch(Throwable e){
			e.printStackTrace();
			throw e;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

}

// **********************************************************************************************
/*
 * String massJson = (String)request.getParameter("battleClickBean");//増すの名前が来る
 * System.out.println(massJson);
 *
 * BattleClickBean mass = JSON.decode(massJson,BattleClickBean.class);
 * System.out.println(mass.getClickObjectType());
 *
 *
 * BattleClickBean mass = new BattleClickBean();
 * mass.setClickObjectType(ClickObjectTypeEnum.MASS);
 * mass.setClickObjectNumber(new ClickObjectNumber(1));
 *
 * String massClickBean = JSON.encode(mass); System.out.println(massClickBean);
 *
 * // System.out.println(mass.getClickObjectNumber().get());
 *
 * PrintWriter out = response.getWriter(); out.println(massClickBean);
 *
 * /* String str = mass.getName(); //名前を取ってくる int mass =
 * Integer.parseInt(str.replaceAll("[^0-9]","")); // 数値の抽出
 *
 *
 * ClickActionService clickActionService = new ClickActionService();
 * MassClickResultBean massClickResultBean = clickActionService.MassOpen(mass);
 * //リザルトビーン返ってくる
 *
 *
 * //Enumからurlに MassState massState = new MassState(); massState.setUrl();
 *
 * Boolean fainalResult = massClickResultBean.getISFINISH(); if (fainalResult ==
 * false){ //ResultSevletへ }else{ MassUpdate massupdate = new MassUpdate();
 * String massClickBean = JSON.encode(massupdate); PrintWriter out =
 * response.getWriter(); out.println(massClickBean);
 *
 * return;
 *
 * }
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * //JSON逆変換 /* ColorBean colorBean = JSON.decode(color,ColorBean.class);
 *
 * Color colorEnum = Color.valueOf(colorBean.getColorName()); //文字列からenum
 *
 * colorBean.setColorName(colorEnum.nextColor.name()); //次の色の名前をBeanへset
 * colorBean.setColorCode(colorEnum.nextColor.colorCode);// 次の色のコードをBeanへset
 *
 * //JSON変換 String colorjson = JSON.encode(colorBean); //JSON形式の表示
 * System.out.println(colorjson);
 */
// 値を返す
