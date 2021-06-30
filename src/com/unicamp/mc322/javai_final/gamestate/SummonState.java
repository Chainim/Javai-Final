package com.unicamp.mc322.javai_final.gamestate;

public class SummonState extends GameState {

	private int toSummonIndex, toSummonFieldIndex;
	private boolean summonConfirmed;
	private boolean summonEnd;
	
	protected SummonState(GameStateManager manager) {
		super(manager);
	}
	
	@Override
	public void onStateLoad() {
		System.err.println("entrou loadSummon");
		toSummonIndex = -1;
		summonConfirmed = false;
		summonEnd = false;
		getManager().getCurrentPlayer().addMana();
		getManager().getCurrentPlayer().drawCardsFromPile();
	}
	
	@Override
	public void update() {
		if(summonEnd) {
			getManager().setState(new AttackState(getManager()));
			return;
		}
		
		if(summonConfirmed) {
			boolean summoned = getManager().getCurrentPlayer().summonCard(toSummonIndex, toSummonFieldIndex); 
			if(!summoned) {
				System.err.println("Couldnt summon this monster");
			}
			summonEnd = false;
			toSummonIndex = -1;
			summonConfirmed = false;
		}
	}
	
	// Digite o indice do monstro da sua mao, enter, o numero do indice da mesa e done para sumonar
	@Override
	public void onInput(String input) {
		if(input.equals("done")) {
			summonEnd = true;
		} else {
			if(toSummonIndex == -1)
				toSummonIndex = Integer.valueOf(input);
			else {				
				toSummonFieldIndex = Integer.valueOf(input);
				summonConfirmed = true;
			}
		}
	}
}
