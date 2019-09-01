#!/usr/bin/env bash

# tail -n: use -n +K to output starting with the Kth
tail -n +10 file.txt | head -1

# awk: 
awk 'NR == 10' file.txt

# sed:
sed -n 10p file.txt
