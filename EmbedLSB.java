import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
Source: https://www.youtube.com/watch?v=e4xphUB0Ptg
*/

public class EmbedLSB {

    public static void Embed(File imageFile, String message, String outputPath) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            Pixel[] pixels = getPixels(image);

            // Convert each character to 8-bit binary string
            String[] messageBinary = new String[message.length()];
            for (int i = 0; i < message.length(); i++) {
                String bin = Integer.toBinaryString(message.charAt(i));
                // Left-pad with zeros to make it 8 bits
                messageBinary[i] = String.format("%8s", bin).replace(' ', '0');
            }

            // Encode each character into 3 pixels (3 bits per pixel channel)
            for (int i = 0; i < messageBinary.length; i++) {
                int base = i * 3;
                boolean isLast = (i + 1 == messageBinary.length);
                String bits = messageBinary[i];

                // First pixel: bits 0-2, Second pixel: bits 3-5
                for (int p = 0; p < 2; p++) {
                    int b = p * 3;
                    pixels[base + p].color = new Color(
                        setLSB(pixels[base + p].color.getRed(),   bits.charAt(b)),
                        setLSB(pixels[base + p].color.getGreen(), bits.charAt(b + 1)),
                        setLSB(pixels[base + p].color.getBlue(),  bits.charAt(b + 2))
                    );
                }

                // Third pixel: bits 6-7, and B channel signals end (0) or not (1)
                pixels[base + 2].color = new Color(
                    setLSB(pixels[base + 2].color.getRed(),   bits.charAt(6)),
                    setLSB(pixels[base + 2].color.getGreen(), bits.charAt(7)),
                    setLSB(pixels[base + 2].color.getBlue(),  isLast ? '0' : '1')
                );
            }

            // Write modified pixels back to image
            for (Pixel p : pixels) {
                image.setRGB(p.x, p.y, p.color.getRGB());
            }

            ImageIO.write(image, "png", new File(outputPath));
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

    // Replace the last bit of a color channel value with the given bit character
    private static int setLSB(int colorValue, char bit) {
        String bin = Integer.toBinaryString(colorValue);
        return Integer.parseInt(bin.substring(0, bin.length() - 1) + bit, 2);
    }
}
