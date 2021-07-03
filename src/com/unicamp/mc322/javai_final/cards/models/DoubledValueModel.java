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
				int index = Integer.parseInt(input);
				
				Card c = manager.getCurrentPlayer().getFieldCards()[index];
				
				MinionCardModel cModel = (MinionCardModel)c.getModel();
				c.setHealth(Math.max(c.getHealth(), cModel.getBaseHealth()) * 2);
				c.setDamage(c.getDamage() * 2);
				
			}
		});
	}

}
