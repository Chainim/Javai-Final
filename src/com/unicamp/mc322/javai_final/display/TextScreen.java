package com.unicamp.mc322.javai_final.display;

public class TextScreen extends Screen{

	char[][] screenbuff;
	
	public TextScreen() {
		screenbuff = new char[10][120];
	}
	
	@Override
	public void drawCard(int posx, int posy, int c, int a, int d) {
		int cardWidth = 8;
		int cardHeight = 7;
		for(int i = 0; i < cardWidth; i++) {
			screenbuff[posy][posx + i] = '-';
			screenbuff[posy + cardHeight - 1][posx + i] = '-';
		}
		for(int i = 0; i < cardHeight; i++) {
			screenbuff[posy + i][posx + 0] = '|';
			screenbuff[posy + i][posx + cardWidth - 1] = '|';
		}
		screenbuff[posy + 0][posx + 0] = '+';
		screenbuff[posy + 0][posx + cardWidth - 1] = '+';
		screenbuff[posy + cardHeight - 1][posx + 0] = '+';
		screenbuff[posy + cardHeight - 1][posx + cardWidth - 1] = '+';
		
		screenbuff[posy + 1][posx + 1] = 'c';
		screenbuff[posy + cardHeight - 2][posx + 1] = 'a';
		screenbuff[posy + cardHeight - 2][posx + cardWidth - 2] = 'd';
	}

	@Override
	public void render() {
		for(int i = 0; i < screenbuff.length; i++) {
			for(int j = 0; j < screenbuff[i].length; j++)
				System.out.print(screenbuff[i][j]);
			System.out.println();
		}
	}



}
