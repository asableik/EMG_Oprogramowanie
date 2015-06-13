package Visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlotPanel extends JPanel{
	public PlotPanel(){

	}
	Integer [][] points = {{0}};
	
	public void paintSamples(Integer[][] sampl){
		this.points = sampl;
		repaint();
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//g.setColor(Color.black);
		//g.fillRect(0, 0,this.getSize().width,this.getSize().height);
		//rysuje t³o
		g.setColor(Color.white);
		g.fillRect(1, 1,this.getSize().width-2,this.getSize().height-2);
		//rysuje wykres
		g.setColor(Color.black);
		g.drawRect(0, 0	, this.getSize().width, this.getSize().height);
		
		for(int i = 0;i<points[0].length-1;i++){
		g.drawLine(points[0][i], points[1][i]+100, points[0][i+1],points[1][i+1]+100);
		}
	}

}
