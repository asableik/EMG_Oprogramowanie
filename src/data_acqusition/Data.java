package data_acqusition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Data {
	private int [] times;
	private short [] values;
	boolean loaded = false;
	private int len2;
	
	File             file;
    FileChannel      channel;
    MappedByteBuffer buffer;
    FileInputStream fin;
    String fileName;
    ExecutorService exec;
    long tic2;
	public Data(int tempNumber,long tic){
		this.tic2 = tic;
		exec = Executors.newFixedThreadPool(Thread.activeCount());
		int howmuch = 612440;
		times = new int [howmuch];
		values = new short [howmuch];
		fileName = "tmp/temp"+tempNumber+".dat";
	    file    = new File(fileName);
	    int len;
	    try {
			 fin = new FileInputStream(file);
			 channel = fin.getChannel();
			 len = (int)file.length();
			 buffer  = channel.map(MapMode.READ_ONLY, 0, len);
			 len2 = len/6;
			exec.execute(new Runnable(){
				 long tic = System.currentTimeMillis();
				@Override
				public void run() {
				
				for(int i = 0;i<len2;i++){
				times[i] = buffer.getInt();
				values[i] = buffer.getShort();
			
				}
				loaded = true;
				System.out.println(fileName+" tic "+(System.currentTimeMillis()-tic)+" "+loaded);
				System.out.println(fileName+" global tic "+(System.currentTimeMillis()-tic2));
				}
				 
			 });
			 
			 buffer.clear();
			 channel.close();
			 fin.close();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public int[] getTimes() {
		return times;
	}

	public short[] getValues() {
		return values;
	}
}
