package tomasulo;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {
	Translater t = new Translater();
	
	public GUI() {
		JFrame frame = new JFrame("Tomasulo");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(300,300);
	       
	       JPanel panelx = new JPanel();
	       BoxLayout boxlayout = new BoxLayout(panelx, BoxLayout.X_AXIS);
	       panelx.setLayout(boxlayout);
	       
	       JPanel panell = new JPanel();
	       BoxLayout boxlayoutl = new BoxLayout(panell, BoxLayout.Y_AXIS);
	       panell.setLayout(boxlayoutl);
	       
	       JPanel panelr = new JPanel();
	       BoxLayout boxlayoutr = new BoxLayout(panelr, BoxLayout.Y_AXIS);
	       panelr.setLayout(boxlayoutr);
	       
	       JTextArea code = new JTextArea(5, 20);
	       JScrollPane areaScrollPane = new JScrollPane(code);
	       areaScrollPane.setVerticalScrollBarPolicy(
	                       JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       areaScrollPane.setPreferredSize(new Dimension(400, 600));
	       
	       JTextArea out = new JTextArea(5, 20);
	       JScrollPane outscroll = new JScrollPane(out);
	       areaScrollPane.setVerticalScrollBarPolicy(
	                       JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	       areaScrollPane.setPreferredSize(new Dimension(400, 600));
	       
	       JButton trans = new JButton("Translate");
	       JLabel outputLabel = new JLabel("output");
	       JLabel assembly = new JLabel("Assembly");
	       
	       trans.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String [] sa = t.translate(code.getText().trim());
				String so = "";
				for(String s:sa) {
					so = so + s + "\n";
				}
				out.setText(so);
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	   
	       });
	       
	       panell.add(assembly);
	       panell.add(areaScrollPane);
	       panell.add(trans);
	       
	       panelr.add(outputLabel);
	       panelr.add(outscroll);
	       
	       panelx.add(panell);
	       panelx.add(panelr);
	       
	       frame.add(panelx);
	       frame.pack();
	       
	       
	       frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unused")
		GUI gui = new GUI();
		

	}

}
