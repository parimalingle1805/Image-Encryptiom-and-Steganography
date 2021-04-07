import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decryption {
	
	public static void main(String[] args)
		throws FileNotFoundException, IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Note : Encryption Key act as Password to Decrypt the same Image, otherwise it will corrupt the Image.");
		
        System.out.println("Enter file name to decrypt: ");
        String encrypted = sc.nextLine();
        
		System.out.print("Enter a key for Decryption : ");
		int key = sc.nextInt();
			

        
		FileInputStream fis = new FileInputStream("C:\\Users\\Parimal\\Pictures\\" + encrypted);

        long startTime = System.nanoTime();

		
		String fileName = "C:\\Users\\Parimal\\Pictures\\"+encrypted;
        Path path = Paths.get(fileName);

        
        long size =0;
        try {
            
            size = Files.size(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		byte data[] = new byte[fis.available()];
			
		// Read the array
			
		fis.read(data);
		int i = 0;
			
		// Performing an XOR operation
		// on each value of
		// byte array to Decrypt it.
		for (byte b : data) {
			data[i] = (byte)(b ^ key);
			i++;
		}
			
		System.out.println("Output will be stored as 'dec.png' in the same directory");
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Parimal\\Pictures\\dec.png");
			
		// Writting Decrypted data on Image
		fos.write(data);
		fos.close();
		fis.close();
		System.out.println("Decyption Done...");

        long endTime = System.nanoTime();

        System.out.println("Size of image is: " + size + " Bytes");
        System.out.println("Time to decrypt:"+ ((endTime - startTime)/1000000) + "ms");
	}
}

