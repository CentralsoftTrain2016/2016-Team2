package domain.value;

import java.util.List;

import domain.Mass;

public class MASSLIST extends Value{
	private List<Mass> value;

	public MASSLIST(List<Mass> value){
		super();
		this.value = value;
	}

	public List<Mass> get(){
		return value;
	}
}
