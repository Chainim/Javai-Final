package com.unicamp.mc322.javai_final.display;

import com.unicamp.mc322.javai_final.cards.CardModel;

public abstract class Screen {
	public abstract void drawCard(int posx, int posy, CardModel m);
	public abstract void drawNexus(int posx, int posy);
	public abstract void render();
}
