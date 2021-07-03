package com.unicamp.mc322.javai_final.cards;

public abstract class MinionCardModel extends CardModel {
	private final int baseDamage;
	private final int baseHealth;
	protected MinionCardModel(String unlocalizedName, String unlocalizedDescription, int manaCost, int baseDamage, int baseHealth) {
		super(unlocalizedName, unlocalizedDescription, manaCost);
		this.baseDamage = baseDamage;
		this.baseHealth = baseHealth;
	}
	
	public int getBaseDamage() {
		return baseDamage;
	}
	
	public int getBaseHealth() {
		return baseHealth;
	}
	
	public void onDeath(Card c) {
		
	}
	
	public void onKill(Card c) {
		
	}
}
