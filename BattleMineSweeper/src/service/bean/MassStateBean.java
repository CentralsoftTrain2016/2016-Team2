package service.bean;

import java.util.ArrayList;
import java.util.List;

public class MassStateBean {

	List<MassUpdateBean> massList = new ArrayList<MassUpdateBean>();
	boolean isMyTurn;


	public List<MassUpdateBean> getMassList() {
		return massList;
	}

	public void getMassList(List<MassUpdateBean> massList_p) {
		this.massList = massList_p;
	}


	public void addMassUpdateBean(MassUpdateBean massUpdateBean_p) {
		this.massList.add(massUpdateBean_p);
	}

	public boolean getIsMyTurn() {
		return isMyTurn;
	}
	public void setIsMyTurn(boolean isMyTurn_p) {
		this.isMyTurn = isMyTurn_p;
	}

}
