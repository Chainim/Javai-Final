package com.unicamp.mc322.javai_final.gamestate;
import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.PoroModel;

public class InitState extends GameState{
	private boolean selectionConfirmed;
	
	protected InitState(GameStateManager manager) {
		super(manager);
	}
	
	private void createDeck() {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
		}
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
		System.out.println("entrou loadInit");
		selectionConfirmed = false;
		
		createDeck();
		shuffleCards();
		giveCards();
	}
	
	public void update() {
		if(selectionConfirmed) {
			getManager().setState(new SummonState(getManager()));
			return;
		}
		
	}
	
	// entrada tem que ser do tipo 0, 1, 2, ...
	public void onInput(String input) {	
		input = input.replace(" ","");
		
		String[] aux = input.split(",");
		int[] indices = new int[aux.length];
		
		for(int i = 0; i < aux.length; i++)
			indices[i] = Integer.valueOf(aux[i]);
		
		askForCardsChanges(indices);
		selectionConfirmed = true;
	}
}
