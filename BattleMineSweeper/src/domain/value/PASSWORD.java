package domain.value;

public class PASSWORD extends Value {
	private String value;

	public PASSWORD(String value) {
		super();
		this.value = value;
	}

	public String get() {
		return value;
	}
}
