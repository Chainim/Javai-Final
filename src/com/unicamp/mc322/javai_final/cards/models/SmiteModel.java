package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.SpellCardModel;

public class SmiteModel extends SpellCardModel {

	SmiteModel() {
		super("card.smite.name", "card.smite.desc", 8);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onSummon() {
		// um aliado ataca todos os inimigos
	}
}
