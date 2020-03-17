#!/usr/bin/env bash

# NF是当前行的field字段数；NR是正在处理的当前行数。
awk '{
  for(i=1;i<=NF;i++){
    res[$i]++
  }
}
END{
  for(k in res){
    print k,res[k]
  }
}' words.txt | sort -nr -k2

#or:

cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{print $2" "$1}'