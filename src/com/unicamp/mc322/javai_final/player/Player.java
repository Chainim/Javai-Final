package com.unicamp.mc322.javai_final.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.cards.SpellCardModel;

public class Player {
	private int nexusHealth;
	private int mana, curManaBuffer;
	private int spellMana;
	private Card[] fieldCards;
	private List<Card> handCards;
	private Stack<Card> drawPile;
	private boolean AI; 
	
	public Player(boolean isAI) {
		
		curManaBuffer = 1;
		this.mana = 0;
		this.spellMana = 0;
		this.nexusHealth = 20;
		
		this.fieldCards = new Card[6];
		this.handCards = new ArrayList<Card>();
		this.drawPile = new Stack<Card>();
		this.AI = isAI;
	}
	
	public Card[] getFieldCards() {
		return fieldCards;
	}
	
	public int getNexusHealth() {
		return nexusHealth;
	}
	
	public void takeNexusDamage(int damage) {
		nexusHealth -= damage;
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
			top.setOwner(this);
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
		
		shufflePile();
		drawCardsFromPile(indices.length);
	}
	
	public boolean summonCard(int indice, int fieldIndice) {
		if(handCards.get(indice).getManaCost() > getMana())
			return false;
		
		Card c = handCards.remove(indice);
		// Ver se o custo de substituicao eh o mesmo de sumonar em um local vazio
		mana -= c.getManaCost();
		if(c.getModel() instanceof MinionCardModel)
			fieldCards[fieldIndice] = c;
		c.onSummon();
		return true;
	}
	
	public void addMana() { 
		mana = curManaBuffer;
		curManaBuffer++;
		curManaBuffer = Math.min(10, curManaBuffer);
	}
	
	public List<Card> getHandCards() {
		return handCards;
	}

	public void calcSpellMana() {
		spellMana = Math.min(3, mana);
		mana = 0;
	}

	public boolean isAI() {
		return AI;
	}
}
