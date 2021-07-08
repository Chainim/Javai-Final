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
	public void onSummon(Card card) {
		GameStateManager manager = GameStateManager.getInstance();

		SummonState g = (SummonState) manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public void onInput(String input) {
				if(manager.getCurrentPlayer().isAI()) {
					for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
						for(int j = 0;j < manager.getOpponentPlayer().getFieldCards().length;j++) {
							if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getOpponentPlayer().getFieldCards()[j] != null) {
								manager.doCombat(manager.getCurrentPlayer().getFieldCards()[i], manager.getOpponentPlayer().getFieldCards()[j]);
								return;
							}
						}
					}
					
				} else {
					
					String[] s = input.split(" ");
					int[] indices = new int[s.length];
					try {
						for (int i = 0; i < indices.length; i++)
							indices[i] = Integer.parseInt(s[i]);
					} catch (NumberFormatException e) {
						System.err.println("Invalid input");
						onSummon(card);
						return;
					}
	
					Card attacking = manager.getCurrentPlayer().getFieldCards()[indices[0]];
					Card defending = manager.getOpponentPlayer().getFieldCards()[indices[1]];
	
					manager.doCombat(attacking, defending);
				}
			}
		});
	}

}
