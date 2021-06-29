package com.unicamp.mc322.javai_final.gamestate;
import java.util.ArrayList;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.DefenderModel;
import com.unicamp.mc322.javai_final.cards.models.PoroModel;


public class InitState extends GameState{
	private boolean selectionConfirmed;
	private ArrayList<Integer> cardsIndices; 
 	
	protected InitState(GameStateManager manager) {
		super(manager);
		cardsIndices = new ArrayList<Integer>();
	}
	
	private void createDeck() {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new PoroModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new DefenderModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new DefenderModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new DefenderModel()));
			getManager().getPlayers()[i].addCardToPile(new Card(new DefenderModel()));
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
		// render
		if(selectionConfirmed) {
			getManager().setState(new SummonState(getManager()));
			return;
		}

	}
	
	// entrada tem que ser do tipo 0, 1, 2, ...
	// escrever done quando terminar
	public void onInput(String input) {	
		if(input.equals("done")) {
			int indices[] = new int[cardsIndices.size()];
			
			for(int i = 0;i < indices.length;i++) {
				indices[i] = cardsIndices.get(i);
			}
			
			askForCardsChanges(indices);
			selectionConfirmed = true;
			return;
		}
		
		cardsIndices.add(Integer.parseInt(input) - Integer.parseInt("0"));
	}
}
