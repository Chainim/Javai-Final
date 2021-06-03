package com.unicamp.mc322.javai_final.gamestate;

public class AttackState extends GameState {

	private boolean selectionConfirmed;
	private int[] selection;
 	
	protected AttackState(GameStateManager manager) {
		super(manager);
	}
	
	public int[] getAttackSelection() {
		return selection;
	}
	
	@Override
	public void onStateLoad() {
		selectionConfirmed = false;
	}
	
	@Override
	public void update() {
		if(selectionConfirmed) {
			getManager().setState(new DefendState(getManager()));
		}
	}
	
	// 0, 1, 2, ...
	public void onInput(String input) {
		String[] aux = input.split(", ");
		int[] indices = new int[aux.length];
		
		for(int i = 0; i < aux.length; i++)
			indices[i] = Integer.valueOf(aux[i]);
		
		selection = indices;
		selectionConfirmed = true;
	}
}
