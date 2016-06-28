package service.bean;

import domain.Item;
import domain.Player;
import domain.Quiz;
import domain.value.ISFINISH;
import domain.value.LosePlayerID;
import domain.value.MASSLIST;
import domain.value.WinPlayerID;

public class ClickResultBean extends Bean {
	private MASSLIST masslist = null;
	private ISFINISH isfinish = new ISFINISH(false);
	private WinPlayerID winPlayerID = null;
	private LosePlayerID losePlayerID = null;
	private Quiz quiz = null;
	private Item item = null;
	private Player myPlayer = null;
	private Player enemyPlayer = null;

	public ClickResultBean() {
		super();
	}
	//getter
	public MASSLIST getMASSLIST() {
		return this.masslist;
	}
	public ISFINISH getISFINISH() {
		return this.isfinish;
	}
	public WinPlayerID getWinPlayerID() {
		return this.winPlayerID;
	}
	public LosePlayerID getLosePlayerID() {
		return this.losePlayerID;
	}
	public Quiz getQuiz() {
		return this.quiz;
	}
	public Item getItem() {
		return this.item;
	}
	public Player getMyPlayer() {
		return this.myPlayer;
	}
	public Player getEnemyPlayer() {
		return this.enemyPlayer;
	}
	//setter
	public void setMASSLIST(MASSLIST mASSLIST) {
		this.masslist = mASSLIST;
	}
	public void setISFINISH(ISFINISH iSFINISH) {
		this.isfinish = iSFINISH;
	}
	public void setWinPlayerID(WinPlayerID wINPLAYERID) {
		this.winPlayerID = wINPLAYERID;
	}
	public void setLosePlayerID(LosePlayerID lOSEPLAYERID) {
		this.losePlayerID = lOSEPLAYERID;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setMyPlayer(Player myplayer) {
		this.myPlayer = myplayer;
	}
	public void setEnemyPlayer(Player enemyplayer) {
		this.enemyPlayer = enemyplayer;
	}
}