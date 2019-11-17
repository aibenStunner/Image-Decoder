import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private String state;
    private ImageIcon apple, orange, tomato;
    private JLabel imageLabel;

    ImagePanel() {
        // Load images
        apple = new ImageIcon("apple.png");
        orange = new ImageIcon("orange.png");
        tomato = new ImageIcon("tomato.png");

        setBackground(Color.WHITE);

        state = "a";
        imageLabel = new JLabel(apple);
        add(imageLabel);
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        // Set image based of selection input by user
        if (state.equals("a"))
            imageLabel.setIcon(apple);
        else if (state.equals("o"))
            imageLabel.setIcon(orange);
        else if (state.equals("t"))
            imageLabel.setIcon(tomato);
    }

    public void setState(String state) {
        this.state = state;
    }
}
