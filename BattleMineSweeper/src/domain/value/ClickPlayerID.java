package domain.value;

public class ClickPlayerID extends Value {
	private int value;

	public ClickPlayerID(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}
}
