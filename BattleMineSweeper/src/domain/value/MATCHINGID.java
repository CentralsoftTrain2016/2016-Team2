package domain.value;

public class MATCHINGID extends Value {
	private int value;

	public MATCHINGID(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}
}