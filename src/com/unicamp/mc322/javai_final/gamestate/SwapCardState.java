package com.unicamp.mc322.javai_final.gamestate;

public class SwapCardState extends GameState {

	private int[][] discardedCardsIndices;
	private boolean selectionConfirmed;
	
	public SwapCardState(GameStateManager manager) {
		super(manager);
		discardedCardsIndices = new int[manager.getPlayers().length][];
	}
	
	@Override
	public void onStateLoad() {
		selectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(selectionConfirmed) {
			for(int i = 0; i < getManager().getPlayers().length; i++) {
				getManager().getPlayers()[i].swapCards(discardedCardsIndices[i]);
			}
			
			getManager().setState(new DrawState(getManager()));
		}
	}
}
