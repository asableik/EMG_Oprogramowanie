package Visualisation;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public abstract class BoxScrollPane extends JScrollPane{
	JPanel containerPanel;
	public BoxScrollPane(){
		containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel,BoxLayout.Y_AXIS));
		this.setViewportView(containerPanel);
	}
	

}
