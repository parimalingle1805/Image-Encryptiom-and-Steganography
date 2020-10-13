

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

        

        int e=0,d=0;
        System.out.println("Choose option number and press Enter\n 1. Encrypt\n 2. Decrypt (No image encrypted yet)");

        e=scan.nextInt();
        System.out.println("\n\n");

        if(e==1){
        System.out.println("Starting Encryption.....");
        encryptFile.encrypt(directoryPath + fileToEncrypt, directoryPath + encryptedFile);
        System.out.println("Encryption completed successfully...!!!\n");

        }else{
            System.out.println("\nInvalid Input\n");
            System.exit(0);
        }

        
        System.out.println("Choose option number and press Enter\n 1.Encrypt (An encrypted image already exists)\n 2. Decrypt\n 3. To exit without decrypting");
        d=scan.nextInt();
        System.out.println("\n\n");

        
        if(d==2){
        System.out.println("Starting Decprytion.........");
        encryptFile.decrypt(directoryPath+encryptedFile,directoryPath+decryptedFile);
        System.out.println("Decryption completed Succesfully...!!!\n");
        }
        
        else if(d==3){
            System.out.println("Exiting.....");            
            System.out.println("Done!!!");
            System.exit(0);

        
        }else{
            System.out.println("\nInvalid Input\n");
            System.exit(0);
        }
       
    }
    private void encrypt(String srcPath, String destPath){
        File rawFile=new File("C:\\Users\\Parimal\\Pictures\\"+"toEncrypt.jpg");
        File encryptedFile= new File("C:\\Users\\Parimal\\Pictures\\"+"encryptedFile.jpg");
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
    private void decrypt(String srcPath, String destPath){
        File encryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+"encryptedFile.jpg");
        File decryptedFile = new File("C:\\Users\\Parimal\\Pictures\\"+"decryptedFile.jpg");
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