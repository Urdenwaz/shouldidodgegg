package ibcompsci.urdenwaz.shouldidodge.ui;

import ibcompsci.urdenwaz.shouldidodge.engine.ApiClient;
import ibcompsci.urdenwaz.shouldidodge.engine.ApiException;
import ibcompsci.urdenwaz.shouldidodge.engine.Summoner;
import ibcompsci.urdenwaz.shouldidodge.engine.LobbyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class MainUI extends JFrame implements ActionListener {

    private JButton dodgeButton;
    private JLabel appNameLabel;
    private JPanel mainUIPanel;
    private JSplitPane topSplitPane;
    private JLabel topLabel;
    private JTextField topSummonerField;
    private JSplitPane topChampPane;
    private JLabel topChampLabel;
    private JTextField topChampField;
    private JSplitPane jungSplitPane;
    private JLabel jungLabel;
    private JTextField jungSummonerField;
    private JSplitPane jungleChampPane;
    private JLabel jungChampLabel;
    private JTextField jungChampField;
    private JSplitPane midPane;
    private JTextField midSummonerField;
    private JSplitPane midChampPane;
    private JTextField midChampField;
    private JSplitPane botPane;
    private JLabel midLabel;
    private JLabel midChampLabel;
    private JLabel botLabel;
    private JTextField botSummonerField;
    private JSplitPane botChampPane;
    private JLabel botChampLabel;
    private JTextField botChampField;
    private JSplitPane supPane;
    private JSplitPane supChampPane;
    private JLabel supLabel;
    private JLabel supChampLabel;
    private JTextField supSummonerField;
    private JTextField supChampField;

    private LobbyInput li;

    public MainUI(String name, ApiClient client) throws ApiException, IOException {
        super(name);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainUIPanel);
        this.setSize(new Dimension(1000, 1000));
        this.pack();

        li = new LobbyInput(client);

        dodgeButton.addActionListener(this);
    }

    // Order:
    // [0] = Top
    // [1] = Jungle
    // [2] = Middle
    // [3] = Bottom
    // [4] = Support
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Summoner[] summonerLobby = {li.getChampion(topSummonerField.getText()),
                    li.getChampion(jungSummonerField.getText()),
                    li.getChampion(midSummonerField.getText()),
                    li.getChampion(botSummonerField.getText()),
                    li.getChampion(supSummonerField.getText())};

            System.out.println(Arrays.toString(summonerLobby));
            int dodgeCount = 0;
            for (Summoner c : summonerLobby) {
                if (c.shouldIdodge()) {
                    dodgeCount++;
                }
            }

            if (dodgeCount < 3) {
                JOptionPane.showMessageDialog(null, "Dodge this game.");
            } else {
                JOptionPane.showMessageDialog(null, "Coast is clear, you're free to go.");
            }
        } catch (IOException | ApiException ex) {
            ex.printStackTrace();
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainUIPanel = new JPanel();
        mainUIPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
        appNameLabel = new JLabel();
        Font appNameLabelFont = this.$$$getFont$$$("Comic Sans MS", -1, 48, appNameLabel.getFont());
        if (appNameLabelFont != null) appNameLabel.setFont(appNameLabelFont);
        appNameLabel.setText("ShouldIDodge.GG");
        mainUIPanel.add(appNameLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dodgeButton = new JButton();
        dodgeButton.setText("Should I Dodge?");
        mainUIPanel.add(dodgeButton, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        topSplitPane = new JSplitPane();
        topSplitPane.setEnabled(false);
        mainUIPanel.add(topSplitPane, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(100, 100), null, 0, false));
        topLabel = new JLabel();
        topLabel.setHorizontalAlignment(0);
        topLabel.setText("Top Summoner");
        topSplitPane.setLeftComponent(topLabel);
        topSummonerField = new JTextField();
        topSummonerField.setHorizontalAlignment(2);
        topSplitPane.setRightComponent(topSummonerField);
        topChampPane = new JSplitPane();
        mainUIPanel.add(topChampPane, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        topChampLabel = new JLabel();
        topChampLabel.setHorizontalAlignment(0);
        topChampLabel.setHorizontalTextPosition(10);
        topChampLabel.setText("Top Summoner");
        topChampPane.setLeftComponent(topChampLabel);
        topChampField = new JTextField();
        topChampPane.setRightComponent(topChampField);
        jungSplitPane = new JSplitPane();
        mainUIPanel.add(jungSplitPane, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        jungLabel = new JLabel();
        jungLabel.setHorizontalAlignment(0);
        jungLabel.setText("Jungle Summoner");
        jungSplitPane.setLeftComponent(jungLabel);
        jungSummonerField = new JTextField();
        jungSplitPane.setRightComponent(jungSummonerField);
        jungleChampPane = new JSplitPane();
        mainUIPanel.add(jungleChampPane, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        jungChampLabel = new JLabel();
        jungChampLabel.setHorizontalAlignment(0);
        jungChampLabel.setText("Jungle Summoner");
        jungleChampPane.setLeftComponent(jungChampLabel);
        jungChampField = new JTextField();
        jungleChampPane.setRightComponent(jungChampField);
        midPane = new JSplitPane();
        mainUIPanel.add(midPane, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        midLabel = new JLabel();
        midLabel.setHorizontalAlignment(0);
        midLabel.setText("Middle Summoner");
        midPane.setLeftComponent(midLabel);
        midSummonerField = new JTextField();
        midPane.setRightComponent(midSummonerField);
        midChampPane = new JSplitPane();
        mainUIPanel.add(midChampPane, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        midChampLabel = new JLabel();
        midChampLabel.setHorizontalAlignment(0);
        midChampLabel.setText("Middle Summoner");
        midChampPane.setLeftComponent(midChampLabel);
        midChampField = new JTextField();
        midChampPane.setRightComponent(midChampField);
        botPane = new JSplitPane();
        mainUIPanel.add(botPane, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        botLabel = new JLabel();
        botLabel.setHorizontalAlignment(0);
        botLabel.setText("Bottom Summoner");
        botPane.setLeftComponent(botLabel);
        botSummonerField = new JTextField();
        botPane.setRightComponent(botSummonerField);
        botChampPane = new JSplitPane();
        mainUIPanel.add(botChampPane, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        botChampLabel = new JLabel();
        botChampLabel.setHorizontalAlignment(0);
        botChampLabel.setText("Bottom Summoner");
        botChampPane.setLeftComponent(botChampLabel);
        botChampField = new JTextField();
        botChampField.setText("");
        botChampPane.setRightComponent(botChampField);
        supPane = new JSplitPane();
        mainUIPanel.add(supPane, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        supLabel = new JLabel();
        supLabel.setHorizontalAlignment(0);
        supLabel.setText("Support Summoner");
        supPane.setLeftComponent(supLabel);
        supSummonerField = new JTextField();
        supPane.setRightComponent(supSummonerField);
        supChampPane = new JSplitPane();
        mainUIPanel.add(supChampPane, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        supChampLabel = new JLabel();
        supChampLabel.setHorizontalAlignment(0);
        supChampLabel.setText("Support Summoner");
        supChampPane.setLeftComponent(supChampLabel);
        supChampField = new JTextField();
        supChampPane.setRightComponent(supChampField);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainUIPanel;
    }

}
