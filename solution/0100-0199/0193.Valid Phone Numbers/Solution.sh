#!/usr/bin/env bash

# -P : --perl-regexp
grep -P '^(\d{3}-|\(\d{3}\) )\d{3}-\d{4}$' file.txt

# or use sed
sed -n -E '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/p' file.txt

# or use awk
awk '/^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$/' file.txt