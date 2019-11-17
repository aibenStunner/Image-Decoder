import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CleanImage {
    private String outPath = "decodedPicture.txt";
    private File inputFile;
    private Scanner reader;
    private int height, width;
    private BufferedImage image;
    private Color[] tomatoColor = new Color[]{Color.BLACK, new Color(0, 143, 0), new Color(0, 51, 0), Color.WHITE, new Color(255, 180, 170), Color.RED};  //Tomato
    private Color[] orangeColor = new Color[]{Color.BLACK, new Color(0, 143, 0), new Color(0, 51, 0), Color.WHITE, new Color(255, 200, 120), new Color(255, 120, 20)};  //Orange
    private Color[] appleColor = new Color[]{Color.BLACK, new Color(0, 143, 0), new Color(0, 51, 0), Color.WHITE, new Color(102, 255, 102), new Color(50, 220, 0)};  //Apple

    CleanImage(String inputFile) {
        this.inputFile = new File(inputFile);
        clean();
        this.inputFile = new File(outPath);
        createImage();
    }

    public void clean() {
        try {
            reader = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        int temp, tempPixel, colCount = 0;
        // Decompress picture.txt and save to a decodedPicture.txt
        try (PrintStream ps = new PrintStream(outPath)) {
            while (reader.hasNext()) {
                temp = reader.nextInt();
                if (temp == -1)
                    break;
                if (count == 2) {
                    tempPixel = reader.nextInt();
                    for (int i = 0; i < temp; i++) {
                        ps.print(tempPixel + " ");
                        colCount += 1;
                        if (colCount == width) {
                            colCount = 0;
                            ps.println();
                        }
                    }
                }
                if (count == 1) {
                    height = temp;
                    count++;
                }
                if (count == 0) {
                    width = temp;
                    count++;
                }
            }
            ps.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage populateImg(Color[] color) {
        // Create image from decodedPicture.txt
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        while (reader.hasNext()) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++)
                    image.setRGB(j, i, color[reader.nextInt()].getRGB());
            }
        }
        return image;
    }

    public void createImage() {
        String[] pics = new String[]{"tomato.png", "orange.png", "apple.png"};
        // Generate images
        for (int p = 0; p < 3; p++) {
            try {
                reader = new Scanner(inputFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (p == 0)
                image = populateImg(tomatoColor);
            if (p == 1)
                image = populateImg(orangeColor);
            if (p == 2)
                image = populateImg(appleColor);
            // Save images to disk
            try {
                ImageIO.write(image, "png", new File(pics[p]));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
