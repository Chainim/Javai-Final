package com.unicamp.mc322.javai_final.player;

import java.util.ArrayList;
import java.util.List;

import com.unicamp.mc322.javai_final.cards.Card;

public class Player {
	private int nexusHealth;
	private int mana;
	private int spellMana;
	private Card[] fieldCards;
	private List<Card> handCards;
	
	public Player() {
		
		this.mana = 0;
		this.spellMana = 0;
		this.nexusHealth = 20;
		
		this.fieldCards = new Card[6];
		this.handCards = new ArrayList<Card>();
	}
	
	public int getNexusHealth() {
		return nexusHealth;
	}
	
	public int getMana() {
		return mana;
	}
	
	public int getSpellMana() {
		return spellMana;
	}
}
