package domain.value;

public class PLAYERID extends Value {
	private int value;

	public PLAYERID(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}
}