package com.unicamp.mc322.javai_final.gamestate;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;

import com.unicamp.mc322.javai_final.display.InterfaceScreen;

public class AttackState extends GameState {

	private boolean selectionConfirmed;
	private Set<Integer> cardsIndices; 
 	
	protected AttackState(GameStateManager manager) {
		super(manager);
		cardsIndices = new HashSet<Integer>();
	}
	
	@Override
	public void onStateLoad() {
		cardsIndices.clear();
		selectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(selectionConfirmed) {
			getManager().setState(getManager().defendState);
		}
	}
	
	// colocar done no final
	public void onInput(String input) {	
		if(getManager().getCurrentPlayer().isAI()) {
			for(int i = 0;i < getManager().getCurrentPlayer().getFieldCards().length;i++) {
				if(getManager().getCurrentPlayer().getFieldCards()[i] != null) {
					cardsIndices.add(i);
				}
			}
			selectionConfirmed = true;
			return;
		}
		
		if(input.equals("done")) {
			selectionConfirmed = true;
			return;
		}
				
		String[] s = input.split(" ");
		
		if(Integer.parseInt(s[2]) != (getManager().getCurrentPlayerIndex()) || s[0].equals("hand") || getManager().getCurrentPlayer().getFieldCards()[Integer.parseInt(s[1])] == null) {
			System.err.println("Escolha as cartas do seu campo válidas");
		} else {
			if(cardsIndices.contains(Integer.parseInt(s[1])))
				cardsIndices.remove(Integer.parseInt(s[1]));
			else
				cardsIndices.add(Integer.parseInt(s[1]));
		}
	}
	
	public ArrayList<Integer> getCardsIndices(){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ret.addAll(cardsIndices);
		return ret;
	}
	
	@Override
	public void onRender() {
		for(int i = 0;i < 6;i++) {
			JButton button = InterfaceScreen.getInterfaceScreen().getFieldCards().get(i + 6*getManager().getCurrentPlayerIndex());
			if(cardsIndices.contains(i)) {
				button.setBackground(Color.RED);	
			} else {
				button.setBackground(Color.GRAY);
			}
		}
	}
}
