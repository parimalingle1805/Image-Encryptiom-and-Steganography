import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




public class EncryptionFile{
    KeyGenerator keyGenerator=null;
    SecretKey secretKey=null;
    Cipher cipher=null;

    public EncryptionFile(){
        try{
            keyGenerator=KeyGenerator.getInstance("Blowfish");
            secretKey=keyGenerator.generateKey();
            

            cipher=Cipher.getInstance("Blowfish");
        }catch(NoSuchPaddingException ex){
            System.out.println(ex);
        }catch(NoSuchAlgorithmException ex){
            System.out.println(ex);
        }
    }


    public static void main(String[] args) {
        String fileToEncrypt="toEncrypt.jpg"; //image file to encrypt
        String encryptedFile="encryptedFile.jpg";
        String decryptedFile="decryptedFile.jpg";
        String directoryPath="C:\\Users\\Parimal\\Pictures"; //For Windows
        //String directoryPath="/mnt/DA1C19171C18F06D/codingProjects/Projects/ImageEncryptionAndSteganography/"; //For Linux
        EncryptionFile encryptFile = new EncryptionFile();
        Scanner scan=new Scanner(System.in);





        System.out.println("************************************************");
        System.out.println("////WELCOME TO ENCRYPTION/DECRPYTION PROGRAM////");
        System.out.println("************************************************\n");

        
        //Initializing Menu with loop

        
        System.out.print("Enter number of operations: ");
        int numberOfOperations=scan.nextInt();
        System.out.println();

        for(int i=0;i<numberOfOperations;i++){

            System.out.println("Choose option number and press Enter\n 1. Encrypt\n 2. Decrypt");

            int Option = scan.nextInt();

            switch(Option){

                case 1:
                    System.out.println("Initializing.....");
                    encryptFile.encrypt(directoryPath + fileToEncrypt, directoryPath + encryptedFile);
                    System.out.println("Encryption completed successfully...!!!\n");
                    break;

                case 2:
                    System.out.println("Initializing.....");
                    encryptFile.decrypt(directoryPath + encryptedFile, directoryPath + decryptedFile);
                    System.out.println("Decryption completed Succesfully...!!!\n");
                    break;


            }
        
        }
        
       
    }




    //Encrypt Function


    private void encrypt(String srcPath, String destPath){
        Scanner scan = new Scanner(System.in);

        //User will input exact name of the image, to be encrypted and desired name of the output encrypted file.

        System.out.print("Enter name of image to be encrypted: ");   
        String imageToEncrypt=scan.nextLine();
        System.out.println();
        System.out.println("Enter the desired name of the output encrypted image file: ");
        String encryptedImageName=scan.nextLine();
        System.out.println();
        System.out.println("Starting Encryption.......\n");

        File rawFile = new File("C:\\Users\\Parimal\\Pictures\\"+imageToEncrypt); //For Windows
        //File rawFile=new File("/mnt/DA1C19171C18F06D/codingProjects/Projects/ImageEncryptionAndSteganography/"+imageToEncrypt); //For Linux
        File encryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+encryptedImageName); //For Windows
        //File encryptedFile= new File("/mnt/DA1C19171C18F06D/codingProjects/Projects/ImageEncryptionAndSteganography/"+encryptedImageName); //For Linux
        InputStream inStream=null;
        OutputStream outStream=null;


        String fileName = "C:\\Users\\Parimal\\Pictures\\"+imageToEncrypt;
        Path path = Paths.get(fileName);
        BufferedImage bimg;
        int width = 0;
        int height = 0;
        long size =0;
        try {
            bimg = ImageIO.read(rawFile);
            width = bimg.getWidth();
            height = bimg.getHeight();
            size = Files.size(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        try{
            long startTime = System.nanoTime();
            //initializing encryption
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            //initialize io streams

            inStream = new FileInputStream(rawFile);
            outStream = new FileOutputStream(encryptedFile);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inStream.read(buffer))>0){
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();

            long endTime = System.nanoTime();

            System.out.println("Resolution of input image is: " + width + " X " + height);
            System.out.println("Size of image is: " + size + " Bytes");
            System.out.println("Time to encrypt:"+ ((endTime - startTime)/1000000) + "ms");
            
        } catch(IllegalBlockSizeException ex){
            System.out.println(ex);
        } catch(BadPaddingException ex){
            System.out.println(ex);
        } catch(InvalidKeyException ex){
            System.out.println(ex);
        } catch(FileNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
    }


    //Decrypt Function


    private void decrypt(String srcPath, String destPath){

        Scanner scan = new Scanner(System.in);

        //User will input exact name of the image, to be decrypted and desired name of the output decrypted file.

        System.out.print("Enter name of image to be decrypted: ");   
        String imageToDecrypt=scan.nextLine();
        System.out.println();
        System.out.println("Enter the desired name of the output decrypted image file: ");
        String decryptedImageName=scan.nextLine();
        System.out.println();
        System.out.println("Starting Decryption...............\n");


        File encryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+imageToDecrypt); //For Windows
        //File encryptedFile = new File("/mnt/DA1C19171C18F06D/codingProjects/Projects/ImageEncryptionAndSteganography/"+imageToDecrypt); //For Linux
        File decryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+decryptedImageName); //For Windows
        //File decryptedFile = new File("/mnt/DA1C19171C18F06D/codingProjects/Projects/ImageEncryptionAndSteganography/"+decryptedImageName); //For Linux
        InputStream inStream=null;
        OutputStream outStream=null;

        String fileName = "C:\\Users\\Parimal\\Pictures\\"+imageToDecrypt;
        Path path = Paths.get(fileName);

        
        long size =0;
        try {
            
            size = Files.size(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try{
            long startTime = System.nanoTime();

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            inStream=new FileInputStream(encryptedFile);
            outStream=new FileOutputStream(decryptedFile);
            byte[] buffer=new byte[1024];
            int len;
            while ((len=inStream.read(buffer))>0){
                outStream.write(cipher.update(buffer, 0, len));
                outStream.flush();
            }
            outStream.write(cipher.doFinal());
            inStream.close();
            outStream.close();


            long endTime = System.nanoTime();

            System.out.println("Size of image is: " + size + " Bytes");
            System.out.println("Time to decrypt:"+ ((endTime - startTime)/1000000) + "ms");

        } catch(IllegalBlockSizeException ex){
            System.out.println(ex);
        } catch(BadPaddingException ex){
            System.out.println(ex);
        } catch(InvalidKeyException ex){
            System.out.println(ex);
        } catch(FileNotFoundException ex){
            System.out.println(ex);
        } catch(IOException ex){
            System.out.println(ex);
        }
    }
}