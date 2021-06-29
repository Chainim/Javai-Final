package com.unicamp.mc322.javai_final;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.unicamp.mc322.javai_final.display.Screen;
import com.unicamp.mc322.javai_final.display.TextScreen;
import com.unicamp.mc322.javai_final.gamestate.GameStateManager;
import com.unicamp.mc322.javai_final.lang.Lang;
import com.unicamp.mc322.javai_final.lang.Localizer;

public class Game {
	
	private GameStateManager stateManager;
	private boolean running;
	
	private JFrame window;
	private JLabel label;
	private JTextArea textArea;
	private Screen screen;
	
	public Game() {
		 stateManager = new GameStateManager();
		 
		 window = new JFrame("Game");
		 window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 window.setSize(960, 720);
		 
		 label = new JLabel();
		 textArea = new JTextArea();
		 
		 window.add(label);
		 window.add(textArea, BorderLayout.SOUTH);
		 
		 window.setLocationRelativeTo(null);
		 
		 screen = new TextScreen(label);
	}
	
	public void start() {
		Localizer.localizerInit(Lang.PT_BR);
		ModelRegisty.initModels();
		
		running = true;
		
		stateManager.init();
		
		window.setVisible(true);
		
		loop();
	}
	
	void readInput() {
		if(textArea.getText().endsWith("\n")) {
			String tex = textArea.getText();
			textArea.setText("");
			stateManager.onInput(tex.substring(0, tex.length() - 1));
		}
	}
	
	public void loop() {
		while(running) {
			readInput();
			stateManager.update();
			stateManager.draw(screen);
			screen.render();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.start();
		

		//Screen d = new TextScreen();
		//for(int i = 0; i < 6; i++)
		//	d.drawCard(30 + 14 * i, 5);
		//d.drawNexus(0, 0);
		//d.render();
	}
}
