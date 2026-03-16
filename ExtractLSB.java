import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
Source: https://www.youtube.com/watch?v=e4xphUB0Ptg
*/

public class ExtractLSB {

    public static void Extract(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Pixel[] pixels = getPixels(image);

            StringBuilder message = new StringBuilder();
            int i = 0;

            while (true) {
                // Read 3 pixels at a time to reconstruct one character
                Pixel p0 = pixels[i], p1 = pixels[i + 1], p2 = pixels[i + 2];
                i += 3;

                // Collect the last bit of each R, G, B channel from the 3 pixels
                // That gives us 8 bits for the character (we skip the last B bit — it's the end signal)
                String bits =
                    getLSB(p0.color.getRed())   +
                    getLSB(p0.color.getGreen()) +
                    getLSB(p0.color.getBlue())  +
                    getLSB(p1.color.getRed())   +
                    getLSB(p1.color.getGreen()) +
                    getLSB(p1.color.getBlue())  +
                    getLSB(p2.color.getRed())   +
                    getLSB(p2.color.getGreen());

                int ascii = Integer.parseInt(bits, 2);
                message.append((char) ascii);

                // The last bit of p2's Blue channel signals end of message (0 = end)
                if (getLSB(p2.color.getBlue()).equals("0")) {
                    break;
                }
            }

            System.out.println("Message: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get all pixels from image as a flat array
    private static Pixel[] getPixels(BufferedImage image) {
        int w = image.getWidth(), h = image.getHeight();
        Pixel[] pixels = new Pixel[w * h];
        int count = 0;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                pixels[count++] = new Pixel(x, y, new Color(image.getRGB(x, y)));
            }
        }
        return pixels;
    }

    // Get the last (least significant) bit of a color channel value as a String "0" or "1"
    private static String getLSB(int colorValue) {
        return String.valueOf(colorValue & 1);
    }
}
