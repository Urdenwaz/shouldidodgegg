package ibcompsci.urdenwaz.shouldidodge.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.objects.InteractivePanel;
import ibcompsci.urdenwaz.shouldidodge.objects.Toolbar;
import ibcompsci.urdenwaz.shouldidodge.resources.FontGenerator;
import ibcompsci.urdenwaz.shouldidodge.resources.ImageModifier;

public class MainUI extends JFrame {

	private ApiClient client;

    private JPanel mainUIPanel;

    // Width should be AT LEAST 400. Recommended is around 700 due to 16 character username length.
    private final int SCREEN_WIDTH = 700;
    private final int SCREEN_HEIGHT = 750;

    private Rectangle[] mainFields;
    private JPanel[] mainPanels;

    private int mouseX;
    private int mouseY;

    public MainUI(String name, ApiClient client) {
        super(name);
        this.client = client;
        init();
    }

    public void init() {
//    	setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(100, 100, SCREEN_WIDTH, SCREEN_HEIGHT));
		setUndecorated(true);

		setBackground(new java.awt.Color(255, 255, 255, 255));

		mainUIPanel = new JPanel();

		setContentPane(mainUIPanel);
		mainUIPanel.setLayout(null);
		mainUIPanel.setBackground(Color.WHITE);
		mainUIPanel.setBorder(new javax.swing.border.LineBorder(Color.BLACK));

		/// dimensions for title, player, and button fields indexed at 0,1,2 respectively.
		mainFields = getMainFields();
		mainPanels = getMainPanels();


		mainPanels[0].addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				framePress(evt);
			}
		});
		mainPanels[0].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			@Override
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				frameDrag(evt);
			}
		});



    }

    private Rectangle[] getMainFields() {
    	int tbHeight = 25; // toolbar height
    	return new Rectangle[] {
    			new Rectangle(0, 0, SCREEN_WIDTH, tbHeight),
    			new Rectangle(0, tbHeight, SCREEN_WIDTH, (SCREEN_HEIGHT-tbHeight)*1/10), // graphics start from top left.
    			new Rectangle(0, SCREEN_HEIGHT*1/10+tbHeight-2, SCREEN_WIDTH, (SCREEN_HEIGHT-tbHeight)*9/10+1)
    		};
    }

    private JPanel[] getMainPanels() {
    	JPanel[] ret = new JPanel[mainFields.length];

    	JPanel r;

    	// toolbar panel
    	Toolbar tool = new Toolbar(this);

    	ret[0] = tool;

    	// title panel

    	r = new JPanel();
    	ret[1] = r;
    	r.setLayout(null);

    	// Interactive panel

    	InteractivePanel ip = new InteractivePanel(client) {
    		@Override
    		public void getBack() {
    			tool.back();
    		}
    	};
    	tool.addBack(new java.awt.event.MouseAdapter() {
    		@Override
    		public void mousePressed(java.awt.event.MouseEvent evt) {
    			ip.swapPanels();
    			tool.back();
     		}
    	});
    	ret[2] = ip;
    	
    	// standardized formatting of panels
		for (int i = 0; i < mainFields.length; i++) {
			ret[i].setBounds(mainFields[i]);
			ret[i].setOpaque(true);
			mainUIPanel.add(ret[i]);
		}


		// Extra aesthetics

    	JLabel label = new JLabel();
    	label.setText("ShouldIDodge.GG");
		label.setFont(FontGenerator.$$$getFont$$$("Comic Sans MS", -1, 48, label.getFont()));
		label.setForeground(java.awt.Color.WHITE);
		label.setBounds(0, 0, r.getWidth(), r.getHeight());
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(false);
		r.add(label);

		label = new JLabel();
		label.setBounds(0, 0, r.getWidth(), r.getHeight());
		try {
			Image titleImage = ImageIO.read(new File("src/main/java/ibcompsci/urdenwaz/shouldidodge/resources/League-Themed Bar.jpg"));
			label.setIcon(
					new ImageIcon(ImageModifier.resizeImage(titleImage, ret[1].getWidth(), ret[1].getHeight())));
		} catch (IOException e) {}
		r.add(label);

		return ret;
    }

    public void frameDrag(java.awt.event.MouseEvent evt) {
    	int x = evt.getXOnScreen();
    	int y = evt.getYOnScreen();

    	this.setLocation(x - mouseX, y - mouseY);
    }

    public void framePress(java.awt.event.MouseEvent evt) {
    	mouseX = evt.getX();
    	mouseY = evt.getY();
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainUIPanel;
    }

}
