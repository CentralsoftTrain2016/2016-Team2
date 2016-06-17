package domain;

import domain.value.DIFFICULTYID;
import domain.value.DIFFICULTYNAME;
import domain.value.MINES;

public class Difficulty {

	private DIFFICULTYID DIFFICULTYID;
	private DIFFICULTYNAME DIFFICULTYNAME;
	private MINES MINES;

	// GETTER-------------------------------------
	public DIFFICULTYID getDIFFICULTYID() {
		return DIFFICULTYID;
	}

	public DIFFICULTYNAME getDIFFICULTYNAME() {
		return DIFFICULTYNAME;
	}

	public MINES getMINES() {
		return MINES;
	}

	// SETTER-------------------------------------
	public void setDIFFICULTYID(DIFFICULTYID dIFFICULTYID) {
		DIFFICULTYID = dIFFICULTYID;
	}

	public void setDIFFICULTYNAME(DIFFICULTYNAME dIFFICULTYNAME) {
		DIFFICULTYNAME = dIFFICULTYNAME;
	}

	public void setMINES(MINES mINES) {
		MINES = mINES;
	}

}
