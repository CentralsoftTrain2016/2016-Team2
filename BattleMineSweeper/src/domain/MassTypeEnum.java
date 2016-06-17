package domain;


public enum MassTypeEnum {
	MASSCLOSE,
	MASSTYPE0,
	MASSTYPE1,
	MASSTYPE2,
	MASSTYPE3,
	MASSTYPE4,
	MASSTYPE5,
	MASSTYPE6,
	MASSTYPE7,
	MASSTYPE8,
	MASSITEM,
	MASSQUIZ,
	MASSMINE,
	MASSFLAG;

	public String url;
	//masImg/mas1.png
	static{
		MASSCLOSE.url = "masImg/mas1.png";
		MASSTYPE0.url = "masImg/masType0.png";
		MASSTYPE1.url = "masImg/masType1.png";
		MASSTYPE2.url = "masImg/masType2.png";
		MASSTYPE3.url = "masImg/masType3.png";
		MASSTYPE4.url = "masImg/masType4.png";
		MASSTYPE5.url = "masImg/masType5.png";
		MASSTYPE6.url = "masImg/masType6.png";
		MASSTYPE7.url = "masImg/masType7.png";
		MASSTYPE8.url = "masImg/masType8.png";
		MASSITEM.url = "masImg/masType1.png";
		MASSQUIZ.url = "masImg/masType1.png";
		MASSMINE.url = "masImg/masType9.png";
		MASSFLAG.url = "masImg/mas3.png";
	}
}
