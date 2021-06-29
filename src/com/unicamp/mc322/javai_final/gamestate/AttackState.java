package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

public class AttackState extends GameState {

	private boolean selectionConfirmed;
	private ArrayList<Integer> cardsIndices; 
 	
	protected AttackState(GameStateManager manager) {
		super(manager);
		cardsIndices = new ArrayList<Integer>();
	}
	
	public int[] getAttackSelection() {
		int selection[] = new int[cardsIndices.size()];
		
		for(int i = 0;i < selection.length;i++) {
			selection[i] = cardsIndices.get(i);
		}
		
		return selection;
	}
	
	@Override
	public void onStateLoad() {
		selectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(selectionConfirmed) {
			getManager().setState(new DefendState(getManager(), getAttackSelection()));
		}
	}
	
	// colocar done no final
	public void onInput(String input) {	
		if(input.equals("done")) {
			selectionConfirmed = true;
			return;
		}
		
		cardsIndices.add(Integer.parseInt(input) - Integer.parseInt("0"));
	}
}
