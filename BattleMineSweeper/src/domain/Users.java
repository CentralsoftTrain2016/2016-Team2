package domain;

import domain.value.LOSECOUNT;
import domain.value.PASSWORD;
import domain.value.USERID;
import domain.value.USERNAME;
import domain.value.WINCOUNT;

public class Users {
	private USERID USERID;
	private USERNAME USERNAME;
	private PASSWORD PASSWORD;
	private WINCOUNT WINCOUNT;
	private LOSECOUNT LOSECOUNT;
	private WINCOUNT singleWINCOUNT;
	private LOSECOUNT singleLOSECOUNT;

	// GETTER-------------------------------------

	public WINCOUNT getSingleWINCOUNT() {
		return singleWINCOUNT;
	}

	public void setSingleWINCOUNT(WINCOUNT singleWINCOUNT) {
		this.singleWINCOUNT = singleWINCOUNT;
	}

	public LOSECOUNT getSingleLOSECOUNT() {
		return singleLOSECOUNT;
	}

	public void setSingleLOSECOUNT(LOSECOUNT singleLOSECOUNT) {
		this.singleLOSECOUNT = singleLOSECOUNT;
	}

	public USERID getUSERID() {
		return this.USERID;
	}

	public USERNAME getUSERNAME() {
		return this.USERNAME;
	}

	public PASSWORD getPASSWORD() {
		return this.PASSWORD;
	}

	public WINCOUNT getWINCOUNT() {
		return this.WINCOUNT;
	}

	public LOSECOUNT getLOSECOUNT() {
		return this.LOSECOUNT;
	}

	// SETTER-------------------------------------

	public void setUSERID(USERID uSERID) {
		this.USERID = uSERID;
	}

	public void setUSERNAME(USERNAME uSERNAME) {
		this.USERNAME = uSERNAME;
	}

	public void setPASSWORD(PASSWORD pASSWORD) {
		this.PASSWORD = pASSWORD;
	}

	public void setWINCOUNT(WINCOUNT wINCOUNT) {
		this.WINCOUNT = wINCOUNT;
	}

	public void setLOSECOUNT(LOSECOUNT lOSECOUNT) {
		this.LOSECOUNT = lOSECOUNT;
	}

}
