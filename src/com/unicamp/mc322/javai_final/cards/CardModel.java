package com.unicamp.mc322.javai_final.cards;

import com.unicamp.mc322.javai_final.lang.Localizer;

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
	
	public String getLocalizedName() {
		return Localizer.getInstance().getLocalization(getUnlocalizedName());
	}
	
	public String getUnlocalizedDescription() {
		return unlocalizedDescription;
	}
	
	public String getLocalizedDescription() {
		return Localizer.getInstance().getLocalization(getUnlocalizedDescription());
	}
	
	public void onSummon(Card c) {
		
	}
	
	public void onTurnEnd(Card c) {
		
	}

	public void onDraw(Card c) {
		
	}
	
	public abstract boolean isSpell();
	public abstract boolean isMinion();
}
