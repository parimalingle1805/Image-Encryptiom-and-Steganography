plainTxt = input("enter plain text:\n")
encTxt = ""
for character in plainTxt:
    encTxt += chr(ord(character) + 2)

print("Plain text:\n", plainTxt)
print()
print("Encrypted Text:\n", encTxt)
print()

decTxt = ""
for character in encTxt:
    decTxt += chr(ord(character) -2)

print("Decrypted Text:\n",  decTxt)
