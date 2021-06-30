package com.unicamp.mc322.javai_final.gamestate;

import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;

public class RoundEndState extends GameState {
	private List<Integer> defendSelection;
	private List<Integer> attackSelection;
  	private DefendState defendState;
	
	protected RoundEndState(GameStateManager manager, DefendState defendState) {
		super(manager);
		this.defendState = defendState;
	}

	@Override
	public void onStateLoad() {
		defendSelection = defendState.getDefendSelection();
		attackSelection = defendState.getAttackSelection();
		
		for(int i = 0;i < attackSelection.size();i++) {
			Card attacking = getManager().getCurrentPlayer().getFieldCards()[attackSelection.get(i)];
			if(defendSelection.get(i) == -1) {
				getManager().getOpponentPlayer().takeNexusDamage(attacking.getDamage());
				continue;
			}
			
			Card defending = getManager().getOpponentPlayer().getFieldCards()[defendSelection.get(i)];
			
			defending.takeDamage(attacking.getDamage());
			attacking.takeDamage(defending.getDamage());
			
			if(attacking.getHealth() <= 0) {
				attacking.onDeath();
				getManager().getCurrentPlayer().getFieldCards()[attackSelection.get(i)] = null;
			}
			if(defending.getHealth() <= 0) {
				defending.onDeath();
				getManager().getOpponentPlayer().getFieldCards()[defendSelection.get(i)] = null;
			}
		}
		
		System.err.println("Avancei player e mudei de turno");
		getManager().advancePlayer();
		getManager().setState(getManager().summonState);
	}
}
