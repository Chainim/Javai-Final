package com.unicamp.mc322.javai_final.cards;

public abstract class SpellCardModel extends CardModel {

	protected SpellCardModel(String unlocalizedName, String unlocalizedDescription, int manaCost) {
		super(unlocalizedName, unlocalizedDescription, manaCost);
	}
	
	@Override
	public boolean isSpell() {
		return true;
	}
	
	@Override
	public boolean isMinion() {
		return false;
	}
}
