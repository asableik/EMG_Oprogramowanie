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
		setSize(500,200);
		setTitle("frame");
		setFocusable(true);
		channelsScrollPane = new ChannelsScrollPane();
		channelsScrollPane.addChannelContainer(1);
		channelsScrollPane.addChannelContainer(2);
		channelsScrollPane.getChannelScrollPane(1).addPlot(PlotType.RAW);
		channelsScrollPane.getChannelScrollPane(1).addPlot(PlotType.RAW);
		channelsScrollPane.getChannelScrollPane(2).addPlot(PlotType.RAW);
		this.add(channelsScrollPane);
	
		
//		containerPanel = new JPanel();
//		containerPanel.setLayout(new BoxLayout(containerPanel,BoxLayout.Y_AXIS));
//		
//		
//		mainPanel = new JPanel();
//		mainPanel2 = new JPanel();
//		
//		
//		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
//		
//		JButton buttonAdd = new JButton("Add subPanel");
//        buttonAdd.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mainPanel.add(new SubPanel());
//                myFrame.pack();
//            }
//        });
//        
//        
//	mainPanel2.setLayout(new BoxLayout(mainPanel2,BoxLayout.Y_AXIS));
//		
//		JButton buttonAdd2 = new JButton("Add subPanel2");
//        buttonAdd2.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                mainPanel2.add(new SubPanel());
//                myFrame.pack();
//            }
//        });
//        
//        
//        getContentPane().add(containerPanel);
//        containerPanel.add(buttonAdd);
//        containerPanel.add(buttonAdd2);
//        containerPanel.add(new JSeparator());
//        containerPanel.add(mainPanel);
//        containerPanel.add(new JSeparator());
//        containerPanel.add(mainPanel2);  
	}
//	
//	public void addVisPanel(){
//		containerPanel.add(new JSeparator());
//		containerPanel.add(mainPanel);
//	}
//	
//	
//	
//	private class SubPanel extends JPanel{
//		SubPanel me;
//		
//		public SubPanel(){
//			this.me = this;
//			 JButton myButtonRemoveMe = new JButton("remove me");
//	            myButtonRemoveMe.addActionListener(new ActionListener(){
//
//	                @Override
//	                public void actionPerformed(ActionEvent e) {
//	                    me.getParent().remove(me);
//	                    myFrame.pack();
//	                }
//	            });
//	            add(myButtonRemoveMe);
//		}
		
//	}

}
