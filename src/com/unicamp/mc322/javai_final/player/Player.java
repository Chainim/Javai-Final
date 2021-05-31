package com.unicamp.mc322.javai_final.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.unicamp.mc322.javai_final.cards.Card;

public class Player {
	private int nexusHealth;
	private int mana;
	private int spellMana;
	private Card[] fieldCards;
	private List<Card> handCards;
	private Stack<Card> drawPile;
	
	public Player() {
		
		this.mana = 0;
		this.spellMana = 0;
		this.nexusHealth = 20;
		
		this.fieldCards = new Card[6];
		this.handCards = new ArrayList<Card>();
		this.drawPile = new Stack<Card>();
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
	
	public void drawCardsFromPile(int count) {
		for(int i = 0; i < count; i++) {
			Card top = drawPile.pop();
			handCards.add(top);
		}
	}
	
	public void drawCardsFromPile() {
		drawCardsFromPile(1);
	}
	
	public void swapCards(int indices[]) {
		Arrays.sort(indices);
		for(int i = indices.length - 1; i >= 0; i--) {
			handCards.remove(indices[i]);
		}
		
		drawCardsFromPile(indices.length);
	}
}
