package com.unicamp.mc322.javai_final;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.unicamp.mc322.javai_final.cards.CardModel;
import com.unicamp.mc322.javai_final.cards.models.PoroModel;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.lang.Lang;
import com.unicamp.mc322.javai_final.lang.Localizer;

public class Game {
	
	private GameStateManager stateManager;
	private boolean running;
	
	private JFrame window;
	private JLabel label;
	private JTextArea textArea;
	
	public Game() {
		 stateManager = new GameStateManager();
		 
		 window = new JFrame("Game");
		 window.setSize(640, 480);
		 
		 label = new JLabel();
		 textArea = new JTextArea();
		 
		 window.add(label);
		 window.add(textArea, BorderLayout.SOUTH);
		 
		 window.setLocationRelativeTo(null);
		 
		 
	}
	
	public void start() {
		running = true;
		
		stateManager.init();
		Localizer.localizerInit(Lang.PT_BR);
		
		window.setVisible(true);
		
		loop();
	}
	
	void readInput() {
		if(textArea.getText().endsWith("\n")) {
			stateManager.onInput(textArea.getText().substring(0, textArea.getText().length() - 1));
			textArea.setText("");
		}
	}
	
	public void loop() {
		while(running) {
			readInput();
			stateManager.update();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		//Game g = new Game();
		//g.start();
		
		//Screen d = new TextScreen();
		//for(int i = 0; i < 6; i++)
		//	d.drawCard(30 + 14 * i, 5);
		//d.drawNexus(0, 0);
		//d.render();
	}
}
