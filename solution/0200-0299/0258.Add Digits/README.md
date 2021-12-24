# [258. 各位相加](https://leetcode-cn.com/problems/add-digits)

[English Version](/solution/0200-0299/0258.Add%20Digits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非负整数 <code>num</code>，反复将各个位上的数字相加，直到结果为一位数。</p>

<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> <code>38</code>
<strong>输出:</strong> 2 
<strong>解释: </strong>各位相加的过程为<strong>：</strong><code>3 + 8 = 11</code>, <code>1 + 1 = 2</code>。 由于&nbsp;<code>2</code> 是一位数，所以返回 2。
</pre>

<p><strong>进阶:</strong><br>
你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

题目要求的数叫做“数根”，我们把 1~30 的数根列出来：

```
原数: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
数根: 1 2 3 4 5 6 7 8 9  1  2  3  4  5  6  7  8  9  1  2  3  4  5  6  7  8  9  1  2  3
```

可以看到，数根 9 个为一组，循环出现。我们可以得出下面的规律：

- n = 0：数根是 0
- n 是 9 的倍数：数根是 9
- n 不是 9 的倍数：数根是 n % 9

将上面的规律用式子：`(n - 1) % 9 + 1` 统一表达。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def addDigits(self, num: int) -> int:
        return 0 if num == 0 else (num - 1) % 9 + 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
