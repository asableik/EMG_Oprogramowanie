package data_acqusition;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Channels implements Observer{
	
	Map<Integer, Channel> channelsList;
	Map<Integer, Integer> channelsCount;   //min value , max value
	PortReader portReader;
	int ch1Count;
	
	public Channels(){
		channelsList =  new HashMap<Integer, Channel>();
		channelsCount = new HashMap<Integer, Integer>();
		portReader = new PortReader();
		
	}
	
	public void createChannel(int channelNumber){
		DataFileBuffer dfb = new DataFileBuffer(channelNumber);
		channelsList.put(channelNumber, new Channel(dfb));
		portReader.addChannelQueue(channelNumber, dfb);
		dfb.addObserver(this);
		channelsList.get(channelNumber).populateDFB();
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		int [] args = (int[]) arg;
		channelsCount.put(args[0], args[1]);
		///System.out.println("DataFileBuffer " + arg);
	}

}
