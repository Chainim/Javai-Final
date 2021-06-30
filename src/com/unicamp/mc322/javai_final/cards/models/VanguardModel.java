package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class VanguardModel extends MinionCardModel {

	VanguardModel() {
		super("card.vanguard.name", "card.vanguard.desc", 4, 3, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onSummon() {
		// +1/+1 para todos os seguidores aliados em campo (campeoes nao inclusos)
		
		super.onSummon();
	}
}
