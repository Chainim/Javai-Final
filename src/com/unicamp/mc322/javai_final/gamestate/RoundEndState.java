package com.unicamp.mc322.javai_final.gamestate;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.unicamp.mc322.javai_final.Game;
import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;

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
		for(int i = 0;i < 6;i++) {
			if(getManager().getCurrentPlayer().getFieldCards()[i] != null)
				getManager().getCurrentPlayer().getFieldCards()[i].onTurnEnd();
		}
		
		for(int i = 0;i < 6;i++) {
			for(int j = 0;j < 2;j++) {
				JButton button = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*j);
				button.setBackground(Color.GRAY);
			}
		}
		
		if(getManager().getOpponentPlayer().getNexusHealth() <= 0) {
			System.err.println("Jogo encerrado, o jogador " + getManager().getCurrentPlayerIndex() + " venceu");
			Game.getInstance().stop();
		}
		getManager().processRemove();
		getManager().clearRemove();
		getManager().advancePlayer();
		if(getManager().getCurrentPlayer().getDrawPile().empty()) {
			System.err.println("Jogo encerrado, o jogador " + (getManager().getCurrentPlayerIndex() + 1) % 2 + " venceu");
			Game.getInstance().stop();
		}
		getManager().setState(getManager().summonState);
	}
}
