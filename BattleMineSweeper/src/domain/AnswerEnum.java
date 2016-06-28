package domain;

import java.util.HashMap;
import java.util.Map;

public enum AnswerEnum {
	YES(0),
	NO(1);

	private Integer value;
	private static Map<Integer, AnswerEnum> map = new HashMap<Integer, AnswerEnum>();


	static{
		map.put(0, YES);
		map.put(1, NO);
	}

	private AnswerEnum(Integer value) {
		this.value = value;
	}

	public int get() {
		return value.intValue();
	}

	public static AnswerEnum getAnswerByValue(int value){
		return map.get(value);
	}

}
