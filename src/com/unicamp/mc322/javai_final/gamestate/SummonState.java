package com.unicamp.mc322.javai_final.gamestate;

import java.util.LinkedList;
import java.util.List;

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
			summonConfirmed = false;
		}
	}
	
	// Digite o indice do monstro da sua mao, enter, o numero do indice da mesa e done para sumonar
	@Override
	public void onInput(String input) {
		if(!listeners.isEmpty()) {
			InputListener i = listeners.remove(0);
			i.onInput(input);
			return;
		}
		if(input.equals("done")) {
			summonEnd = true;
		} else {
			String[] s = input.split(" ");
			if(s.length < 2) {
				System.err.println("Invalid input for summoning. Expected two integers.");
				return;
			}
			toSummonIndex = Integer.valueOf(s[0]);
			toSummonFieldIndex = Integer.valueOf(s[1]);
			summonConfirmed = true;
		}
	}
	
	public void addListener(InputListener i) {
		listeners.add(i);
	}
}
