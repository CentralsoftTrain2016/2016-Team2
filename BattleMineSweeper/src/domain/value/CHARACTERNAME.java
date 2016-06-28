package domain.value;

public class CHARACTERNAME extends Value {
	private String value;

	public CHARACTERNAME(String value) {
		super();
		this.value = value;
	}

	public String get() {
		return value;
	}
}
