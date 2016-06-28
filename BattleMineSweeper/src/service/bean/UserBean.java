package service.bean;

import domain.value.LOSECOUNT;
import domain.value.USERID;
import domain.value.USERNAME;
import domain.value.WINCOUNT;

public class UserBean extends Bean {

	public UserBean() {
		super();
	}

	private USERID userID = null;
	private USERNAME userName = null;
	private WINCOUNT winCount = null;
	private LOSECOUNT loseCount = null;

	// getter

	public USERID getUserID() {
		return userID;
	}

	public USERNAME getUserName() {
		return this.userName;
	}

	public WINCOUNT getWinCount() {
		return winCount;
	}

	public LOSECOUNT getLoseCount() {
		return loseCount;
	}
	// setter

	public void setUserID(USERID userID_p){
		this.userID = userID_p;
		}

	public void setUserName(USERNAME userName_p) {
		this.userName = userName_p;
	}

	public void setWinCount(WINCOUNT winCount) {
		this.winCount = winCount;
	}

	public void setLoseCount(LOSECOUNT loseCount) {
		this.loseCount = loseCount;
	}

}
