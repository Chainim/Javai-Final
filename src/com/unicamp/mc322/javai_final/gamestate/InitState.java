package com.unicamp.mc322.javai_final.gamestate;
import java.util.ArrayList;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.DefenderModel;
import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.cards.models.PoroModel;


public class InitState extends GameState{
	private int switchingPlayer;
	private ArrayList<Integer> cardsIndices; 
 	
	protected InitState(GameStateManager manager) {
		super(manager);
		cardsIndices = new ArrayList<Integer>();
		switchingPlayer = 0;
	}
	
	private void createDeck() {
		for(int i = 0;i < getManager().getPlayers().length;i++) {
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
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
		getManager().getPlayers()[switchingPlayer].swapCards(indices);
//		System.err.printf("Troquei as cartas que o player %d escolheu, vez do proximo player\n", switchingPlayer);
	}
	
	public void onStateLoad() {
		System.err.println("entrou loadInit");
		switchingPlayer = 0;
		
		createDeck();
		shuffleCards();
		giveCards();
	}
	
	public void update() {
		// render
		if(switchingPlayer >= getManager().getPlayers().length || (getManager().getOpponentPlayer().isAI() && switchingPlayer == 1)) {
			getManager().setState(getManager().summonState);
			return;
		}

	}
	
	// entrada tem que ser do tipo 0, enter, 1, enter, ...
	// escrever done quando terminar
	public void onInput(String input) {	
		if(input.equals("Done") || getManager().getCurrentPlayer().isAI()) {
			int indices[] = new int[cardsIndices.size()];
			
			for(int i = 0;i < indices.length;i++) {
				indices[i] = cardsIndices.get(i);
			}
			
			askForCardsChanges(indices);
			switchingPlayer++;
			cardsIndices.clear();
		} else {
			cardsIndices.add(Integer.parseInt(input) - Integer.parseInt("0"));			
		}
	}
}
