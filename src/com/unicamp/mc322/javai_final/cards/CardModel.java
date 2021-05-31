package com.unicamp.mc322.javai_final.cards;

public abstract class CardModel {
	private final String unlocalizedName;
	private final String unlocalizedDescription;
	private final int manaCost;
	
	protected CardModel(String unlocalizedName, String unlocalizedDescription, int manaCost) {
		this.unlocalizedName = unlocalizedName;
		this.unlocalizedDescription = unlocalizedDescription;
		this.manaCost = manaCost;
	}
	
	public int getManaCost() {
		return manaCost;
	}
	
	public String getUnlocalizedName() {
		return unlocalizedName;
	}
	
	public String getUnlocalizedDescription() {
		return unlocalizedDescription;
	}
	
	public void onSummon() {
		
	}
	
	public void onTurnEnd() {
		
	}
}
