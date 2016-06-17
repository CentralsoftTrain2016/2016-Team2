package domain;

import domain.value.AROUNDMINES;
import domain.value.ENDFLAG;
import domain.value.GAMEID;
import domain.value.ISEXISTMINE;
import domain.value.ISOPEN;
import domain.value.ITEMID;
import domain.value.MASSID;
import domain.value.QUIZID;

public class Mass {
	private MASSID MASSID;
	private GAMEID GAMEID;
	private ISEXISTMINE ISEXISTMINE;
	private AROUNDMINES AROUNDMINES;
	private ISOPEN ISOPEN;
	private QUIZID QUIZID;
	private ITEMID ITEMID;
	private ENDFLAG ENDFLAG = (new ENDFLAG(false)); // 爆発せずに終了した場合に使う。

	//GETTER --------------------------------------
	public MASSID getMASSID(){
		return this.MASSID;
	}
	public GAMEID getGAMEID(){
		return this.GAMEID;
	}
	public ISEXISTMINE getISEXISTMINE(){
		return this.ISEXISTMINE;
	}
	public AROUNDMINES getAROUNDMINES(){
		return this.AROUNDMINES;
	}
	public ISOPEN getISOPEN(){
		return this.ISOPEN;
	}
	public QUIZID getQUIZID(){
		return this.QUIZID;
	}
	public ITEMID getITEMID(){
		return this.ITEMID;
	}
	public ENDFLAG getENDFLAG() {
		return ENDFLAG;
	}

	//SETTER ---------------------------------------
	public void setMASSID(MASSID mASSID){
		this.MASSID = mASSID;
	}
	public void setGAMEID(GAMEID gAMEID){
		this.GAMEID = gAMEID;
	}
	public void setISEXISTMINE(ISEXISTMINE iSEXISTMINE){
		this.ISEXISTMINE = iSEXISTMINE;
	}
	public void setAROUNDMINES(AROUNDMINES aROUNDMINES){
		this.AROUNDMINES = aROUNDMINES;
	}
	public void setISOPEN(ISOPEN iSOPEN){
		this.ISOPEN = iSOPEN;
	}

	public void setQUIZID(QUIZID qUIZID){
		this.QUIZID = qUIZID;
	}

	public void setITEMID(ITEMID iTEMID){
		this.ITEMID = iTEMID;
	}

	public void setENDFLAG(ENDFLAG eNDFLAG) {
		ENDFLAG = eNDFLAG;
	}

	// マスの状態によって表示させる状態の情報を返す。
	public MassTypeEnum getMassState(){
		MassTypeEnum mte = MassTypeEnum.MASSCLOSE;
		if(ISOPEN.get()==false){
			mte = MassTypeEnum.MASSCLOSE;
		}

		else
			if(ISEXISTMINE.get()){
				mte = MassTypeEnum.MASSMINE;
				if(ENDFLAG.get()){
					mte = MassTypeEnum.MASSFLAG;
				}
			}

			else if (AROUNDMINES.get() == 0){
				mte = MassTypeEnum.MASSTYPE0;
			}
			else if (AROUNDMINES.get() == 1){
				mte = MassTypeEnum.MASSTYPE1;
			}
			else if (AROUNDMINES.get() == 2){
				mte = MassTypeEnum.MASSTYPE2;
			}
			else if (AROUNDMINES.get() == 3){
				mte = MassTypeEnum.MASSTYPE3;
			}
			else if (AROUNDMINES.get() == 4){
				mte = MassTypeEnum.MASSTYPE4;
			}
			else if (AROUNDMINES.get() == 5){
				mte = MassTypeEnum.MASSTYPE5;
			}
			else if (AROUNDMINES.get() == 6){
				mte = MassTypeEnum.MASSTYPE6;
			}
			else if (AROUNDMINES.get() == 7){
				mte = MassTypeEnum.MASSTYPE7;
			}
			else if (AROUNDMINES.get() == 8){
				mte = MassTypeEnum.MASSTYPE8;
			}

		return mte;
	}



}