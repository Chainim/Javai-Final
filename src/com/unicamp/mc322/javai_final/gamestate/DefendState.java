package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

import com.unicamp.mc322.javai_final.display.Screen;

public class DefendState extends GameState {
	private boolean defendSelectionConfirmed;
	private ArrayList<Integer> defendSelection;
	private ArrayList<Integer> attackSelection;
	
 	
	protected DefendState(GameStateManager manager, ArrayList<Integer> attackSelection) {
		super(manager);
		this.attackSelection = attackSelection;
		this.defendSelection = new ArrayList<Integer>();
	}
	
	@Override
	public void onStateLoad() {
		// lembrar de printar attackSelection
		
		for(int i = 0;i < attackSelection.size();i++) {
			defendSelection.add(-1);
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
			defendSelectionConfirmed = true;
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
	
	@Override
	public void onRender(Screen s) {
		final int xoffset = 18;
		for(Integer i : attackSelection) {
			int yPos;
			if(getManager().currentPlayerIndex == 0) {
				yPos = 20;
			} else {
				yPos = 20 - 5;
			}
			s.drawStringCentered(yPos + 1, xoffset + 10 + i * 8 + 3, "*");
		}
	}
}
