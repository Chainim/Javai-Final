package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class DefenderModel extends MinionCardModel {
	
	DefenderModel() {
		super("card.defender.name", "card.defender.desc", 2, 2, 2);
	}
	
	@Override
	public void onKill(Card c) {
		// furia +0/+1
		c.setHealth(c.getHealth() + 1);
	}
}
