package com.unicamp.mc322.javai_final.cards;

import com.unicamp.mc322.javai_final.player.Player;

public class Card {
	private CardModel model;
	private int health;
	private int damage;
	private int attack_count;
	private boolean isElusive;
	private boolean level_up;
	private Player owner;
	
	public Card(CardModel model) {
		//TODO: Consertar isso aqui
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
		this.attack_count = 0;
		this.isElusive = false;
		this.level_up = false;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
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
		model.onSummon(this);
	}
	
	public void onDeath() {
		if(model instanceof MinionCardModel)
			((MinionCardModel)model).onDeath(this);
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void takeDamage(int damage) {
		health -= damage;
	}
	
	public void onKill() {
		if(model instanceof MinionCardModel)
			((MinionCardModel)model).onKill(this);
	}
	
	public void setOwner(Player p) {
		this.owner = p;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int getFieldIndex() {
		for(int i = 0;i < owner.getFieldCards().length;i++) {
			if(owner.getFieldCards()[i] == this) {
				return i; 				
			}
		}
		return -1;
	}
	
	public void addAttackCount() {
		attack_count++;
	}
	
	public int getAttackCount() {
		return attack_count;
	}
	
	public void levelUp() {
		if(model instanceof HeroCardModel) {
			level_up = true;
			((HeroCardModel) model).levelUp(this);
		}
	}
	
	public void turnElusiveOn() {
		isElusive = true;
	}
	
	public boolean getElusiveStatus() {
		return isElusive;
	}
	
	public boolean isLeveldUp() {
		return level_up;
	}
}
