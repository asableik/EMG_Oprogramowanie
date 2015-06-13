package data_acqusition;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import Visualisation.ChannelsScrollPane;

public class Channels implements Observer{
	
	Map<Integer, Channel> channelsList;
	Map<Integer, Integer> channelsCount;   //min value , max value
	private PortReader portReader;
	private ChannelsScrollPane channelsScrollPane;
	int ch1Count;
	
	public Channels(ChannelsScrollPane csp){
		this.channelsScrollPane = csp;
		channelsList =  new HashMap<Integer, Channel>();
		channelsCount = new HashMap<Integer, Integer>();
		portReader = new PortReader();
		
	}
	public Channel getChannel(int i){
		if(channelsList.containsKey(i)) return channelsList.get(i);
		else return null;
	
	}
	public void createChannel(int channelNumber){
		DataFileBuffer dfb = new DataFileBuffer(channelNumber);
		channelsList.put(channelNumber, new Channel(dfb));
		portReader.addChannelQueue(channelNumber, dfb);
		dfb.addObserver(this);
		//channelsList.get(channelNumber).populateDFB();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		int [] args = (int[]) arg;
		if(channelsScrollPane.getChannelScrollPane(args[0])!=null){
		channelsScrollPane.getChannelScrollPane(args[0]).addPoint(channelsList.get(args[0]).getDataFileBuffer().getValues(20));
			
		}
			
		
		channelsCount.put(args[0], args[1]);
		///System.out.println("DataFileBuffer " + arg);
	}

}
