package kr.co.network.constant;

public enum Line {
	LINE1("01호선"),
	LINE2("02호선"),
	LINE3("03호선"),
	LINE4("04호선"),
	LINE5("05호선"),
	LINE6("06호선"),
	LINE7("07호선"),
	LINE8("08호선"),
	LINE9("09호선"),
	GYEONGGANG("경강선"),
	GYEONGUI("경의선"),
	GYEONGCHUN("경춘선"),
	AREX("공항철도"),
	GIMPOGOLDLINE("김포도시철도"),
	BUNDANG("분당선"),
	SEOHAE("서해선"),
	SUIN("수인선"),
	SHINBUNDANG("신분당선"),
	EVERLINE("용인경전철"),
	UI("우이신설선"),
	ULINE("의정부경전철"),
	INCHEONSUBWAYLINE2("인천2호선"),
	INCHEONSUBWAYLINE1("인천선");

	final String name;

	public String getName(){
		return this.name;
	}

	private Line(String name){
		this.name = name;
	}

}
