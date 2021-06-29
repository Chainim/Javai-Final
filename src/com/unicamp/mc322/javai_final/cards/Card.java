package com.unicamp.mc322.javai_final.cards;

public class Card {
	private CardModel model;
	private int health;
	private int damage;
	
	public Card(CardModel model) {
		this.model = model;
		if(model instanceof MinionCardModel) {
			MinionCardModel mmodel = (MinionCardModel)model;
			this.health = mmodel.getBaseHealth();
			this.damage = mmodel.getBaseDamage();
		}
		else {
			this.health = -1;
			this.damage = -1;
		}
	}
	
	public CardModel getModel() {
		return model;
	}
	
	public int getManaCost() {
		return model.getManaCost();
	}
	
	public String getUnlocalizedName() {
		return model.getUnlocalizedName();
	}
	
	public String getUnlocalizedDescription() {
		return model.getUnlocalizedDescription();
	}
	
	public void onSummon() {
		model.onSummon();
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
}
