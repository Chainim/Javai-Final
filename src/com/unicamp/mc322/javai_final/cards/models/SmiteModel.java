package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class SmiteModel extends SpellCardModel {

	SmiteModel() {
		super("card.smite.name", "card.smite.desc", 8);
	}
	
	@Override
	public void onSummon() {
		//Efeito: Um aliado atacante golpeia todos os oponentes defensores
		//Como ele 'golpeia', nao eh um embate?
		
		GameStateManager manager = GameStateManager.getInstance();
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public void onInput(String input) {
				int index = Integer.parseInt(input);
				Card attacking = manager.getCurrentPlayer().getFieldCards()[index];
				
				for(int i = 0; i < 6; i++)
					if(manager.getOpponentPlayer().getFieldCards()[i] != null) {
						Card defending = manager.getOpponentPlayer().getFieldCards()[i];
						defending.takeDamage(attacking.getDamage());
					}
						
				
			}
		});
	}
}
