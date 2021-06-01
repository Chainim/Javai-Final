package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class PoroDefenderModel extends MinionCardModel {

	public PoroDefenderModel() {
		super("card.poro_defender.name", "card.poro_defender.desc", 1, 1, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDeath() {
		// puxa uma carta do deck
	}
}
