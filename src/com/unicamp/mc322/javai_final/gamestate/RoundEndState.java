package com.unicamp.mc322.javai_final.gamestate;

import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;

public class RoundEndState extends GameState {
	private List<Integer> defendSelection;
	private List<Integer> attackSelection;
	
	protected RoundEndState(GameStateManager manager) {
		super(manager);
	}

	@Override
	public void onStateLoad() {
		DefendState defendState = getManager().defendState;
		defendSelection = defendState.getDefendSelection();
		attackSelection = defendState.getAttackSelection();
		
		for(int i = 0;i < attackSelection.size();i++) {
			Card attacking = getManager().getCurrentPlayer().getFieldCards()[attackSelection.get(i)];
			if(defendSelection.get(i) == -1) {
				getManager().getOpponentPlayer().takeNexusDamage(attacking.getDamage());
				continue;
			}
			
			Card defending = getManager().getOpponentPlayer().getFieldCards()[defendSelection.get(i)];
			
			getManager().doCombat(attacking, defending);
		}
		
		System.err.println("Avancei player e mudei de turno");
		getManager().advancePlayer();
		getManager().setState(getManager().summonState);
	}
}
