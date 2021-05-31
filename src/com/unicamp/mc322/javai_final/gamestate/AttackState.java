package com.unicamp.mc322.javai_final.gamestate;

public class AttackState extends GameState {

	private boolean selectionConfirmed;
	
	protected AttackState(GameStateManager manager) {
		super(manager);
	}
	
	@Override
	public void onStateLoad() {
		selectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(selectionConfirmed) {
			
			getManager().setState(new DefendState(getManager()));
		}
	}
}
