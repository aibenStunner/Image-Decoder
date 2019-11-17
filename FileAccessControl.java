import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileAccessControl extends JPanel {
    private JLabel label;
    private JButton openBtn;
    private String filePath;

    FileAccessControl() {
        openBtn = new JButton("Open");

        setBackground(Color.WHITE);
        // Add action listener to the button to capture user response on buttons
        openBtn.addActionListener(new OpenListener());
        add(openBtn);

        label = new JLabel("No file selected...");
        label.setForeground(Color.BLACK);
        add(label);
    }

    private class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String com = evt.getActionCommand();
            if (com.equals("Open")) {
                File f = new File(System.getProperty("user.dir"));
                JFileChooser j = new JFileChooser(f, FileSystemView.getFileSystemView());

                // Restrict the user to select files of all types
                j.setAcceptAllFileFilterUsed(false);

                j.setDialogTitle("Select a .txt file");

                // Only allow files of .txt extension
                FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
                j.addChoosableFileFilter(restrict);

                // Invoke the showsOpenDialog function to show the open dialog
                int r = j.showOpenDialog(null);

                // If the user selects a file
                if (r == JFileChooser.APPROVE_OPTION) {
                    filePath = j.getSelectedFile().getAbsolutePath();
                    try {
                        CleanImage cleanImage = new CleanImage(j.getSelectedFile().getAbsolutePath());
                        ImagePanel imagePanel = new ImagePanel();
                        PanelControl controls = new PanelControl(imagePanel);
                        ///////////////// Display Images in main Window
                        add(imagePanel);
                        add(controls);

                        label.setText("Success...");
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("Fatal error occurred. Select the right file...");
                    }
                }
                // If the user cancelled the operation
                else
                    label.setText("Cancelled");
            }
        }
    }
}


