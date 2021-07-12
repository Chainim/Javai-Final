package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.unicamp.mc322.javai_final.cards.Card;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;
import com.unicamp.mc322.javai_final.player.Player;

public class GameStateManager {
	
	private static GameStateManager instance;
	
	private GameState currentState;
	private Player[] players;
	private int currentPlayerIndex;
	private ArrayList<Card> toRemoveDamage;
	private ArrayList<Card> toRemoveHealth;
	
	private int currentRound;
	
	InitState initState;
	SummonState summonState;
	AttackState attackState;
	DefendState defendState;
	RoundEndState roundEndState;
	
	public GameStateManager() {
		instance = this;
		
		players = new Player[2];
		currentPlayerIndex = 0;
		currentRound = 1;
		toRemoveDamage = new ArrayList<Card>();
		toRemoveHealth = new ArrayList<Card>();
		
		
		initState = new InitState(this);
		summonState = new SummonState(this);
		attackState = new AttackState(this);
		defendState = new DefendState(this);
		roundEndState = new RoundEndState(this);
		
		currentState = initState;
	}
	
	public int getCurrentRound() {
		return currentRound;
	}
	
	public void init() {
		players[0] = new Player(false);
		players[1] = new Player(false); // Coloque true para jogar com IA
			
		players[0].setMana(0);
		players[1].setMana(0);
		currentRound = 1;
	
		currentState.onStateLoad();
	}
	
	public GameState getCurrentState() {
		return currentState;
	}
	
	public void setState(GameState state) {
		System.err.println("Trocando para o estado: " + state.getClass().getSimpleName());
		currentState.onStateUnload();
		currentState = state;
		currentState.onStateLoad();
	}
	
	public void update() {
		currentState.update();
	}
	
	public void turnAIOn() {
		players[1].turnAIOn();
	}
	
	public void turnAIOff() {
		players[1].turnAIOff();
	}
	
	Player[] getPlayers() {
		return players;
	}
	
	public Player getCurrentPlayer() {
		return players[currentPlayerIndex];
	}
	
	public Player getOpponentPlayer() {
		return players[(currentPlayerIndex + 1) % 2];
	}
	
	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	
	public int getOpponentPlayerIndex() {
		return (currentPlayerIndex + 1) % 2;
	}
	
	
	public void advancePlayer() {
		if(currentPlayerIndex == players.length - 1)
			currentRound++;
		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
	}
	
	public void onInput(String input) {
		currentState.onInput(input);
	}
	
	public void drawInterface() {
		
	}
	
	public void draw() {
		
		for(int i = 0;i < players.length;i++) {
			InterfaceScreen.getInterfaceScreen().getManaBars()[i].setValue(players[i].getMana());
			InterfaceScreen.getInterfaceScreen().getManaBars()[i].setString("Mana: " + players[i].getMana() + " (+" + players[i].getSpellMana() + ")");
			InterfaceScreen.getInterfaceScreen().getManaBars()[i + 2].setValue(players[i].getSpellMana());
			
			InterfaceScreen.getInterfaceScreen().getNexusHealth()[i].setText("Nexus: " + players[i].getNexusHealth());
			
			for(int j = players[i].getHandCards().size();j < 10;j++) {
				JButton button = InterfaceScreen.getInterfaceScreen().getHandCards().get(j + i * 10);
				button.setVisible(false);
			}
			
			for(int j = 0;j < players[i].getHandCards().size();j++) {
				JButton button = InterfaceScreen.getInterfaceScreen().getHandCards().get(j + i * 10);
				Card c = players[i].getHandCards().get(j);
				button.setVisible(true);
				
				if(c.isMinion()) {
					((JLabel) button.getComponent(1)).setVisible(true);
					((JLabel) button.getComponent(2)).setVisible(true);
					((JLabel) button.getComponent(1)).setText(Integer.toString(c.getDamage()));
					((JLabel) button.getComponent(2)).setText(Integer.toString(c.getHealth()));
				}
				else {
					((JLabel) button.getComponent(1)).setVisible(false);
					((JLabel) button.getComponent(2)).setVisible(false);
				}
				((JLabel) button.getComponent(0)).setText(Integer.toString(c.getManaCost()));
				((JLabel) button.getComponent(3)).setText(c.getLocalizedName());
				((JLabel) button.getComponent(4)).setText("<html>" + c.getLocalizedDescription() + "</html>");
			}
			
			for(int j = 0;j < players[i].getFieldCards().length;j++) {
				JButton button = InterfaceScreen.getInterfaceScreen().getFieldCards().get(j + 6*i);
				
				Card c = players[i].getFieldCards()[j];
				
				if(c == null) {
					((JLabel) button.getComponent(0)).setText("");
					((JLabel) button.getComponent(1)).setText("");
					((JLabel) button.getComponent(2)).setText("");
					continue;
				}	
				
				((JLabel) button.getComponent(0)).setText(Integer.toString(c.getDamage()));
				((JLabel) button.getComponent(1)).setText(Integer.toString(c.getHealth()));
				((JLabel) button.getComponent(2)).setText("<html>" + c.getLocalizedName() + "</html>");
			}
		}
		
		currentState.onRender();
	}
	
	public static GameStateManager getInstance() {
		return instance;
	}

	public void doCombat(Card attacking, Card defending) {
		defending.takeDamage(attacking.getDamage());
		attacking.takeDamage(defending.getDamage());
		
		boolean attackerDead = false, defenderDead = false;
		
		if(attacking.getHealth() <= 0) {
			attacking.onDeath();
			attackerDead = true;
			this.getCurrentPlayer().getFieldCards()[attacking.getFieldIndex()] = null;
		} else {
			attacking.onAttack();			
		}
		if(defending.getHealth() <= 0) {
			defending.onDeath();
			defenderDead = true;
			this.getOpponentPlayer().getFieldCards()[defending.getFieldIndex()] = null;
		}
		
//		if(attackerDead && defenderDead)
//			return;
		
		if(attackerDead)
			defending.onKill();
		if(defenderDead)
			attacking.onKill();
	}

	public void addToRemoveDamage(Card c) {
		toRemoveDamage.add(c);
	}
	
	public void addToRemoveHealth(Card c) {
		toRemoveHealth.add(c);
	}
	
	public void clearRemove() {
		toRemoveDamage.clear();
		toRemoveHealth.clear();
	}
	
	public void processRemove() {
		for(Card c : toRemoveHealth) {
			c.setHealth(c.getHealth() - 1);
			if(c.getHealth() == 0) {
				c.getOwner().getFieldCards()[c.getFieldIndex()] = null;
				c = null;
			}
		}
		for(Card c : toRemoveDamage) {
			c.setDamage(c.getDamage() - 1);
		}
	}
}
