import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner myObj = new Scanner(System.in);
        System.out.println("What would you like to do?(A/B)\nA:Write a hidden message to an image.\nB:Read a hidden message from an image.");

        String action = myObj.nextLine();

        if(action.equals("A")){
            System.out.println("What message would you like to hide?");
            String message = myObj.nextLine();

            System.out.println("Where is your image located? (eg. pictures\\image.png)");
            String input_path = myObj.nextLine();

            System.out.println("Where would you like to save the output image?");
            String output_path = myObj.nextLine();

            Generate_image(message, input_path, output_path);
        }
        else if(action.equals("B")){

            System.out.println("Enter the location of the image.");
            String output_path = myObj.nextLine();

            Extract_text(output_path);
        }
        else {
            System.out.println("Invalid input!");
        }
    }

    public static void Generate_image(String message, String input_path, String output_path) throws Exception {
        File imageFile = new File(input_path);

        if(imageFile != null) {
            EmbedLSB.Embed(imageFile, message, output_path);
            System.out.println("Message is hidden successfully");
        }
    }

    public static void Extract_text(String output_path) throws Exception {
        ExtractLSB.Extract(output_path);
    }
}
