package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

public class HeadToHeadModel extends SpellCardModel {

	HeadToHeadModel() {
		super("card.head_to_head.name", "card.head_to_head.desc", 2);
	}
	
	@Override
	public void onSummon() {
		GameStateManager manager = GameStateManager.getInstance();
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public void onInput(String input) {
				String[] s = input.split(" ");
				int[] indices = new int[s.length];
				for(int i = 0; i < indices.length; i++)
					indices[i] = Integer.parseInt(s[i]);
				
				Card attacking = manager.getCurrentPlayer().getFieldCards()[indices[0]];
				Card defending = manager.getOpponentPlayer().getFieldCards()[indices[1]];
				
				defending.takeDamage(attacking.getDamage());
				attacking.takeDamage(defending.getDamage());
				
				if(attacking.getHealth() <= 0) {
					attacking.onDeath();
					defending.onKill();
					manager.getCurrentPlayer().getFieldCards()[indices[0]] = null;
				}
				if(defending.getHealth() <= 0) {
					defending.onDeath();
					attacking.onKill();
					manager.getOpponentPlayer().getFieldCards()[indices[1]] = null;
				}
				
			}
		});
	}

}
