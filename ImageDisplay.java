import javax.swing.*;
import java.awt.*;

public class ImageDisplay {
    ImageDisplay() {
        // Construct the main window interface
        JFrame frame = new JFrame("Image Decoder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FileAccessControl FAC = new FileAccessControl();

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(400, 0)));
        panel.add(FAC);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
