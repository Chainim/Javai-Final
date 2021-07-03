package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.player.Player;

public class PoroDefenderModel extends MinionCardModel {

	PoroDefenderModel() {
		super("card.poro_defender.name", "card.poro_defender.desc", 1, 1, 2);
	}

	@Override
	public void onDeath(Card c) {
		Player p = c.getOwner();
		p.drawCardsFromPile();
	}
}
