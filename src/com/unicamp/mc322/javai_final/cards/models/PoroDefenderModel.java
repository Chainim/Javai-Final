package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.player.Player;

public class PoroDefenderModel extends MinionCardModel {

	PoroDefenderModel() {
		super("card.poro_defender.name", "card.poro_defender.desc", 1, 1, 2);
	}

	@Override
	public void onDeath() {
		// puxa uma carta do deck
		//TODO: Precisa saber quem é o dono dessa carta
		GameStateManager g = GameStateManager.getInstance();
		Player p = null;
		p.drawCardsFromPile();
	}
}
