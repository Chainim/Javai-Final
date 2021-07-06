package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.HeroCardModel;

public class GarenModel extends HeroCardModel {

	GarenModel() {
		super("card.garen.name", "card.garen.desc", 5, 5, 5);
	}

	@Override
	public void onAttack(Card c) {
		c.addAttackCount();
		if(c.getAttackCount() == 2) {
			c.levelUp();
		}
	}
	
	@Override
	public void onTurnEnd(Card c) {
		c.setHealth(Math.max(c.getHealth(), c.isLeveldUp() ? getLeveldUpHealth() : getBaseHealth()));
	}
	
	@Override
	public int getLeveldUpDamage() {
		return getBaseDamage() + 1;
	}
	
	@Override
	public int getLeveldUpHealth() {
		return getBaseHealth() + 1;
	}
	
	public void levelUp(Card c) {
		c.turnElusiveOn();
		c.setDamage(getLeveldUpDamage());
	}
}
