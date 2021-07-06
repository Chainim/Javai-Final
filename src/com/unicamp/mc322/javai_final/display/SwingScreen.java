package com.unicamp.mc322.javai_final.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class SwingScreen {
	public static JButton buildCard(ActionListener l) {
		JButton card = new JButton();
		card.setMargin(new Insets(0, 0, 0, 0));
		card.setBackground(Color.LIGHT_GRAY);
		
		card.setPreferredSize(new Dimension(60, 100));
		card.setSize(60, 100);
		
		card.addActionListener(l);
		
		SpringLayout s = new SpringLayout();
		card.setLayout(s);
		
		JLabel costLabel = new JLabel("C");
		JLabel attackLabel = new JLabel("A");
		JLabel healthLabel = new JLabel("D");
		
		JLabel nameLabel = new JLabel("Name");
		//System.out.println(nameLabel.getFont().getName());
		//nameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		
		s.putConstraint(SpringLayout.WEST, costLabel, 1, SpringLayout.WEST, card);
		s.putConstraint(SpringLayout.NORTH, costLabel, 0, SpringLayout.NORTH, card);
		
		s.putConstraint(SpringLayout.WEST, attackLabel, 1, SpringLayout.WEST, card);
		s.putConstraint(SpringLayout.SOUTH, attackLabel, 0, SpringLayout.SOUTH, card);
		
		s.putConstraint(SpringLayout.EAST, healthLabel, -1, SpringLayout.EAST, card);
		s.putConstraint(SpringLayout.SOUTH, healthLabel, 0, SpringLayout.SOUTH, card);
		
		s.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabel, 0, SpringLayout.HORIZONTAL_CENTER, card);
		s.putConstraint(SpringLayout.VERTICAL_CENTER, nameLabel, 0, SpringLayout.VERTICAL_CENTER, card);
		
		card.add(costLabel);
		card.add(attackLabel);
		card.add(healthLabel);
		card.add(nameLabel);
		
		return card;
	}
}
