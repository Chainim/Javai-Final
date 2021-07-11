package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;
import com.unicamp.mc322.javai_final.util.InputUtils;

public class TianaModel extends MinionCardModel {
	TianaModel() {
		super("card.tiana.name", "card.tiana.desc", 8, 7, 7);
	}
	
	@Override
	public void onDraw(Card card) {
		// Uma das cartas sumonadas ataca o nexus inimigo
		GameStateManager manager = GameStateManager.getInstance();
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				Card c = null;
				if(!manager.getCurrentPlayer().hasFieldCards()) {
					System.err.println("Nao existe carta para aplicar o Efeito de Tiana");
					return true;
				}
				
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
				
				manager.getOpponentPlayer().takeNexusDamage(c.getDamage());
				return true;
			}
		});
	}
}
