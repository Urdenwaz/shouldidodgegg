package ibcompsci.urdenwaz.shouldidodge.objects;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import ibcompsci.urdenwaz.shouldidodge.resources.FontGenerator;

public class TextListener extends JPanel {
	
	private final String start = "Click anywhere to Begin";
	private final String command = "Ctrl + a and Ctrl + v the lobby chat into this screen"; 
	private final String confirm = "Confirmed. Please wait momentarily. . .";
	
	private Font font;
	private FontMetrics fm;
	
	private JTextPane jtp;
	private JTextArea jta;
	
	private JPanel listener;
	
	public TextListener(Rectangle r) {
		super.setBounds(0, 0, r.width, r.height);
		setOpaque(false);
		setBackground(new java.awt.Color(0, 10, 80, 255));
		setLayout(null);
		
		font = FontGenerator.$$$getFont$$$("Comic Sans MS", -1, 50, getFont());
		fm = getFontMetrics(font);
		
		
		// Mouse Listener Panel
		
		listener = new JPanel();
		listener.setBounds(0, 0, r.width, r.height);
		listener.setOpaque(false);
		
		listener.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				customListener(listener, r);
			}
		});
		
		add(listener);
		
		
		// Text Areas for Instructions
		jtp = new JTextPane();
		jtp.setEditable(false);
		jtp.setFont(font);
		jtp.setOpaque(false);
		
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
		jtp.setParagraphAttributes(attribs, false);
		
		add(jtp);
		
		updateText(start);
		
	}
	
	public void updateText(String s) {
		Rectangle r = getBounds();
		jtp.setText(s);
		double width = fm.stringWidth(s);
		int lines = (int) Math.ceil(width / r.width);
		jtp.setBounds(0, (r.height - fm.getHeight()*lines)/2, r.width, fm.getHeight()*lines);
		jtp.setForeground(java.awt.Color.WHITE);
		
	}
	
	public void customListener(JPanel listener, Rectangle r) {
//		remove(listener);
		
		jta = new JTextArea();
		jta.setBounds(10, 10, r.width-20, r.height-20);
		jta.setEditable(true);
		jta.setOpaque(false);
		jta.setCaretColor(java.awt.Color.WHITE);
		jta.setForeground(java.awt.Color.WHITE);
		jta.setFont(jtp.getFont());
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		
		jta.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent evt) {
				
				java.awt.Color transparent = new java.awt.Color(0, 0, 0, 0);
				
				if (evt.getDot() == 0) {
					jtp.setVisible(false);
				}
				
				if (evt.getDot() + evt.getMark() > 40) {
					
					jtp.setVisible(true);
					jta.update(jta.getGraphics());
					
					try {Thread.sleep(1000);} catch (InterruptedException e) {};
					
					updateText(confirm);
					jta.setVisible(false);
					
					triggerSwap(jta.getText());
				}
			}
		});
		
		add(jta, 0);
		
		updateText(command);
		
	}
	
	// function inherited from callee object at instantiation point
	public void triggerSwap(String s) {}
	
	public void superUpdate() {}

}
