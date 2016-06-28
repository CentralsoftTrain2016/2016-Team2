package service.bean;

import domain.ClickObjectTypeEnum;

public class BattleClickBean {
	private ClickObjectTypeEnum clickObjectType;
	private int clickObjectNumber;
	private boolean isMyTurn;
	private int turnTotalTime;

	public int getTurnTotalTime() {
		return turnTotalTime;
	}
	public void setTurnTotalTime(int turnTotalTime) {
		this.turnTotalTime = turnTotalTime;
	}

	public ClickObjectTypeEnum getClickObjectType() {
		return clickObjectType;
	}
	public void setClickObjectType(ClickObjectTypeEnum clickObjectType_p) {
		this.clickObjectType = clickObjectType_p;
	}

	public int getClickObjectNumber() {
		return clickObjectNumber;
	}
	public void setClickObjectNumber(int clickObjectNumber_p) {
		this.clickObjectNumber = clickObjectNumber_p;
	}

	public boolean getIsMyTurn() {
		return isMyTurn;
	}
	public void setIsMyTurn(boolean isMyTurn_p) {
		this.isMyTurn = isMyTurn_p;
	}

}
