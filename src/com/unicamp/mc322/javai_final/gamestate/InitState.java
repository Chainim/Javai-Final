package com.unicamp.mc322.javai_final.gamestate;

public class InitState extends GameState{
	private boolean selectionConfirmed;
	
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

	private void askForCardsChanges(int[] indices) {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].swapCards(indices);
		}
	}
	
	public void onStateLoad() {
		selectionConfirmed = false;
		shuffleCards();
		giveCards();
	}
	
	public void update() {
		if(selectionConfirmed) {
			getManager().setState(new SummonState(getManager()));
			return;
		}
		
	}
	
	@Override
	public void onInput(String input) {
		if(input.equals("done")) {
			selectionConfirmed = true;
			return;
		}
		
		String[] aux = input.split(", ");
		int[] indices = new int[aux.length];
		for(int i = 0; i < aux.length; i++)
			indices[i] = Integer.valueOf(aux[i]);
		
		askForCardsChanges(indices);
		
	}
}
