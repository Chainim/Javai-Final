package com.unicamp.mc322.javai_final.gamestate;

public class SummonState extends GameState {

	private int toSummonIndex;
	private int toSummonFieldIndex;
	private boolean summonConfirmed;
	private boolean summonEnd;
	
	protected SummonState(GameStateManager manager) {
		super(manager);
	}
	
	@Override
	public void onStateLoad() {
		System.out.println("entrou loadSummon");
		summonConfirmed = false;
		summonEnd = false;
	}
	
	@Override
	public void update() {
		if(summonEnd) {
			getManager().setState(new AttackState(getManager()));
			return;
		}
		
		if(summonConfirmed) {
			getManager().getCurrentPlayer().summonCard(toSummonIndex, toSummonFieldIndex);
			summonConfirmed = false;
		}
	}
	
	@Override
	public void onInput(String input) {
		if(input.equals("done")) {
			summonEnd = true;
			return;
		}
		
		toSummonIndex = Integer.valueOf(input);
		summonConfirmed = true;
	}
}
