# [400. 第 N 位数字](https://leetcode-cn.com/problems/nth-digit)

[English Version](/solution/0400-0499/0400.Nth%20Digit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 <code>n</code><em> </em>位数字。</p>

<p> </p>

<p><strong>注意：</strong><code>n</code><em> </em>是正数且在 32 位整数范围内（<code>n < 2<sup>31</sup></code>）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>3
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>11
<strong>输出：</strong>0
<strong>解释：</strong>第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 <strong>0 </strong>，它是 10 的一部分。
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
        while (n / bits > t)
        {
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
