package com.unicamp.mc322.javai_final.gamestate;

public class InitState extends GameState{
	protected InitState(GameStateManager manager) {
		super(manager);
	}
	
	private void shuffleCards() {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].shufflePile();
		}
	}
	private void giveCards() {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].drawCardsFromPile(4);
		}
	}
	private int[] askForIndices() {
		return null;
	}
	private void askForCardsChanges() {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].swapCards(askForIndices());
		}
	}
	
	public void onStateLoad() {
		shuffleCards();
		giveCards();
	}
	
	public void update() {
		askForCardsChanges();
		getManager().setState(new SummonState(getManager()));
	}
}
