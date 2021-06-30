package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

public class AttackState extends GameState {

	private boolean selectionConfirmed;
	private ArrayList<Integer> cardsIndices; 
 	
	protected AttackState(GameStateManager manager) {
		super(manager);
		cardsIndices = new ArrayList<Integer>();
	}
	
	@Override
	public void onStateLoad() {
		selectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(selectionConfirmed) {
			getManager().setState(new DefendState(getManager(), cardsIndices));
		}
	}
	
	// colocar done no final
	public void onInput(String input) {	
		if(input.equals("done")) {
			selectionConfirmed = true;
			return;
		}
		int id = Integer.parseInt(input);
		
		if(cardsIndices.contains(id) == true || getManager().getCurrentPlayer().getFieldCards()[id] == null) {
			System.out.println("Nao foi possivel selecionar monstro para ataque");
			return;
		}
		
		cardsIndices.add(id);
	}
}
