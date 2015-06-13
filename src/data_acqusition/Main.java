package data_acqusition;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.SortedSet;

import javax.swing.SwingUtilities;

import Visualisation.MainFrame;

public class Main {
	
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
	new Channels().createChannel(1);
	SwingUtilities.invokeLater(new Runnable(){

		@Override
		public void run() {
			new MainFrame().setVisible(true);
		}
		
	});
	System.out.println("mogeeeeee");

}
}
