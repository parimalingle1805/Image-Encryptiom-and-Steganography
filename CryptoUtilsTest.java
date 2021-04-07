import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CryptoUtilsTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String key = "0123456789101112";
        System.out.println("\nEnter name of input image\n");
		String rawFile = sc.nextLine();
        File inputFile = new File("C:\\Users\\Parimal\\Pictures\\" + rawFile);

        File encryptedFile = new File("C:\\Users\\Parimal\\Pictures\\enc_aes.png");
        File decryptedFile = new File("C:\\Users\\Parimal\\Pictures\\dec_aes.png");

        String fileName = "C:\\Users\\Parimal\\Pictures\\"+rawFile;
        Path path = Paths.get(fileName);
      
        
        long size =0;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int option = 0;
        System.out.println("Choose Option:\n 1. Encrypt\n 2. Decrypt\n 3. Exit");
        option = sc.nextInt();
        

        
        switch(option){
            case 1:
                long startTime_enc = System.nanoTime();

                try {
                    CryptoUtils.encrypt(key, inputFile, encryptedFile);
                    
                } catch (CryptoException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
                System.out.println("Output file will be stored as 'enc_aes.png' in the same directory.");

                long endTime_enc = System.nanoTime();

                System.out.println("Size of image is: " + size + " Bytes");
                System.out.println("Time to encrypt:"+ ((endTime_enc - startTime_enc)/1000000) + "ms");
                break;


            case 2:
                long startTime_dec = System.nanoTime();
                try {
                    CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
                } catch (CryptoException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
                System.out.println("Output file will be stored as 'dec_aes.png' in the same directory.");

                long endTime_dec = System.nanoTime();

                System.out.println("Size of image is: " + size + " Bytes");
                System.out.println("Time to encrypt:"+ ((endTime_dec - startTime_dec)/1000000) + "ms");
                break;
            case 3:
                System.out.println("Exiting...\nThankyou!!!");
                System.exit(0);
        }
        
    }
}