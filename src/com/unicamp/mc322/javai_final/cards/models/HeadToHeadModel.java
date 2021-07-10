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
				Card attacking = null;
				Card defending = null;
				boolean possibleAttackSelection = false;
				boolean possibleDefendSelection = false;
				for(int i = 0;i < 6;i++) {
					if(manager.getCurrentPlayer().getFieldCards()[i] != null)
						possibleAttackSelection = true;
					if(manager.getOpponentPlayer().getFieldCards()[i] != null)
						possibleDefendSelection = true;
				}
				if(!possibleAttackSelection || !possibleDefendSelection) {
					System.err.println("Sem cartas para fazer o mano a mano");
					manager.getCurrentPlayer().getHandCards().add(new Card(ModelRegistry.HEAD_TO_HEAD));
					return;
				}
				
				if(manager.getCurrentPlayer().isAI()) {
					for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
						for(int j = 0;j < manager.getOpponentPlayer().getFieldCards().length;j++) {
							if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getOpponentPlayer().getFieldCards()[j] != null) {
								attacking = manager.getCurrentPlayer().getFieldCards()[i]; 
								defending = manager.getOpponentPlayer().getFieldCards()[j];
							}
						}
					}
				} else {
					int attackID = -1;
					int defendID = -1;
					
					String[] s = input.split(" ");
					// TODO
				}
				manager.doCombat(attacking, defending);
			}
		});
	}

}
