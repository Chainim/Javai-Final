package com.unicamp.mc322.javai_final.gamestate;

import java.util.LinkedList;
import java.util.List;

import com.unicamp.mc322.javai_final.cards.SpellCardModel;

public class SummonState extends GameState {

	private int toSummonIndex, toSummonFieldIndex;
	private boolean summonConfirmed;
	private boolean summonEnd;
	
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
		getManager().getCurrentPlayer().addMana();
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
			while(true) {
				
				int idx = -1;
				for(int i = 0;i < getManager().getCurrentPlayer().getFieldCards().length;i++) {
					if(getManager().getCurrentPlayer().getFieldCards()[i] == null) {
						idx = i;
						break;
					}
				}
				
				if(idx == -1)
					break;
				
				for(int i = 0;i < getManager().getCurrentPlayer().getHandCards().size();i++) {
					if(getManager().getCurrentPlayer().summonCard(i, idx)) {
						idx = -1;
						break;
					}
				}
				
				if(idx != -1)
					break;
			}
			summonEnd = true;
			return;
		}
		
		if(!listeners.isEmpty()) {
			InputListener i = listeners.remove(0);
			i.onInput(input);
			return;
		}
		if(input.equals("done")) {
			summonEnd = true;
		} else {
			String[] s = input.split(" ");
			if(toSummonIndex == -1) {
				if(s[0].equals("hand")) {
					toSummonIndex = Integer.valueOf(s[1]);
					if(getManager().getCurrentPlayer().getHandCards().get(toSummonIndex).getModel() instanceof SpellCardModel) {
						summonConfirmed = true;
					}
				} else {
					System.err.println("Selecione uma carta da mao");
				}
			} else if(toSummonFieldIndex == -1) {
				if(s[0].equals("field")) {
					toSummonFieldIndex = Integer.valueOf(s[1]);
					summonConfirmed = true;
				} else {
					System.err.println("Selecione uma posição do campo");
				}
			} else {
				summonConfirmed = true;		
			}
		}
	}
	
	public void addListener(InputListener i) {
		listeners.add(i);
	}
}
