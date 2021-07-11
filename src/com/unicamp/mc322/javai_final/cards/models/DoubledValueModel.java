package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
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
		
		SummonState g = (SummonState)manager.getCurrentState();
		g.addListener(new InputListener() {
			@Override
			public boolean onInput(String input) {
				Card c = null;
				if(!manager.getCurrentPlayer().hasFieldCards()) {
					System.err.println("Nao existe carta para aplicar o valor redobrado");
					manager.getCurrentPlayer().getHandCards().add(new Card(ModelRegistry.DOUBLED_VALUE));
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
				
				MinionCardModel cModel = (MinionCardModel)c.getModel();
				c.setHealth(Math.max(c.getHealth(), cModel.getBaseHealth()) * 2);
				c.setDamage(c.getDamage() * 2);
				return true;
			}
		});
	}

}
