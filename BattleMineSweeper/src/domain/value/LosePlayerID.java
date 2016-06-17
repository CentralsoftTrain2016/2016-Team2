package domain.value;

public class LosePlayerID extends Value {
	private int value;

	public LosePlayerID(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}
}
