package com.unicamp.mc322.javai_final.cards.models;

import com.unicamp.mc322.javai_final.cards.MinionCardModel;
import com.unicamp.mc322.javai_final.player.Player;

public class DuelistModel extends MinionCardModel {

	DuelistModel() {
		super("card.duelist.name", "card.duelist.desc", 3, 3, 2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onKill() {
		 //Se a carta destruir um seguidor do inimigo nesta rodada, uma carta “Poro” e colocada em sua mao.
		//TODO: pegar o player correto
		Player p = null;
		p.getHandCards().add(new Card(ModelRegistry.PORO));
	}
}
