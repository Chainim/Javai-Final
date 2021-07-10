package com.unicamp.mc322.javai_final.gamestate;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

import com.unicamp.mc322.javai_final.display.InterfaceScreen;

public class DefendState extends GameState {
	private int defendID, attackID;
	private boolean defendSelectionConfirmed;
	private ArrayList<Integer> defendSelection;
	private ArrayList<Integer> attackSelection;
	private ArrayList<Integer> toRenderAttack;
	private ArrayList<Integer> toRenderDefend;
 	
	protected DefendState(GameStateManager manager) {
		super(manager);
		this.defendSelection = new ArrayList<Integer>();
		toRenderAttack = new ArrayList<Integer>();
		toRenderDefend = new ArrayList<Integer>();
	}
	
	@Override
	public void onStateLoad() {
		// lembrar de printar attackSelection
		attackSelection = getManager().attackState.getCardsIndices();
		defendSelection.clear();
		toRenderAttack.clear();
		toRenderDefend.clear();
		
		for(int i = 0;i < attackSelection.size();i++) {
			defendSelection.add(-1);
		}
		
		defendID = -1;
		attackID = -1;
		defendSelectionConfirmed = false;
		
	}
	
	@Override
	public void update() {
		if(defendSelectionConfirmed == true) {
			getManager().setState(getManager().roundEndState);
		}
	}
	
	// colocar -1 caso não queira defender ataque
	// colocar (carta de defesa carta de ataque)
	public void onInput(String input) {
		if(getManager().getOpponentPlayer().isAI()) {
			
			for(int i : attackSelection) {
				for(int j = 0;j < getManager().getOpponentPlayer().getFieldCards().length;j++) { // j defende i
					if(getManager().getOpponentPlayer().getFieldCards()[j] != null && 
					   defendSelection.contains(j) == false && 
					  (getManager().getCurrentPlayer().getFieldCards()[i].getElusiveStatus() == false || getManager().getOpponentPlayer().getFieldCards()[j].getElusiveStatus() == true)) {
						defendSelection.set(attackSelection.indexOf(i), j);
					}
				}
			}
			defendSelectionConfirmed = true;
			return;
		}
		
		String[] s = input.split(" ");
		if(input.equals("done")) {
			if(attackID != -1 && defendID != -1) {
				toRenderAttack.add(attackID);
				toRenderDefend.add(defendID);
				
				defendSelection.set(attackSelection.indexOf(attackID), defendID);	
				defendID = -1;
				attackID = -1;
			} else if(attackID == -1 && defendID == -1) {
				defendSelectionConfirmed = true;				
			} else {
				System.err.println("Seleção de defesa invalida");
			}
		} else if(s[0].equals("hand")) {
			System.err.println("Escolha as cartas do campo");
		} else if(Integer.parseInt(s[2]) == getManager().getOpponentPlayerIndex()) {
			if(defendID == Integer.parseInt(s[1]))
				defendID = -1;
			else {
				defendID = Integer.parseInt(s[1]);
				if(getManager().getOpponentPlayer().getFieldCards()[defendID] == null) {
					System.err.println("Selecione um campo com aliado para defesa");
					defendID = -1;
				} else if(toRenderDefend.contains(defendID)) {
					System.err.println("Carta já foi pareada para defender");
					defendID = -1;
				}
			}
		} else if(Integer.parseInt(s[2]) == getManager().getCurrentPlayerIndex()) {
			if(attackID == Integer.parseInt(s[1]))
				attackID = -1;
			else {
				attackID = Integer.parseInt(s[1]);
				if(getManager().getCurrentPlayer().getFieldCards()[attackID] == null || !attackSelection.contains(attackID)) {
					System.err.println("Selecione um campo com inimigo para defesa");
					attackID = -1;
				} else if(toRenderAttack.contains(attackID)) {
					System.err.println("Carta já foi pareada para ser defendida");
					attackID = -1;
				}
			}		
		}
		
	}
	
	public ArrayList<Integer> getAttackSelection() {
		return attackSelection;
	}
	
	public ArrayList<Integer> getDefendSelection() {
		return defendSelection;
	}
	
	@Override
	public void onRender() {
		Color[] colors = new Color[] {
			(Color.GREEN),
			(Color.CYAN),
			(Color.LIGHT_GRAY),
			(Color.MAGENTA),
			(Color.ORANGE),
			(Color.PINK)
		};
			
		int j = 0;
		for(int i : toRenderAttack) {
			JButton button = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*getManager().getCurrentPlayerIndex());
			button.setBackground(colors[j]);
			j++;
		}
		j = 0;
		for(int i : toRenderDefend) {
			JButton button = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*getManager().getOpponentPlayerIndex());
			button.setBackground(colors[j]);
			j++;
		}
		
		for(int i = 0;i < 6;i++) {
			if(toRenderDefend.contains(i))
				continue;
			JButton defendButton = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*getManager().getOpponentPlayerIndex());
			if(defendID == i) {
				defendButton.setBackground(Color.BLUE);
			} else {
				defendButton.setBackground(Color.GRAY);
			}
		}
		for(int i = 0;i < 6;i++) {
			if(toRenderAttack.contains(i))
				continue;
			JButton attackButton = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*getManager().getCurrentPlayerIndex());
			if(attackID == i) {
				attackButton.setBackground(Color.BLUE);
			} else if(attackSelection.contains(i)) {
				attackButton.setBackground(Color.RED);
			} else {
				attackButton.setBackground(Color.GRAY);
			}
		}
	}
}
