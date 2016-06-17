package domain;

import domain.value.INFORMATIONID;
import domain.value.USERID;

public class DictionaryKey {

	private INFORMATIONID INFORMATIONID;
	private USERID USERID;

	// GETTER--------------------------------------------------------------
	public INFORMATIONID getINFORMATIONID() {
		return INFORMATIONID;
	}

	public USERID getUSERID() {
		return USERID;
	}

	// SETTER--------------------------------------------------------------
	public void setINFORMATIONID(INFORMATIONID iNFORMATIONID) {
		INFORMATIONID = iNFORMATIONID;
	}

	public void setUSERID(USERID uSERID) {
		USERID = uSERID;
	}

}
