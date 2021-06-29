package com.unicamp.mc322.javai_final.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	
	public void shufflePile() {
		Collections.shuffle(drawPile);
	}
	
	public void addCardToPile(Card c) {
		drawPile.add(c);
	}
	
	public void drawCardsFromPile(int count) {
		for(int i = 0; i < count; i++) {
			if(drawPile.empty()) {
				return;
			}
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
			drawPile.add(handCards.get(indices[i]));
			handCards.remove(indices[i]);
		}
		
		drawCardsFromPile(indices.length);
	}
	
	public void summonCard(int indice, int fieldIndice) {
		if(handCards.get(indice).getManaCost() > getMana())
			return;
		
		Card c = handCards.remove(indice);
		mana -= c.getManaCost();
		fieldCards[fieldIndice] = c;
		c.onSummon();
	}
	
	public List<Card> getHandCards() {
		return handCards;
	}
}
