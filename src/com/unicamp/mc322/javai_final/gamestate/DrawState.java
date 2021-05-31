package com.unicamp.mc322.javai_final.gamestate;

public class DrawState extends GameState {

	protected DrawState(GameStateManager manager) {
		super(manager);
	}
	
	@Override
	public void onStateLoad() {
		getManager().getCurrentPlayer().drawCardsFromPile();
		getManager().setState(new SummonState(getManager()));
	}
}
