# [2507. 使用质因数之和替换后可以取到的最小值](https://leetcode.cn/problems/smallest-value-after-replacing-with-sum-of-prime-factors)

[English Version](/solution/2500-2599/2507.Smallest%20Value%20After%20Replacing%20With%20Sum%20of%20Prime%20Factors/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>n</code> 。</p>

<p>请你将 <code>n</code> 的值替换为 <code>n</code> 的 <strong>质因数</strong> 之和，重复这一过程。</p>

<ul>
	<li>注意，如果 <code>n</code> 能够被某个质因数多次整除，则在求和时，应当包含这个质因数同样次数。</li>
</ul>

<p>返回<em> </em><code>n</code><em> </em>可以取到的最小值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 15
<strong>输出：</strong>5
<strong>解释：</strong>最开始，n = 15 。
15 = 3 * 5 ，所以 n 替换为 3 + 5 = 8 。
8 = 2 * 2 * 2 ，所以 n 替换为 2 + 2 + 2 = 6 。
6 = 2 * 3 ，所以 n 替换为 2 + 3 = 5 。
5 是 n 可以取到的最小值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>3
<strong>解释：</strong>最开始，n = 3 。
3 是 n 可以取到的最小值。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：暴力模拟**

根据题意，我们可以得到一个质因数分解的过程，即将一个数不断地分解为质因数，分解不能分解。过程中将每次分解的质因数相加，递归或者迭代地进行即可。

时间复杂度 $O(\sqrt{n})$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def smallestValue(self, n: int) -> int:
        while 1:
            t, s, i = n, 0, 2
            while i <= n // i:
                while n % i == 0:
                    n //= i
                    s += i
                i += 1
            if n > 1:
                s += n
            if s == t:
                return t
            n = s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int smallestValue(int n) {
        while (true) {
            int t = n, s = 0;
            for (int i = 2; i <= n / i; ++i) {
                while (n % i == 0) {
                    s += i;
                    n /= i;
                }
            }
            if (n > 1) {
                s += n;
            }
            if (s == t) {
                return s;
            }
            n = s;
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestValue(int n) {
        while (1) {
            int t = n, s = 0;
            for (int i = 2; i <= n / i; ++i) {
                while (n % i == 0) {
                    s += i;
                    n /= i;
                }
            }
            if (n > 1) s += n;
            if (s == t) return s;
            n = s;
        }
    }
};
```

### **Go**

```go
func smallestValue(n int) int {
	for {
		t, s := n, 0
		for i := 2; i <= n/i; i++ {
			for n%i == 0 {
				s += i
				n /= i
			}
		}
		if n > 1 {
			s += n
		}
		if s == t {
			return s
		}
		n = s
	}
}
```

### **...**

```

```

<!-- tabs:end -->
