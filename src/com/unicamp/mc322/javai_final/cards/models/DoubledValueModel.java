package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;

public class DoubledValueModel extends SpellCardModel {

	DoubledValueModel() {
		super("card.doubled_value.name", "card.doubled_value.desc", 6);
	}
	
	@Override
	public void onSummon() {
		//– Nome: Valor Redobrado. Custo: 6. Efeito: Cure inteiramente um
		//aliado; Dobre o ataque e defesa deste aliado.

		Card c = null;
		
		MinionCardModel cModel = (MinionCardModel)c.getModel();
		c.setHealth(cModel.getBaseHealth() * 2);
		c.setDamage(c.getDamage() * 2);
	}

}
