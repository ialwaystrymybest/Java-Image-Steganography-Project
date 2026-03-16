import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("What would you like to do? (A/B)");
            System.out.println("A: Write a hidden message to an image.");
            System.out.println("B: Read a hidden message from an image.");

            String action = sc.nextLine();

            if (action.equals("A")) {
                System.out.println("What message would you like to hide?");
                String message = sc.nextLine();

                System.out.println("Where is your image located? (eg. pictures\\image.png)");
                String inputPath = sc.nextLine();

                System.out.println("Where would you like to save the output image?");
                String outputPath = sc.nextLine();

                EmbedLSB.Embed(new File(inputPath), message, outputPath);
                System.out.println("Message hidden successfully!");

            } else if (action.equals("B")) {
                System.out.println("Enter the location of the image.");
                String imagePath = sc.nextLine();

                ExtractLSB.Extract(imagePath);

            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
