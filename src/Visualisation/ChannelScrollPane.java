package Visualisation;
import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import data_acqusition.PlotType;
@SuppressWarnings("serial")
public class ChannelScrollPane extends BoxScrollPane{
	
	Map<PlotType,PlotPanel> panels = new HashMap<PlotType,PlotPanel>();
	Map<PlotType,AxisPanel> axisPanels = new HashMap<PlotType,AxisPanel>();
	JLabel channelLabel;
	Deque<Integer[]> samplesDeque = new ArrayDeque<Integer[]>();
	int samplesDequeSize = 0;
	public ChannelScrollPane(int channelNumber){
		channelLabel = new JLabel("CHANNEL "+channelNumber);
		containerPanel.add(channelLabel);
	}
	
	private int iter = 0; 
	
	
	public void addPoint(int[][] samples){
		Integer [][] sampl = new Integer[2][samplesDequeSize+samples[0].length];
		//System.out.println("samples len: "+samples[0].length);
			for(int i = 0;i<samples[0].length;i++){
			
				if(samplesDequeSize==980){
				
					iter = 0;
					samplesDeque.clear();
					samplesDequeSize = 0;
					//samplesDeque.addLast(new Integer[]{samples[0][i],samples[1][i]});
					//samplesDeque.addLast(new Integer[]{iter,samples[1][i]});
				}
					//samplesDeque.addLast(new Integer[]{samples[0][i],samples[1][i]});
					samplesDeque.addLast(new Integer[]{iter,samples[1][i]});
					samplesDequeSize++;
				
				iter++;
				 
				 
			}
			samplesDeque.descendingIterator();
			int j = 0;
			for(Integer[] in : samplesDeque){
				sampl[0][j] = in[0];
				sampl[1][j++] = in[1];
			}
			//sampl = samplesDeque.toArray(new Integer[samplesDequeSize][2]);
			
			for(Map.Entry<PlotType, PlotPanel> entry : panels.entrySet()){
				entry.getValue().paintSamples(sampl);
			}
	}
	
	public void addPlot(PlotType type) {
		if(panels.containsKey(type)==false){
		AxisPanel ap = new AxisPanel(type);
		PlotPanel pp = new PlotPanel();
		
		ap.setPreferredSize(new Dimension(1120,240));
		ap.setMaximumSize(ap.getPreferredSize());
		
		//pp.setPreferredSize(new Dimension(1000,200));
		//pp.setMaximumSize(pp.getPreferredSize());
		
		ap.add(pp,"w 1000:1000:1000,h 200:201:201,gapleft 60,gapbottom 30,gaptop 10");

		panels.put(PlotType.RAW, pp);
		axisPanels.put(PlotType.RAW, ap);

		
		
		containerPanel.add(ap);
		}
	}

}
