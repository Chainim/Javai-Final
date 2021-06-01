package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class TianaModel extends MinionCardModel {
	TianaModel() {
		super("card.tiana.name", "card.tiana.desc", 8, 7, 7);
	}
	@Override
	public void onSummon() {
		// Uma das cartas sumonadas ataca o nexus inimigo
		
		super.onSummon();
	}
}
