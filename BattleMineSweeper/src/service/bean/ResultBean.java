package service.bean;

import domain.value.ADDESCRIPTION;
import domain.value.INFORMATIONDESCRIPTION;
import domain.value.USERNAME;

public class ResultBean extends Bean{

	private String myName = null;
	private String enemyName = null;
	private String infoDescript = null;
	private String adDescript = null;


	public ResultBean() {
		super();
	}

	public String getMyName() {
		return this.myName;
	}
	public void setMyName(USERNAME myName_p) {
		this.myName = myName_p.get();
	}
	public String getEnemyName() {
		return enemyName;
	}
	public void setEnemyName(USERNAME enemyName_p) {
		this.enemyName = enemyName_p.get();
	}

	public String getInfoDescription(){
		return this.infoDescript;
	}
	public void setInfoDescription(INFORMATIONDESCRIPTION infoDescript_p) {
		this.infoDescript = infoDescript_p.get();
	}
	public String getAdDescription() {
		return adDescript;
	}
	public void setAdDescription(ADDESCRIPTION adDescript_p) {
		this.adDescript = adDescript_p.get();
	}


}
