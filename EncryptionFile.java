

import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;



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
        String directoryPath="C:\\Users\\Parimal\\Pictures";
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
                    encryptFile.decrypt(directoryPath+encryptedFile,directoryPath+decryptedFile);
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

        File rawFile=new File("C:\\Users\\Parimal\\Pictures\\"+imageToEncrypt);
        File encryptedFile= new File("C:\\Users\\Parimal\\Pictures\\"+encryptedImageName);
        InputStream inStream=null;
        OutputStream outStream=null;

        try{
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


        File encryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+imageToDecrypt);
        File decryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+decryptedImageName);
        InputStream inStream=null;
        OutputStream outStream=null;

        try{
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