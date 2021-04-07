import numpy as np
from PIL import Image

def Encode(src, message, dest):
    
    img = Image.open(src, 'r')
    width, height = img.size
    array = np.array(list(img.getdata()))

    if img.mode == 'RGB':
        n = 3
        m = 0
    elif img.mode == 'RGBA':
        n = 4
        m = 1

    total_pixels = array.size//n

    message += "$t3g0"
    b_message = ''.join([format(ord(i), "08b") for i in message])
    req_pixels = len(b_message)

    if req_pixels > total_pixels:
        print("ERROR: Need larger file size")

    else:
        index=0
        for p in range(total_pixels):
            for q in range(m, n):
                if index < req_pixels:
                    array[p][q] = int(bin(array[p][q])[2:9] + b_message[index], 2)
                    index += 1

        array=array.reshape(height, width, n)
        enc_img = Image.fromarray(array.astype('uint8'), img.mode)
        enc_img.save(dest)
        print("Image Encoded Successfully")

def Decode(src):
    
    img = Image.open(src, 'r')
    array = np.array(list(img.getdata()))

    if img.mode == 'RGB':
        n = 3
        m = 0
    elif img.mode == 'RGBA':
        n = 4
        m = 1

    total_pixels = array.size//n

    hidden_bits = ""
    for p in range(total_pixels):
        for q in range(m, n):
            hidden_bits += (bin(array[p][q])[2:][-1])

    hidden_bits = [hidden_bits[i:i+8] for i in range(0, len(hidden_bits), 8)]

    message = ""
    for i in range(len(hidden_bits)):
        if message[-5:] == "$t3g0":
            break
        else:
            message += chr(int(hidden_bits[i], 2))
    if "$t3g0" in message:
         return message[:-5]
    else:
        print("No Hidden Message Found")

def Stego():
    print("##########################################")
    print("#                                        #")
    print("#    Welcome to Steganography Program    #")
    print("#                                        #")
    print("##########################################")
    print()
    print("Enter digit to choose option:")
    print("1: Encode")
    print("2: Decode")

    func = input()

    if func == '1':
        print("\nEnter Source Image Path:\n")
        src = input()
        print("\nEnter Message to Hide:\n")
        message = input()
        print("\nSetting Destination Image Path to same as Source Image Path...\n")
        dest = src
        print("Encoding......")
        encTxt = ""
        for character in message:
            encTxt += chr(ord(character) + 2)
        Encode(src, encTxt, dest)

    elif func == '2':
        print("\nEnter Source Image Path:\n")
        src = input()
        print("Decoding...")
        encTxt =  Decode(src)
        decTxt = ""
        for character in encTxt:
            decTxt += chr(ord(character) -2)
        print(decTxt)

    else:
        print("ERROR: Invalid option chosen")

Stego()