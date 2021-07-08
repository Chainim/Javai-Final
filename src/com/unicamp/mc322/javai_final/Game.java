package com.unicamp.mc322.javai_final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.unicamp.mc322.javai_final.cards.models.ModelRegistry;
import com.unicamp.mc322.javai_final.display.InterfaceScreen;
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
	private InterfaceScreen iScreen;

	public Game() {
		stateManager = new GameStateManager();

		/*
		 * window = new JFrame("Game");
		 * window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); window.setSize(960,
		 * 720); window.setResizable(false);
		 * 
		 * label = new JLabel(); textArea = new JTextArea();
		 * 
		 * window.add(label); window.add(textArea, BorderLayout.SOUTH);
		 * 
		 * window.setLocationRelativeTo(null);
		 * 
		 * screen = new TextScreen(label);
		 */
		
		// interface grafica
		window = new JFrame("Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(960, 720));
		window.setResizable(false);
		window.setLayout(null);
		
		iScreen = new InterfaceScreen(window);
	}

	public void start() {
		Localizer.localizerInit(Lang.PT_BR);
		ModelRegistry.initModels();

		running = true;

		stateManager.init();

		window.setVisible(true);

		loop();
	}

	void readInput() {
		if (textArea.getText().endsWith("\n")) {
			String tex = textArea.getText();
			textArea.setText("");
			stateManager.onInput(tex.substring(0, tex.length() - 1));
		}
	}

	public void loop() {
		
		iScreen.show();
		
		while (running) {
			//readInput();
			stateManager.update();
			stateManager.draw(screen);
			//screen.render();

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
	}
}
