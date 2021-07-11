package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;
import com.unicamp.mc322.javai_final.util.InputUtils;

public class OnTargetModel extends SpellCardModel {

	OnTargetModel() {
		super("card.on_target.name", "card.on_target.desc", 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onSummon(Card card) {
		GameStateManager manager = GameStateManager.getInstance();
		
		if(!manager.getCurrentPlayer().hasFieldCards()) {
			System.err.println("Nao existe carta para aplicar o golpe certeiro");
			manager.getCurrentPlayer().getHandCards().add(new Card(ModelRegistry.ON_TARGET));
			return;
		}
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
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
				
				c.setHealth(c.getHealth() + 1);
				c.setDamage(c.getDamage() + 1);
				manager.addToRemoveDamage(c);
				manager.addToRemoveHealth(c);
				return true;
			}
		});
	}

}
