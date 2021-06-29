package com.unicamp.mc322.javai_final.display;

import com.unicamp.mc322.javai_final.cards.CardModel;

public abstract class Screen {
	public abstract void drawCard(int posx, int posy, CardModel m);
	public abstract void drawNexus(int posx, int posy);
	public abstract void render();
	public abstract void drawBox(int posy, int posx, int height, int width);
	public abstract void drawStringLeftAnchored(int posy, int posx, String s);
	public abstract void drawStringRightAnchored(int posy, int posx, String s);
	public abstract void drawStringCentered(int posy, int posx, String s);
}
