package Visualisation;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import data_acqusition.PlotType;
@SuppressWarnings("serial")
public class ChannelScrollPane extends BoxScrollPane{
	
	Map<PlotType,PlotPanel> panels = new HashMap<PlotType,PlotPanel>();
	Map<PlotType,AxisPanel> axisPanels = new HashMap<PlotType,AxisPanel>();
	JLabel channelLabel;
	
	public ChannelScrollPane(int channelNumber){
		channelLabel = new JLabel("CHANNEL "+channelNumber);
		containerPanel.add(channelLabel);
	}
	
	private int iter = 0; 
	
	
	public void addPoint(int[][] samples){
			
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
