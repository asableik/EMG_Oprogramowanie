package Visualisation;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import data_acqusition.PlotType;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	JPanel mainPanel;
	MainFrame myFrame;
	JMenuBar menuBar;
	JMenu mfile,medit,mplot,macquisition;
	JMenu mfile_mnew; //Jmenu nale¿¹ce do mfile;
	JMenu mplot_mnew;
	JMenuItem mfile_miuser,mfile_misubject,mistartacq,mipauseacq,mistopacq,minewplot,miraw,mirectified,mirms,mipowerspectrum;
	 
	private ChannelsScrollPane channelsScrollPane;
	public MainFrame(){
		this.myFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300,300);
		setTitle("frame");
		setFocusable(true);
		channelsScrollPane = new ChannelsScrollPane();
		//channelsScrollPane.addChannelScrollPane(1);
		//channelsScrollPane.addChannelScrollPane(2);
//		channelsScrollPane.getChannelScrollPane(1).addPlot(PlotType.RAW);
//		channelsScrollPane.getChannelScrollPane(1).addPlot(PlotType.RAW);
//		channelsScrollPane.getChannelScrollPane(2).addPlot(PlotType.RAW);
		this.add(channelsScrollPane);
	
	}
	
	public ChannelsScrollPane getChannelsScrollPane(){
		return channelsScrollPane;
	}

}
