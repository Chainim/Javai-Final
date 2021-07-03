package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.HeroCardModel;

public class GarenModel extends HeroCardModel {

	GarenModel() {
		super("card.garen.name", "card.garen.desc", 5, 5, 5);
	}
	
	@Override
	public void onTurnEnd(Card c) {
		
	}
}
