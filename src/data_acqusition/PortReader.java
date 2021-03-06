package data_acqusition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class PortReader {
	
	
		SerialPort[] 					portList;				// lista port�w
		Map<Integer,DataFileBuffer> 	portQueuesList;
		Queue <Integer[]>				sampleQueue;			// kolejka FIFO sampli
		byte[]							newData;
		int 							len;
		ExecutorService 				pool;					// newSingleThreadExecutor - w�tki wykonywane sekwencyjnie
																// zapewnia kolejno�� rosn�c� wstawie�
		int timestamp = 0;
	  public void addChannelQueue(int channelNumber, DataFileBuffer portBuffer){
		  portQueuesList.put(channelNumber,portBuffer);
	  }
	  
	  public PortReader(){
		 pool = Executors.newSingleThreadExecutor();
		 portQueuesList = new HashMap<Integer,DataFileBuffer>(); 
		 portList = SerialPort.getCommPorts();
		 for(final SerialPort sp:portList){
			 System.out.println(sp.getSystemPortName());
			 if(sp.getSystemPortName().equals("COM2")){
				 try{
				 System.out.println(sp.openPort());
				// sp.setComPortParameters(9600, 8, 1, 0); //(baudrate,databits,stopbits,parity)
				 sp.addDataListener(new SerialPortDataListener(){

					@Override
					public int getListeningEvents() {
						return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
					}

					@Override
					public void serialEvent(SerialPortEvent event) {
						if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;
					      
					      newData = new byte[sp.bytesAvailable()];
					      len = newData.length;
					      System.out.println(len);
					      int numRead = sp.readBytes(newData, len);
					      int[]values = new int[len];
					      if(len>=6){
				
					    	 pool.submit(new Runnable(){

								@Override
									
							    	  public void run(){
									 	ByteBuffer buf = ByteBuffer.wrap(newData);
									 		
							       	      for(int i = 0;i<=len/6;i++){
							       	    	  int timestamp = buf.getInt();
										      
										      byte firstByte = buf.get();
										      byte secByte = buf.get();
										      
										      int adcNumber = firstByte>>4;
										      ByteBuffer bb = ByteBuffer.allocate(2);
										      firstByte = (byte) ((firstByte)& 0xF);
										      bb.put(firstByte);
										      bb.put(secByte);					      
										      int sampleValue = bb.getShort(0);
							
										      if(portQueuesList.containsKey(adcNumber)){
										    	  portQueuesList.get(adcNumber).addBufferData(new int[]{timestamp,sampleValue});
										      }
										      System.out.println("timestamp: "+timestamp+" adc number: "+adcNumber+" sample Value: "+sampleValue);
							       	      }
								}
					    		 
					    	 }) ;
					    	  
					      }
					}
				 });
				// sp.closePort();
				 } catch(Exception e){
					 System.out.println(e.getStackTrace());
				 }
			 }
		 }
	  }
}
