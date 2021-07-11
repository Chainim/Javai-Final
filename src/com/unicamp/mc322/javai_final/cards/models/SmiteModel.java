package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.SummonState;
import com.unicamp.mc322.javai_final.player.Player;
import com.unicamp.mc322.javai_final.util.InputUtils;

public class SmiteModel extends SpellCardModel {

	SmiteModel() {
		super("card.smite.name", "card.smite.desc", 8);
	}
	
	private static void combat(Card attacker, Player defender) {
		for(int i = 0; i < 6; i++)
			if(defender.getFieldCards()[i] != null) {
				Card defending = defender.getFieldCards()[i];
				defending.takeDamage(attacker.getDamage());
				if(defending.getHealth() <= 0) {
					defending.onDeath();
					attacker.onKill();
					defender.getFieldCards()[i] = null;
				}
			}
	}
	
	@Override
	public void onSummon(Card card) {
		//Efeito: Um aliado atacante golpeia todos os oponentes defensores
		//Como ele 'golpeia', nao eh um embate?
		
		GameStateManager manager = GameStateManager.getInstance();
		
		if(!manager.getCurrentPlayer().hasFieldCards()) {
			System.err.println("Nao existe carta para aplicar o Julgamento");
			manager.getCurrentPlayer().getHandCards().add(new Card(ModelRegistry.SMITE));
			manager.getCurrentPlayer().rollbackMana();
			return;
		}
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener((String input) -> {
			Card c = null;
			
			if(manager.getCurrentPlayer().isAI()) {
				int maxDamageCard = 0;
				for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
					if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getCurrentPlayer().getFieldCards()[i].getDamage() > maxDamageCard) {
						c = manager.getCurrentPlayer().getFieldCards()[i];
						maxDamageCard = c.getDamage();
					}
				}
			} else {
				int index = InputUtils.expectCardOnCurrentPlayerField(input);
				if(index == -1)
					return false;
				c = manager.getCurrentPlayer().getFieldCards()[index];
			}
			
			Card attacking = c;
			
			combat(attacking, manager.getOpponentPlayer());
			return true;
		});
	}
}
