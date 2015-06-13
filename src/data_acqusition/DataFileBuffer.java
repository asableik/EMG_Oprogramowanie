package data_acqusition;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataFileBuffer extends Observable{
	BufferedWriter writer;												// Writer odpowiadaj¹cy za zapis do pliku
	private int bufSize = 3*1024; 										// bufor podzielny przez 6 zapewnia ze zawsze wpisane sa cale linie
																		// 512*6 tj 512 linijek w jednym zapisie, 3072 BAJTY
																		// ilosc linijek powinna byc wielokrotnoscia 512
	 																	// np 512 * 1200 = 612440 linii
																		// 612440
	private int maxSize = 612440;  										// maksymalny rozmiar bufora
	// liczba danych w buforze (x,y)
	private int [] times = new int [maxSize];
	private short [] values = new short [maxSize];
	private DataOutputStream os;
	private int fileBufferi = 0;										// zmienna reprezentuj¹ca zape³nienie bufora
	private int fileNumber = 0;											// numer kolejnoœci pliku
	
	private int firstSample = 0;										// wartoœæ pierwszej próbki w pliku
	private int lastSample = 0;											// wartoœæ ostatniej próbki w pliku

	private int linesPexPix = 20;										// 1 pix - 20 ms
	
	
	private Map<Integer,Integer[]> fileMinMax;
	private int channelNumber = 0;
	private String path;
	private Executor exec;
	public int getChannelNumber(){
		return channelNumber;
	}
	public DataFileBuffer(int channelNumber){
		exec = Executors.newSingleThreadExecutor();
		this.channelNumber = channelNumber;
		fileMinMax = new HashMap<Integer,Integer[]>();
		/*Otwieranie pliku na pierwsze maxSize danych*/
		try{
			path = "tmp/"+channelNumber;
			new File(path).mkdirs();
			os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path+"/temp0.dat"),bufSize));
		} catch (IOException e)
		  {
			e.printStackTrace();
		  }
		
	};
	
	public void addBufferData(final int[] data){
		exec.execute((new Runnable(){
	
			@Override
			public void run() {
				/* Przy wk³adaniu pierwszej próbki wpisujemy j¹ jako pierwsz¹  */
				if(fileBufferi==0){
					firstSample = data[0]; 
				}
		
					/* Je¿eli bufor osi¹gn¹³ rozmiar maxSize */
					if(fileBufferi == maxSize){
						changeFile();
					}
					times[fileBufferi] = data[0];
					if(data[0]>	lastSample) lastSample = data[0];
					values[fileBufferi] = (short)(data[1]);
					try {
						os.writeInt(data[0]);
						os.writeShort((short)(data[1]));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					if(fileBufferi!=0 && fileBufferi%linesPexPix == 0){
					setChanged();
					notifyObservers(new int[]{channelNumber,lastSample});	
					}
					
					
					
					fileBufferi++;	
					
			}
		}));

		
	}
	
	private void changeFile(){
		times = new int [maxSize];
		values = new short [maxSize];
		fileMinMax.put(fileNumber, new Integer[]{firstSample,lastSample});
		try {
			os.close();
			fileNumber++;
			os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path+"temp"+fileNumber+".dat"),bufSize));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileBufferi = 0;	
	}
	
}
