package com.unicamp.mc322.javai_final.gamestate;

import java.util.ArrayList;

import com.unicamp.mc322.javai_final.display.Screen;

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
		
		String[] s = input.split(" ");
		
		for(int i = 0;i < s.length;i++) {
			int id = Integer.parseInt(s[i]);
			
			if(cardsIndices.contains(id) == true || id > 5 || getManager().getCurrentPlayer().getFieldCards()[id] == null) {
				System.out.println("Nao foi possivel selecionar monstro para ataque");
				return;
			}
			cardsIndices.add(id);
		}
	}
	
	@Override
	public void onRender(Screen s) {
		final int xoffset = 18;
		for(Integer i : cardsIndices) {
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
