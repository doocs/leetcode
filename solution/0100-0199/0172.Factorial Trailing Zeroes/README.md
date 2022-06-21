# [172. 阶乘后的零](https://leetcode.cn/problems/factorial-trailing-zeroes)

[English Version](/solution/0100-0199/0172.Factorial%20Trailing%20Zeroes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <code>n</code> ，返回 <code>n!</code> 结果中尾随零的数量。</p>

<p>提示&nbsp;<code>n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>0
<strong>解释：</strong>3! = 6 ，不含尾随 0
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>1
<strong>解释：</strong>5! = 120 ，有一个尾随 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你可以设计并实现对数时间复杂度的算法来解决此问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

题目实际上是求 $[1,n]$ 中有多少个 $5$ 的因数。

我们以 $130$ 为例来分析：

1. 第 $1$ 次除以 $5$，得到 $26$，表示存在 $26$ 个包含因数 $5$ 的数；
1. 第 $2$ 次除以 $5$，得到 $5$，表示存在 $5$ 个包含因数 $5^2$ 的数；
1. 第 $3$ 次除以 $5$，得到 $1$，表示存在 $1$ 个包含因数 $5^3$ 的数；
1. 累加得到从 $[1,n]$ 中所有 $5$ 的因数的个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def trailingZeroes(self, n: int) -> int:
        ans = 0
        while n:
            n //= 5
            ans += n
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function trailingZeroes(n: number): number {
    let ans = 0;
    while (n > 0) {
        n = Math.floor(n / 5);
        ans += n;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int trailingZeroes(int n) {
        int ans = 0;
        for (int i = 5; i <= n; i *= 5) ans += n / i;
        return ans;
    }
};
```

### **Go**

```go
func trailingZeroes(n int) int {
	ans := 0
	for n > 0 {
		n /= 5
		ans += n
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
