package data_acqusition;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.SortedSet;

import javax.swing.SwingUtilities;

import Visualisation.MainFrame;

public class Main {
	static MainFrame mainFrame;
	static Channels channels;
public void writeToFile(SortedSet<Integer[]> subset){
	try{
			@SuppressWarnings("resource")
			Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("filename.txt"), "utf-8"));
			for(Integer[] i:subset){
				writer.write(i[0]+" "+i[1]+"\n");
			}
            } catch( Exception e){
            	
            }
	
}
public static void main(String [] args){
	SwingUtilities.invokeLater(new Runnable(){

		@Override
		public void run() {
			mainFrame = new MainFrame();
			mainFrame.setVisible(true);
		}
		
	});

	while(mainFrame == null || mainFrame.isVisible() == false){
		System.out.println("tutaj");
	}

	channels  = new Channels(mainFrame.getChannelsScrollPane());
	channels.createChannel(1);
	channels.createChannel(2);
	mainFrame.getChannelsScrollPane().addChannelScrollPane(1);
	mainFrame.getChannelsScrollPane().getChannelScrollPane(1).addPlot(PlotType.RAW);
	mainFrame.getChannelsScrollPane().addChannelScrollPane(2);
	mainFrame.getChannelsScrollPane().getChannelScrollPane(2).addPlot(PlotType.RAW);
	System.out.println("tutaj");
	//channels.getChannel(1).populateDFB();
	
	mainFrame.getChannelsScrollPane().revalidate();
	
	System.out.println("mogeeeeee");

}
}
