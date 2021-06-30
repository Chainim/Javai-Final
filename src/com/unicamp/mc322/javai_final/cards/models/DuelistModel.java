package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;

public class DuelistModel extends MinionCardModel {

	DuelistModel() {
		super("card.duelist.name", "card.duelist.desc", 3, 3, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onSummon() {
		// Se destruir um seguidor inimigo nesta rodada, 
		// o jogador ganha uma carta do tipo "Poro" para sua mao
		
		super.onSummon();
	}
}
