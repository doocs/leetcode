import os
from urllib.parse import quote

for file in os.listdir("./"):
    if os.path.isdir("./" + file) and file != '__pycache__':
        print("\n- " + file + "\n")
        for sub in os.listdir("./" + file):
            enc = quote(sub)
            print(f'\t- [{sub}](/solution/{file}/{enc}/README.md)')
