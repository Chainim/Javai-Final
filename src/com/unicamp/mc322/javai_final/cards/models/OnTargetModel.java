package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.SummonState;
import com.unicamp.mc322.javai_final.util.InputUtils;

public class OnTargetModel extends SpellCardModel {

	OnTargetModel() {
		super("card.on_target.name", "card.on_target.desc", 1);
	}
	
	@Override
	public void onSummon(Card card) {
		GameStateManager manager = GameStateManager.getInstance();
		
		if(!card.getOwner().hasFieldCards()) {
			System.err.println("Nao existe carta para aplicar o golpe certeiro");
			card.getOwner().addCardOnHand(new Card(ModelRegistry.ON_TARGET));
			card.getOwner().rollbackMana();
			return;
		}
		
		if(card.getOwner().isAI()) {
			Card c = null;
			int maxDamageCard = 0;
			for(int i = 0;i < card.getOwner().getFieldCards().length;i++) {
				if(card.getOwner().getFieldCards()[i] != null && card.getOwner().getFieldCards()[i].getDamage() > maxDamageCard) {
					c = card.getOwner().getFieldCards()[i];
					maxDamageCard = c.getDamage();
				}
			}
			c.setHealth(c.getHealth() + 1);
			c.setDamage(c.getDamage() + 1);
			manager.addToRemoveDamage(c);
			manager.addToRemoveHealth(c);
			return;
		}
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener((String input) -> {
			Card c = null;
			
			{
				int index = InputUtils.expectCardOnCurrentPlayerField(input);
				if(index == -1)
					return false;
				c = card.getOwner().getFieldCards()[index];
			}
			
			c.setHealth(c.getHealth() + 1);
			c.setDamage(c.getDamage() + 1);
			manager.addToRemoveDamage(c);
			manager.addToRemoveHealth(c);
			return true;
		});
	}

}
