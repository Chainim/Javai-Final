package com.unicamp.mc322.javai_final.cards;

public abstract class MinionCardModel extends CardModel {
	private int baseDamage;
	private int baseHealth;
	protected MinionCardModel(String unlocalizedName, String unlocalizedDescription, int manaCost, int baseDamage, int baseHealth) {
		super(unlocalizedName, unlocalizedDescription, manaCost);
		this.baseDamage = baseDamage;
		this.baseHealth = baseHealth;
	}
}
