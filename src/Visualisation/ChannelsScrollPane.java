package Visualisation;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import data_acqusition.PlotType;

@SuppressWarnings("serial")
public class ChannelsScrollPane extends BoxScrollPane{

	Map<Integer,ChannelScrollPane> channelsContainer = new HashMap<Integer,ChannelScrollPane>();
	
	public ChannelScrollPane getChannelScrollPane(int number){
		
		if(channelsContainer.containsKey(number)) return channelsContainer.get(number);
		else return null;
		
	}
	public void addChannelScrollPane(int channelNumber) {
		ChannelScrollPane csp = new ChannelScrollPane(channelNumber);
		csp.setMaximumSize(new Dimension(100000,300));
		channelsContainer.put(channelNumber, csp); 
		containerPanel.add(csp);		
	}


}
