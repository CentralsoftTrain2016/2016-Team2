package domain;

import domain.value.ADDESCRIPTION;
import domain.value.ADID;
import domain.value.VIEWS;

public class Ad {

	private ADID ADID;
	private ADDESCRIPTION ADDESCRIPTION;
	private VIEWS VIEWS;

	// GETTER-------------------------------------
	public ADID getADID() {
		return ADID;
	}

	public ADDESCRIPTION getADDESCRIPTION() {
		return ADDESCRIPTION;
	}

	public VIEWS getVIEWS() {
		return VIEWS;
	}

	// SETTER-------------------------------------
	public void setADID(ADID aDID) {
		ADID = aDID;
	}

	public void setADDESCRIPTION(ADDESCRIPTION aDDESCRIPTION) {
		ADDESCRIPTION = aDDESCRIPTION;
	}

	public void setVIEWS(VIEWS vIEWS) {
		VIEWS = vIEWS;
	}

}
