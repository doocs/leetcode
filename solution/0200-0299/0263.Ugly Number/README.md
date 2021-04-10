# [263. 丑数](https://leetcode-cn.com/problems/ugly-number)

[English Version](/solution/0200-0299/0263.Ugly%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>编写一个程序判断给定的数是否为丑数。</p>

<p>丑数就是只包含质因数&nbsp;<code>2, 3, 5</code>&nbsp;的<strong>正整数</strong>。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 6
<strong>输出:</strong> true
<strong>解释: </strong>6 = 2 &times;&nbsp;3</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> 8
<strong>输出:</strong> true
<strong>解释: </strong>8 = 2 &times; 2 &times;&nbsp;2
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong> 14
<strong>输出:</strong> false 
<strong>解释: </strong><code>14</code> 不是丑数，因为它包含了另外一个质因数&nbsp;<code>7</code>。</pre>

<p><strong>说明：</strong></p>

<ol>
	<li><code>1</code>&nbsp;是丑数。</li>
	<li>输入不会超过 32 位有符号整数的范围:&nbsp;[&minus;2<sup>31</sup>,&nbsp; 2<sup>31&nbsp;</sup>&minus; 1]。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 若 `n < 1`，说明 n 一定不是丑数，返回 false。
- 若 `n % 2 == 0`，说明 2 是 n 的因子，此时应 `n /= 2`，然后继续判断 n 除以 2 后的值的因子。
- 若 `n % 3 == 0`，说明 3 是 n 的因子，此时应 `n /= 3`，然后继续判断 n 除以 3 后的值的因子。
- 若 `n % 5 == 0`，说明 5 是 n 的因子，此时应 `n /= 5`，然后继续判断 n 除以 5 后的值的因子。
- 最后，判断 n 是否等于 1，若是，说明 n 的因子只可能包含 2、3、5，返回 true；否则返回 false。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def isUgly(self, n: int) -> bool:
        if n < 1:
            return False
        while n % 2 == 0:
            n //= 2
        while n % 3 == 0:
            n //= 3
        while n % 5 == 0:
            n //= 5
        return n == 1
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean isUgly(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool isUgly(int n) {
        if (n < 1) return false;
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        return n == 1;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {boolean}
 */
var isUgly = function (n) {
  if (n < 1) return false;
  while (n % 2 == 0) {
    n /= 2;
  }
  while (n % 3 == 0) {
    n /= 3;
  }
  while (n % 5 == 0) {
    n /= 5;
  }
  return n == 1;
};
```

### **...**

```

```

<!-- tabs:end -->
