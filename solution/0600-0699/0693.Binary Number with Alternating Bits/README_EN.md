# [693. Binary Number with Alternating Bits](https://leetcode.com/problems/binary-number-with-alternating-bits)

[中文文档](/solution/0600-0699/0693.Binary%20Number%20with%20Alternating%20Bits/README.md)

## Description

<p>Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary representation of 5 is: 101
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 7
<strong>Output:</strong> false
<strong>Explanation:</strong> The binary representation of 7 is: 111.</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 11
<strong>Output:</strong> false
<strong>Explanation:</strong> The binary representation of 11 is: 1011.</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> true
<strong>Explanation:</strong> The binary representation of 10 is: 1010.</pre>

<p><strong>Example 5:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> false
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
    def hasAlternatingBits(self, n: int) -> bool:
        n = (n ^ (n >> 1)) + 1
        return (n & (n - 1)) == 0
```

### **Java**

```java
class Solution {
    public boolean hasAlternatingBits(int n) {
        n = (n ^ (n >> 1)) + 1;
        return (n & (n - 1)) == 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool hasAlternatingBits(int n) {
        n ^= (n >> 1);
        return (n & ((long) n + 1)) == 0;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
