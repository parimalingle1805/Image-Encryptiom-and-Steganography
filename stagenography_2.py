
import cv2
import string
import time
#import os
print("##########################################")
print("#                                        #")
print("#    Welcome to Steganography Program    #")
print("#                                        #")
print("##########################################")
print()

d={}
c={}

for i in range(255):
    d[chr(i)]=i
    c[i]=chr(i)
  
  
todo = int(input("Press 1 to hide data in a image\nPress 2 to retrieve data from a image\nPress Any other key to exit"))

#print(c)
if todo==1:
    img = input("Enter name to image file in which data is to be hidden: ")

    x=cv2.imread(img)

    i=x.shape[0]
    j=x.shape[1]
    print(i,j)

    key=input("Enter secret key for hiding data : ")
    key_file = open("key.txt","w+")
    key_file.truncate(0)
    key_file.write(key)
    
    text=input("Enter text to hide : ")
    time.sleep(2)
    enc_img = input("Enter name of output file : ")

    kl=0
    tln=len(text)
    z=0 
    n=0 
    m=0 

    l=len(text)
    key_file.write(str(l))
    key_file.close()

    for i in range(l):
        x[n,m,z]=d[text[i]]^d[key[kl]]
        n=n+1
        m=m+1
        m=(m+1)%3
        kl=(kl+1)%len(key)
    
    cv2.imwrite(enc_img,x) 
    #os.startfile("encrypted_img.png")
    print("Data Hiding in Image completed successfully.")
    #x=cv2.imread(“encrypted_img.jpg")
        

    kl=0
    tln=len(text)
    z=0 #decides plane
    n=0 #number of row
    m=0 #number of column

#ch = int(input("\nEnter 1 to extract data from Image : "))

elif todo == 2:
    img = input("Enter name of image from which data is to be extracted :")
    key1=input("\n\nRe enter key to extract text : ")
    decrypt=""

    key_file = open("key.txt","r+")
    text_from_file = key_file.read()
    key = text_from_file[0]
    l=int(text_from_file[1])
    kl=0
    tln=l
    z=0 #decides plane
    n=0 #number of row
    m=0

    for i in range(l):
        x[n,m,z]=d[text[i]]^d[key[kl]]
        n=n+1
        m=m+1
        m=(m+1)%3
        kl=(kl+1)%len(key)

    print(key,l)
    if key1 == key:
        for i in range(l):
            decrypt+=c[x[n,m,z]^d[key[kl]]]
            n=n+1
            m=m+1
            m=(m+1)%3
            kl=(kl+1)%len(key)
        print("Encrypted text was : ",decrypt)
    else:
        print("Key doesn't matched.")

print("Thank you. EXITING.")
   

    
    
 
    
    
    