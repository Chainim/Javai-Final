package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;
import com.unicamp.mc322.javai_final.util.InputUtils;

public class HeadToHeadModel extends SpellCardModel {

	HeadToHeadModel() {
		super("card.head_to_head.name", "card.head_to_head.desc", 2);
	}

	@Override
	public void onSummon(Card card) {
		GameStateManager manager = GameStateManager.getInstance();

		SummonState g = (SummonState) manager.getCurrentState();
		if(!card.getOwner().hasFieldCards() || !manager.getOpponentPlayer().hasFieldCards()) {
			System.err.println("Sem cartas para fazer o mano a mano");
			card.getOwner().addCardOnHand(new Card(ModelRegistry.HEAD_TO_HEAD));
			card.getOwner().rollbackMana();
			return;
		}
		
		Card attacking = null;
		Card defending = null;
		if(card.getOwner().isAI()) {
			for(int i = 0;i < card.getOwner().getFieldCards().length;i++) {
				for(int j = 0;j < manager.getOpponentPlayer().getFieldCards().length;j++) {
					if(card.getOwner().getFieldCards()[i] != null && manager.getOpponentPlayer().getFieldCards()[j] != null) {
						attacking = card.getOwner().getFieldCards()[i]; 
						defending = manager.getOpponentPlayer().getFieldCards()[j];
						manager.doCombat(attacking, defending);
						return;
					}
				}
			}
		}
		
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				
				int index = InputUtils.expectCardOnCurrentPlayerField(input);
				if(index == -1)
					return false;
				g.attacking1v1 = card.getOwner().getFieldCards()[index];
				return true;
				
			}
		});
		
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				
				int index = InputUtils.expectCardOnOpponentPlayerField(input);
				if(index == -1)
					return false;
				g.defending1v1 = manager.getOpponentPlayer().getFieldCards()[index];
				if(g.attacking1v1 != null && g.defending1v1 != null) {
					manager.doCombat(g.attacking1v1, g.defending1v1);
					g.attacking1v1 = null;
					g.defending1v1 = null;
				}
				return true;
				
			}		
		});
	}
}
