package data_acqusition;

import java.util.ArrayDeque;
import java.util.Deque;

public class Channel {
private DataFileBuffer dataFileBuffer;
//private SampleSource sampleSource;
//private VisualisationPanel visualisationPanel;

private Deque<Data> dataDeque;
private int channelStartTime = 0;
int maxi = 100000000;
public Channel(DataFileBuffer dfb){
	this.dataFileBuffer = dfb;
	dataDeque = new ArrayDeque<Data>();
	long globaltic = System.currentTimeMillis();

	//dataDeque.addFirst(new Data(0,globaltic));
}

public int getChannelStartTime() {
	return channelStartTime;
}

public DataFileBuffer getDataFileBuffer(){
	return dataFileBuffer;
	
}

public synchronized void populateDFB(){
	Thread t1 = new Thread(new Runnable(){

		@Override
		public void run() {
			for(int i = 0;i<maxi;i++){
				if(i<maxi/2){
					dataFileBuffer.addBufferData(new int[]{i,(int)(Math.random()*100)});
				} else 
					dataFileBuffer.addBufferData(new int[]{i+1000,(int)(Math.random()*100)});
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
		
	});
	
	t1.start();
	
}
}
