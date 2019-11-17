import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelControl extends JPanel {
    private ImagePanel imagePanel;
    private JButton appleBtn, orangeBtn, tomatoBtn;

    PanelControl(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;

        // Initialize buttons
        appleBtn = new JButton("Apple");
        appleBtn.setEnabled(false);
        appleBtn.setMnemonic('a');
        appleBtn.setToolTipText("Show Apple");
        appleBtn.addActionListener(new AppleListener());

        orangeBtn = new JButton("Orange");
        orangeBtn.setEnabled(true);
        orangeBtn.setMnemonic('o');
        orangeBtn.setToolTipText("Show Orange");
        orangeBtn.addActionListener(new OrangeListener());

        tomatoBtn = new JButton("Tomato");
        tomatoBtn.setEnabled(true);
        tomatoBtn.setMnemonic('t');
        tomatoBtn.setToolTipText("Show Tomato");
        tomatoBtn.addActionListener(new TomatoListener());

        setBackground(Color.WHITE);
        // Add the buttons to the main window
        add(appleBtn);
        add(orangeBtn);
        add(tomatoBtn);
    }

    ////// Action listeners
    private class AppleListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            imagePanel.setState("a");
            appleBtn.setEnabled(false);
            orangeBtn.setEnabled(true);
            tomatoBtn.setEnabled(true);
            imagePanel.repaint();
        }
    }

    private class OrangeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            imagePanel.setState("o");
            appleBtn.setEnabled(true);
            orangeBtn.setEnabled(false);
            tomatoBtn.setEnabled(true);
            imagePanel.repaint();
        }
    }

    private class TomatoListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            imagePanel.setState("t");
            appleBtn.setEnabled(true);
            orangeBtn.setEnabled(true);
            tomatoBtn.setEnabled(false);
            imagePanel.repaint();
        }
    }

}
