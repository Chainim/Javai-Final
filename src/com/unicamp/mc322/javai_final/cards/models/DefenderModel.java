package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class DefenderModel extends MinionCardModel {

	protected DefenderModel() {
		super("card.defender.name", "card.defender.desc", 2, 2, 2);
		// Tem um traco de +0/+1
	}
}
