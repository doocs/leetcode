# [400. 第 N 位数字](https://leetcode.cn/problems/nth-digit)

[English Version](/solution/0400-0499/0400.Nth%20Digit/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数 <code>n</code> ，请你在无限的整数序列&nbsp;<code>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]</code> 中找出并返回第&nbsp;<code>n</code><em> </em>位上的数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 11
<strong>输出：</strong>0
<strong>解释：</strong>第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 <strong>0 </strong>，它是 10 的一部分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

位数为 $k$ 的最小整数和最大整数分别为 $10^{k-1}$ 和 $10^k-1$，因此 $k$ 位数的总位数为 $k \times 9 \times 10^{k-1}$。

我们用 $k$ 表示当前数字的位数，用 $cnt$ 表示当前位数的数字的总数，初始时 $k=1$, $cnt=9$。

每次将 $n$ 减去 $cnt \times k$，当 $n$ 小于等于 $cnt \times k$ 时，说明 $n$ 对应的数字在当前位数的数字范围内，此时可以计算出对应的数字。

具体做法是，首先计算出 $n$ 对应的是当前位数的哪一个数字，然后计算出是该数字的第几位，从而得到该位上的数字。

时间复杂度 $O(\log_{10} n)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findNthDigit(self, n: int) -> int:
        k, cnt = 1, 9
        while k * cnt < n:
            n -= k * cnt
            k += 1
            cnt *= 10
        num = 10 ** (k - 1) + (n - 1) // k
        idx = (n - 1) % k
        return int(str(num)[idx])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findNthDigit(int n) {
        int k = 1, cnt = 9;
        while ((long) k * cnt < n) {
            n -= k * cnt;
            ++k;
            cnt *= 10;
        }
        int num = (int) Math.pow(10, k - 1) + (n - 1) / k;
        int idx = (n - 1) % k;
        return String.valueOf(num).charAt(idx) - '0';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findNthDigit(int n) {
        int k = 1, cnt = 9;
        while (1ll * k * cnt < n) {
            n -= k * cnt;
            ++k;
            cnt *= 10;
        }
        int num = pow(10, k - 1) + (n - 1) / k;
        int idx = (n - 1) % k;
        return to_string(num)[idx] - '0';
    }
};
```

### **Go**

```go
func findNthDigit(n int) int {
	k, cnt := 1, 9
	for k*cnt < n {
		n -= k * cnt
		k++
		cnt *= 10
	}
	num := int(math.Pow10(k-1)) + (n-1)/k
	idx := (n - 1) % k
	return int(strconv.Itoa(num)[idx] - '0')
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var findNthDigit = function (n) {
    let k = 1,
        cnt = 9;
    while (k * cnt < n) {
        n -= k * cnt;
        ++k;
        cnt *= 10;
    }
    const num = Math.pow(10, k - 1) + (n - 1) / k;
    const idx = (n - 1) % k;
    return num.toString()[idx];
};
```

### **C#**

```cs
public class Solution {
    public int FindNthDigit(int n) {
        int k = 1, cnt = 9;
        while ((long) k * cnt < n) {
            n -= k * cnt;
            ++k;
            cnt *= 10;
        }
        int num = (int) Math.Pow(10, k - 1) + (n - 1) / k;
        int idx = (n - 1) % k;
        return num.ToString()[idx] - '0';
    }
}
```

### **...**

```

```

<!-- tabs:end -->
