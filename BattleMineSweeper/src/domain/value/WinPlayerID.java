package domain.value;

public class WinPlayerID extends Value {
	private int value;

	public WinPlayerID(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}
}
