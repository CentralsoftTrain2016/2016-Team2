package domain;

import domain.value.CHARACTERID;
import domain.value.COOLTIME;
import domain.value.ITEMID;
import domain.value.PLAYERID;
import domain.value.TIMELIMIT;
import domain.value.TOTALTIME;
import domain.value.USERID;

public class Player {
	private PLAYERID PLAYERID;
	private USERID USERID;
	private CHARACTERID CHARACTERID;
	private TOTALTIME TOTALTIME;
	private ITEMID ITEMID;
	private COOLTIME COOLTIME;
	private TIMELIMIT TIMELIMIT;

	// GETTER-------------------------------------
	public PLAYERID getPLAYERID() {
		return PLAYERID;
	}

	public USERID getUSERID() {
		return USERID;
	}

	public CHARACTERID getCHARACTERID() {
		return CHARACTERID;
	}

	public TOTALTIME getTOTALTIME() {
		return TOTALTIME;
	}

	public ITEMID getITEMID() {
		return ITEMID;
	}

	public COOLTIME getCOOLTIME() {
		return COOLTIME;
	}

	public TIMELIMIT getTIMELIMIT() {
		return TIMELIMIT;
	}

	// SETTER-------------------------------------
	public void setPLAYERID(PLAYERID pLAYERID) {
		PLAYERID = pLAYERID;
	}

	public void setUSERID(USERID uSERID) {
		USERID = uSERID;
	}

	public void setCHARACTERID(CHARACTERID cHARACTERID) {
		CHARACTERID = cHARACTERID;
	}

	public void setTOTALTIME(TOTALTIME tOTALTIME) {
		TOTALTIME = tOTALTIME;
	}

	public void setITEMID(ITEMID iTEMID) {
		ITEMID = iTEMID;
	}

	public void setCOOLTIME(COOLTIME cOOLTIME) {
		COOLTIME = cOOLTIME;
	}

	public void setTIMELIMIT(TIMELIMIT tIMELIMIT) {
		TIMELIMIT = tIMELIMIT;
	}

}
