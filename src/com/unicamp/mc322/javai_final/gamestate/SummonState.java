package com.unicamp.mc322.javai_final.gamestate;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;

public class SummonState extends GameState {

	private int toSummonIndex, toSummonFieldIndex;
	private boolean summonConfirmed;
	private boolean summonEnd;
	public Card attacking1v1;
	public Card defending1v1;
	
	private List<InputListener> listeners;
	
	protected SummonState(GameStateManager manager) {
		super(manager);
		listeners = new LinkedList<InputListener>();
	}

	
	@Override
	public void onStateLoad() {
		System.err.println("entrou loadSummon");
		toSummonIndex = -1;
		toSummonFieldIndex = -1;
		summonConfirmed = false;
		summonEnd = false;
		attacking1v1 = null;
		defending1v1 = null;
		getManager().getCurrentPlayer().setMana(getManager().getCurrentRound());
		getManager().getCurrentPlayer().drawCardsFromPile();
	}
	
	@Override
	public void update() {
		if(summonEnd) {
			getManager().getCurrentPlayer().calcSpellMana();
			getManager().setState(getManager().attackState);
			return;
		}
		
		if(summonConfirmed) {
			boolean summoned = getManager().getCurrentPlayer().summonCard(toSummonIndex, toSummonFieldIndex); 
			if(!summoned) {
				System.err.println("Couldnt summon this monster");
			}
			summonEnd = false;
			toSummonIndex = -1;
			toSummonFieldIndex = -1;
			summonConfirmed = false;
		}
	}
	
	// Digite o indice do monstro da sua mao, enter, o numero do indice da mesa e done para sumonar
	@Override
	public void onInput(String input) {
		if(getManager().getCurrentPlayer().isAI()) {
				
			for(int j = 0;j < getManager().getCurrentPlayer().getHandCards().size();j++) {
				for(int i = 0;i < getManager().getCurrentPlayer().getFieldCards().length;i++) {
					if(getManager().getCurrentPlayer().getFieldCards()[i] == null && 
					   getManager().getCurrentPlayer().getHandCards().get(j) != null) {
						if(getManager().getCurrentPlayer().summonCard(j, i)) {
							break;
						}
					}	
				}
			}

			summonEnd = true;
			return;
		}
		
		if(!listeners.isEmpty()) {
			InputListener i = listeners.get(0);
			if(i.onInput(input))
				listeners.remove(0);
			return;
		}
		if(input.equals("done")) {
			if(toSummonFieldIndex == -1 && toSummonIndex == -1) {
				summonEnd = true;				
			} else if(toSummonIndex != -1 && (toSummonFieldIndex != -1 || getManager().getCurrentPlayer().getHandCards().get(toSummonIndex).isSpell())) {
				summonConfirmed = true;
			}
			else {
				System.err.println("Seleção invalida para sumonar");					
			}
			return;
		}
		
		
		String[] s = input.split(" ");
		int s1Int = Integer.parseInt(s[1]);
		
		if(Integer.parseInt(s[2]) != (getManager().getCurrentPlayerIndex())) 
			System.err.println("Selecione as suas coisas somente");
		else if(s[0].equals("hand"))
			toSummonIndex = (toSummonIndex == s1Int ? -1 : s1Int);
		else if(s[0].equals("field"))
			toSummonFieldIndex = (toSummonFieldIndex == s1Int ? -1 : s1Int);
	}
	
	@Override
	public void onRender() {
		for(int i = 0;i < getManager().getCurrentPlayer().getHandCards().size();i++) {
			JButton button = InterfaceScreen.getInterfaceScreen().getHandCards().get(i + getManager().getCurrentPlayerIndex() * 10);
			if(i == toSummonIndex) {
				button.setBackground(Color.YELLOW);				
			} else {
				button.setBackground(Color.LIGHT_GRAY);	
			}
		}
		for(int i = 0;i < 6;i++) {
			JButton button = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*getManager().getCurrentPlayerIndex());
			if(i == toSummonFieldIndex) {
				button.setBackground(Color.YELLOW);	
			} else {
				button.setBackground(Color.GRAY);	
			}
		}
	}

	public void addListener(InputListener i) {
		listeners.add(i);
	}
}
