package service;
//いしもりがデバッグ用に勝手に作ったクラスだよ。あとで消すよ

import java.sql.Connection;
import java.sql.SQLException;

import dao.Dao;
import domain.ClickObjectTypeEnum;
import domain.Mass;
import domain.value.ClickPlayerID;
import domain.value.GAMEID;
import service.bean.ClickResultBean;

public class IshimoriDebug {

	public static void main(String[] args) {
		Mass ms = new Mass();
		Connection con = null;
		try{
			con = Dao.getConnection();
			ClickObjectTypeEnum cote = ClickObjectTypeEnum.SKILL;
			ClickResultBean bean = cote.clickAction(null, new GAMEID(4), 0, new ClickPlayerID(11), con);
			if(bean.getItem() != null && bean.getQuiz() != null){
				System.out.println("itemID = " + bean.getItem().getITEMID().get());
				System.out.println("quizID = " + bean.getQuiz().getQUIZID().get());
			}else if(bean.getItem() == null && bean.getQuiz() != null){
				System.out.println("itemID = null" );
				System.out.println("quizID = " + bean.getQuiz().getQUIZID().get());
			}else if(bean.getItem() != null && bean.getQuiz() == null){
				System.out.println("itemID = " + bean.getItem().getITEMID().get());
				System.out.println("quizID = null");
			}else{
				System.out.println("itemID = null" );
				System.out.println("quizID = null" );
			}

//			CHARACTERID c = new CHARACTERID(1);
//			CharacterSkillEnum cse = CharacterSkillEnum.getSkillEnumByValue(c.get());
//			System.out.println(cse.getSkillName());

//			MassDao msdao =  new MassDao(con);
//
//			ClickObjectTypeEnum obj = ClickObjectTypeEnum.MASS;
//			MASSID massID = new MASSID(80);
//			GAMEID gameID = new GAMEID( 2);
//			ClickPlayerID clickPlayerID = new ClickPlayerID( 2);
//
//			ClickResultBean bean = obj.clickAction(massID, gameID, clickPlayerID, con);
////			ms = msdao.getMass(80, 1 );				//クリックされたマスデータの取得
////
//			System.out.println(ms.getAROUNDMINES().get());
//			System.out.println(ms.getMASSID().get());
//			System.out.println(ms.getGAMEID().get());
//			System.out.println(ms.getISEXISTMINE().get());
//			System.out.println(ms.getISOPEN().get());
//			System.out.println(ms.getITEMID().get());
//			System.out.println(ms.getQUIZID().get());


//			FieldService fs = new FieldService();
//			fs.fieldPlacement(gameID.get(), 2);
//			System.out.println("field create Successed");

//			ClickActionService cas = new ClickActionService();
//			ClickResultBean crb = cas.clickAction(new MASSID(1),gameID,new ClickPlayerID(1) );
//			if(crb.getMASSLIST() ==null){
//				System.out.println("null だよー");
//				System.exit(0);
//			}
//			List<Mass> ml = crb.getMASSLIST().get();
//
//			for(Mass test: ml){
//				int massid = test.getMASSID().get();
//				System.out.println(massid);
//			}
			System.out.println("おわりだよー");
		}catch(SQLException|ClassNotFoundException e){
		//}catch(SQLException e){
			e.printStackTrace();
			System.out.println("例外だよー");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
