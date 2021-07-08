package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.gamestate.InputListener;
import com.unicamp.mc322.javai_final.gamestate.SummonState;

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
			public void onInput(String input) {
				Card c = null;
				if(manager.getCurrentPlayer().isAI()) {
					int maxDamageCard = 0;
					for(int i = 0;i < manager.getCurrentPlayer().getFieldCards().length;i++) {
						if(manager.getCurrentPlayer().getFieldCards()[i] != null && manager.getCurrentPlayer().getFieldCards()[i].getDamage() > maxDamageCard) {
							c = manager.getCurrentPlayer().getFieldCards()[i];
							maxDamageCard = c.getDamage();
						}
					}
					if(c == null)
						return;
				} else {
					int index;
					try {
						index = Integer.parseInt(input);
					} catch (NumberFormatException e)  {
						System.err.println("Invalid input");
						onSummon(card);
						return;
					}
					
					if(index > 5 || manager.getCurrentPlayer().getFieldCards()[index] == null) {
						System.err.println("Invalid input");
						onSummon(card);
						return;
					}
					
					c = manager.getCurrentPlayer().getFieldCards()[index];
				}
				
				MinionCardModel cModel = (MinionCardModel)c.getModel();
				c.setHealth(Math.max(c.getHealth(), cModel.getBaseHealth()) * 2);
				c.setDamage(c.getDamage() * 2);
				
			}
		});
	}

}
