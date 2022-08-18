# [05.01. Insert Into Bits](https://leetcode.cn/problems/insert-into-bits-lcci)

[中文文档](/lcci/05.01.Insert%20Into%20Bits/README.md)

## Description

<p>You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to insert M into N such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M. That is, if M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.</p>

<p><strong>Example1:</strong></p>

<pre>



<strong> Input</strong>: N = 10000000000, M = 10011, i = 2, j = 6



<strong> Output</strong>: N = 10001001100



</pre>

<p><strong>Example2:</strong></p>

<pre>



<strong> Input</strong>:  N = 0, M = 11111, i = 0, j = 4



<strong> Output</strong>: N = 11111



</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python


```

### **Java**

```java
class Solution {
    public int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; k++) {
            N &= ~(1 << k);
        }
        return N ^ (M << i);
    }
}
```

### **...**

```


```

<!-- tabs:end -->
