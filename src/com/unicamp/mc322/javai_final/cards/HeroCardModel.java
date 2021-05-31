package com.unicamp.mc322.javai_final.cards;

public abstract class HeroCardModel extends MinionCardModel {

	protected HeroCardModel(String unlocalizedName, String unlocalizedDescription, int manaCost, int baseDamage,
			int baseHealth) {
		super(unlocalizedName, unlocalizedDescription, manaCost, baseDamage, baseHealth);
	}

}
