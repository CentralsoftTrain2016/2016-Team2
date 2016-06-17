package domain.value;

public class QUIZDESCRIPTION extends Value {
	private String value;

	public QUIZDESCRIPTION(String value) {
		super();
		this.value = value;
	}

	public String get() {
		return value;
	}
}