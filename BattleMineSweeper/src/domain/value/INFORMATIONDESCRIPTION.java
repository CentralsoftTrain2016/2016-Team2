package domain.value;


public class INFORMATIONDESCRIPTION extends Value {
	private String value;

	public INFORMATIONDESCRIPTION(String value) {
		super();
		this.value = value;
	}

	public String get() {
		return value;
	}
}