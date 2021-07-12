package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.SummonState;
import com.unicamp.mc322.javai_final.util.InputUtils;

public class DoubledValueModel extends SpellCardModel {

	DoubledValueModel() {
		super("card.doubled_value.name", "card.doubled_value.desc", 6);
	}
	
	@Override
	public void onSummon(Card card) {
		//– Nome: Valor Redobrado. Custo: 6. Efeito: Cure inteiramente um
		//aliado; Dobre o ataque e defesa deste aliado.
		GameStateManager manager = GameStateManager.getInstance();
		if(!card.getOwner().hasFieldCards()) {
			System.err.println("Nao existe carta para aplicar o valor redobrado");
			card.getOwner().getHandCards().add(new Card(ModelRegistry.DOUBLED_VALUE));
			card.getOwner().rollbackMana();
			return;
		}
		
		SummonState g = (SummonState)manager.getCurrentState();
		
		g.addListener((String input) -> {
			Card c = null;
			
			if(card.getOwner().isAI()) {
				int maxDamageCard = 0;
				for(int i = 0;i < card.getOwner().getFieldCards().length;i++) {
					if(card.getOwner().getFieldCards()[i] != null && card.getOwner().getFieldCards()[i].getDamage() > maxDamageCard) {
						c = card.getOwner().getFieldCards()[i];
						maxDamageCard = c.getDamage();
					}
				}
			} else {
				int index = InputUtils.expectCardOnCurrentPlayerField(input);
				if(index == -1)
					return false;
				c = card.getOwner().getFieldCards()[index];
			}
			
			MinionCardModel cModel = (MinionCardModel)c.getModel();
			c.setHealth(Math.max(c.getHealth(), cModel.getBaseHealth()) * 2);
			c.setDamage(c.getDamage() * 2);
			return true;
		});
	}

}
