# [693. 交替位二进制数](https://leetcode-cn.com/problems/binary-number-with-alternating-bits)

[English Version](/solution/0600-0699/0693.Binary%20Number%20with%20Alternating%20Bits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>true
<strong>解释：</strong>5 的二进制表示是：101
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>false
<strong>解释：</strong>7 的二进制表示是：111.</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 11
<strong>输出：</strong>false
<strong>解释：</strong>11 的二进制表示是：1011.</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>true
<strong>解释：</strong>10 的二进制表示是：1010.</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

假设 01 交替出现，那么我们可以通过错位异或将尾部全部转为 1，加 1 可以得到 2 的幂次的一个数 n（n 中只有一个位是 1），接着利用 `n & (n - 1)` 可以消除最后一位的 1。

此时判断是否为 0，若是，说明假设成立，是 01 交替串。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def hasAlternatingBits(self, n: int) -> bool:
        n = (n ^ (n >> 1)) + 1
        return (n & (n - 1)) == 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
