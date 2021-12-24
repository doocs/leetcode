# [204. 计数质数](https://leetcode-cn.com/problems/count-primes)

[English Version](/solution/0200-0299/0204.Count%20Primes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>统计所有小于非负整数&nbsp;<em><code>n</code>&nbsp;</em>的质数的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 10
<strong>输出：</strong>4
<strong>解释：</strong>小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出</strong>：0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 5 * 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。

我们设 `primes[i]` 表示数 i 是不是质数，如果是质数则为 true，否则为 false。从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 false，这样在运行结束的时候我们即能知道质数的个数。

对于一个质数 x，我们从 2x 开始标记其实是冗余的，应该直接从 x⋅x 开始标记，因为 2x,3x,… 这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPrimes(self, n: int) -> int:
        if n < 2:
            return 0
        res = 0
        primes = [True for _ in range(n)]
        for i in range(2, n):
            if primes[i]:
                res += 1
                for j in range(i * i, n, i):
                    primes[j] = False
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countPrimes(int n) {
        if (n < 2) return 0;
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int res = 0;
        for (int i = 2; i < n; ++i) {
            if (primes[i]) {
                ++res;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        primes[j] = false;
                    }
                }
            }
        }
        return res;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
