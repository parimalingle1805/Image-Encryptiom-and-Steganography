import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encryption {
	public static void main(String[] args)
		throws FileNotFoundException, IOException
	{
		Scanner sc = new Scanner(System.in);
		//System.out.println("Note : Encryption Key act as Password to Decrypt the same Image,otherwise it will corrupt the Image.");
	
		// Here key is act as password to Encrypt and
		// Decrypt the Image
		System.out.println("\nEnter name of input image\n");
		String rawFile = sc.nextLine();

		System.out.print("\nEnter key for Encryption :\n ");
		int key = sc.nextInt();
							
		FileInputStream fis = new FileInputStream("C:\\Users\\Parimal\\Pictures\\" + rawFile);

		String fileName = "C:\\Users\\Parimal\\Pictures\\"+rawFile;
        Path path = Paths.get(fileName);
      
        
        long size =0;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
							
	
			
		long startTime = System.nanoTime();

		byte data[] = new byte[fis.available()];
							
		fis.read(data);
		int i = 0;
							
		
		for (byte b : data) {
			data[i] = (byte)(b ^ key);
			i++;
		}
							
		System.out.println("Output file will be stored as 'enc.png' in the same directory.");
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Parimal\\Pictures\\enc.png");
							

							
		fos.write(data);
							
		
		System.out.println("\nEncyption Done...\n");

		

		long endTime = System.nanoTime();

		System.out.println("Size of image is: " + size + " Bytes");
		System.out.println("Time to encrypt:"+ ((endTime - startTime)/1000000) + "ms");

		fos.close();
		fis.close();
	}
}
