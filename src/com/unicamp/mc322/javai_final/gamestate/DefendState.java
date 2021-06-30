package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

public class DefendState extends GameState {
	private boolean defendSelectionConfirmed;
	private ArrayList<Integer> defendSelection;
	private ArrayList<Integer> attackSelection;
	
 	
	protected DefendState(GameStateManager manager, ArrayList<Integer> attackSelection) {
		super(manager);
		this.attackSelection = attackSelection;
	}
	
	@Override
	public void onStateLoad() {
		// lembrar de printar attackSelection
		
		for(int i = 0;i < attackSelection.size();i++) {
			attackSelection.add(-1);
		}
		
		defendSelectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(defendSelectionConfirmed == true) {
			getManager().setState(new RoundEndState(getManager(), defendSelection, attackSelection));
		}
	}
	
	// colocar -1 caso não queira defender ataque
	// colocar (carta de defesa carta de ataque)
	public void onInput(String input) {
		if(input.equals("done")) {
			return;
		}
		
		String[] l = input.split(" ");
		
		int id1 = Integer.parseInt(l[0]);
		int id2 = Integer.parseInt(l[1]);
		
		if(getManager().getOpponentPlayer().getFieldCards()[id1] == null || attackSelection.contains(id2) == false) {
			System.out.println("Não foi possivel selecionar monstro para defesa");
			return;
		}
		
		defendSelection.set(attackSelection.indexOf(id2), id1); 
	}

}
