package service;

import java.util.List;

import domain.Mass;
import domain.value.GAMEID;
import domain.value.PLAYERID;
import service.bean.AutoRequestBean;
import service.bean.MassUpdateBean;

public class AutoRequestMiniService extends Service{

	public AutoRequestBean run(boolean isMyTurn, PLAYERID playerID, GAMEID gameID){
		ChangeTurnPlayerService ctps = null;
		CountZeroService czs = null;
		AutoRequestBean arb = null;
		//相手のターンのとき、制限時間切れで相手が負けていないか確認して負けてたら勝ち
		czs = new CountZeroService(this.con);
		if(!isMyTurn){
			czs.setToWinForCountZero(
					gameID,
					playerID
					);
		}

		ctps = new ChangeTurnPlayerService(this.con);
		//自分の手盤か・ゲームが終了したか・マスの更新があったかを確認する
		arb = ctps.checkTurnPlayerOrGameFinished(
				gameID,
				playerID
				);

		if( (isMyTurn != arb.getIsMyTurn()) || (arb.getIsFinished()) ){
			List<Mass> list = ctps.getAllMass(gameID);

			for (Mass ms : list) {
				int massId = ms.getMASSID().get();
				//String url = MassTypeEnum.MASSTYPE0.url;
				String url = ms.getMassState().url;
				MassUpdateBean massUpdateBean = new MassUpdateBean(massId, url);

				// デバック用--------------------------------------------------
				System.out.println(massUpdateBean.getMassNumber());
				System.out.println(massUpdateBean.getUrl());

				arb.addMassUpdateBean(massUpdateBean);
			}

			//ターン交代したかどうか
			if(isMyTurn != arb.getIsMyTurn()) arb.setIsChanged(true);
		}

		return arb;
	}

}