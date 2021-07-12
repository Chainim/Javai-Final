package com.unicamp.mc322.javai_final.gamestate;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;


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
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.GAREN));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.GAREN));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.TIANA));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.TIANA));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.VANGUARD));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.VANGUARD));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.VANGUARD));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.VANGUARD));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DUELIST));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DUELIST));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DUELIST));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DUELIST));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DEFENDER));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.PORO_DEFENDER));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.SMITE));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.DOUBLED_VALUE));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.ON_TARGET));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.ON_TARGET));
			
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.HEAD_TO_HEAD));
			getManager().getPlayers()[i].addCardToPile(new Card(ModelRegistry.HEAD_TO_HEAD));
			
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
		shuffleCards();
	}
	
	public void onStateLoad() {
		System.err.println("entrou loadInit");
		switchingPlayer = 0;
		
		createDeck();
		shuffleCards();
		giveCards();
	}
	
	public void update() {
		if(switchingPlayer >= getManager().getPlayers().length || (getManager().getOpponentPlayer().isAI() && switchingPlayer == 1)) {
			getManager().setState(getManager().summonState);
			return;
		}

	}
	
	public void onInput(String input) {	
		if(input.equals("done") || getManager().getCurrentPlayer().isAI()) {
			int[] indices = new int[cardsIndices.size()];
			for(int i = 0;i < indices.length;i++) {
				indices[i] = cardsIndices.get(i);
			}
			
			askForCardsChanges(indices);
			cardsIndices.clear();
			onRender();
			switchingPlayer++;
		} else {
			String[] s = input.split(" ");
			if(Integer.parseInt(s[2]) != (switchingPlayer) || s[0].equals("field")) {
				System.err.println("Selecione cartas da sua mão para troca!");
			} else {
				int idx = Integer.parseInt(s[1]) ;
				if(cardsIndices.contains(idx)) {
					cardsIndices.remove(cardsIndices.indexOf(idx));
				}
				else {					
					cardsIndices.add(idx);	
				}
			}
		}
	}
	
	@Override
	public void onRender() {
		for(int i = 0;i < getManager().getCurrentPlayer().getHandCards().size();i++) {
			JButton button = InterfaceScreen.getInterfaceScreen().getHandCards().get(i + switchingPlayer * 10);
			if(cardsIndices.contains(i)) {
				button.setBackground(Color.YELLOW);				
			} else {
				button.setBackground(Color.LIGHT_GRAY);	
			}
		}
	}
}
