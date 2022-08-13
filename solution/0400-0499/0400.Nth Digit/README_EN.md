# [400. Nth Digit](https://leetcode.com/problems/nth-digit)

[中文文档](/solution/0400-0499/0400.Nth%20Digit/README.md)

## Description

<p>Given an integer <code>n</code>, return the <code>n<sup>th</sup></code> digit of the infinite integer sequence <code>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 3
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 11
<strong>Output:</strong> 0
<strong>Explanation:</strong> The 11<sup>th</sup> digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findNthDigit(self, n: int) -> int:
        bits, t = 1, 9
        while n > bits * t:
            n -= bits * t
            bits += 1
            t *= 10

        start = 10 ** (bits - 1) + (n // bits) - 1
        if n % bits == 0:
            return start % 10
        return int(str((start + 1))[(n % bits) - 1])
```

### **Java**

```java
class Solution {
    public int findNthDigit(int n) {
        int bits = 1, t = 9;
        while (n / bits > t) {
            n -= bits * t;
            ++bits;
            t *= 10;
        }
        int start = (int) Math.pow(10, bits - 1) + (n / bits) - 1;
        if (n % bits == 0) {
            return start % 10;
        }
        return String.valueOf(start + 1).charAt((n % bits) - 1) - '0';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findNthDigit(int n) {
        int bits = 1, t = 9;
        while (n / bits > t) {
            n -= bits * t;
            ++bits;
            t *= 10;
        }
        int start = pow(10, bits - 1) + (n / bits) - 1;
        if (n % bits == 0) return start % 10;
        return to_string(start + 1)[(n % bits) - 1] - '0';
    }
};
```

### **...**

```

```

<!-- tabs:end -->
