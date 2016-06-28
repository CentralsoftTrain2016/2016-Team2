package service.bean;

import java.util.ArrayList;
import java.util.List;

public class AutoRequestBean {
	private boolean isMyTurn;
	private boolean isFinished;
	private boolean isChanged = false;;
	private List<MassUpdateBean> massList = new ArrayList<MassUpdateBean>();
	private String playerCharacterImageURL;
	private String enemyCharacterWaitImageURL;
	private String playerCharacterWaitImageURL;
	private String enemyCharacterImageURL;
	private PlayerBean myPlayerBean;
	private PlayerBean enemyPlayerBean;

	public PlayerBean getMyPlayerBean() {
		return myPlayerBean;
	}

	public void setMyPlayerBean(PlayerBean myPlayerBean) {
		this.myPlayerBean = myPlayerBean;
	}

	public PlayerBean getEnemyPlayerBean() {
		return enemyPlayerBean;
	}

	public void setEnemyPlayerBean(PlayerBean enemyPlayerBean) {
		this.enemyPlayerBean = enemyPlayerBean;
	}

	public boolean getIsMyTurn() {
		return this.isMyTurn;
	}

	public void setIsMyTurn(boolean isMyTurn_p) {
		this.isMyTurn = isMyTurn_p;
	}

	public boolean getIsFinished() {
		return this.isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public boolean getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}

	public List<MassUpdateBean> getMassList() {
		return this.massList;
	}

	public void setMassList(List<MassUpdateBean> massList_p) {
		this.massList = massList_p;
	}

	public void addMassUpdateBean(MassUpdateBean mass_p) {
		this.massList.add(mass_p);
	}

	public String getPlayerCharacterImageURL() {
		return playerCharacterImageURL;
	}

	public void setPlayerCharacterImageURL(String playerCharacterImageURL) {
		this.playerCharacterImageURL = playerCharacterImageURL;
	}

	public String getEnemyCharacterWaitImageURL() {
		return enemyCharacterWaitImageURL;
	}

	public void setEnemyCharacterWaitImageURL(String enemyCharacterWaitImageURL) {
		this.enemyCharacterWaitImageURL = enemyCharacterWaitImageURL;
	}

	public String getPlayerCharacterWaitImageURL() {
		return playerCharacterWaitImageURL;
	}

	public void setPlayerCharacterWaitImageURL(String playerCharacterWaitImageURL) {
		this.playerCharacterWaitImageURL = playerCharacterWaitImageURL;
	}

	public String getEnemyCharacterImageURL() {
		return enemyCharacterImageURL;
	}

	public void setEnemyCharacterImageURL(String enemyCharacterImageURL) {
		this.enemyCharacterImageURL = enemyCharacterImageURL;
	}

}
