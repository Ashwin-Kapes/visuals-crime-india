package com.vci;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

	public class Main extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			java.awt.Polygon p = new java.awt.Polygon();
			/*for (int i = 0; i < 5; i++)
				p.addPoint((int) (100 + 50 * Math.cos(i * 2 * Math.PI / 5)),
						(int) (100 + 50 * Math.sin(i * 2 * Math.PI / 5)));*/
			
			XMLReader reader = new XMLReader();
			reader.parseXmlFile();
			reader.parseDocument();
			reader.getPolygon();
			
			g.drawPolygon(p);
		}

		public static void main(String[] args) {
			JFrame frame = new JFrame();
			frame.setTitle("Polygon");
			frame.setSize(350, 250);
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			Container contentPane = frame.getContentPane();
			contentPane.add(new Main());
			frame.setVisible(true);
		}
	}

