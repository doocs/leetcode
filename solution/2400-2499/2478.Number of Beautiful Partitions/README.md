# [2478. 完美分割的方案数](https://leetcode.cn/problems/number-of-beautiful-partitions)

[English Version](/solution/2400-2499/2478.Number%20of%20Beautiful%20Partitions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，每个字符是数字&nbsp;<code>'1'</code>&nbsp;到&nbsp;<code>'9'</code>&nbsp;，再给你两个整数&nbsp;<code>k</code> 和&nbsp;<code>minLength</code>&nbsp;。</p>

<p>如果对 <code>s</code>&nbsp;的分割满足以下条件，那么我们认为它是一个 <strong>完美</strong>&nbsp;分割：</p>

<ul>
	<li><code>s</code>&nbsp;被分成 <code>k</code>&nbsp;段互不相交的子字符串。</li>
	<li>每个子字符串长度都 <strong>至少</strong>&nbsp;为&nbsp;<code>minLength</code>&nbsp;。</li>
	<li>每个子字符串的第一个字符都是一个 <b>质数</b> 数字，最后一个字符都是一个 <strong>非质数</strong>&nbsp;数字。质数数字为&nbsp;<code>'2'</code>&nbsp;，<code>'3'</code>&nbsp;，<code>'5'</code>&nbsp;和&nbsp;<code>'7'</code>&nbsp;，剩下的都是非质数数字。</li>
</ul>

<p>请你返回 <code>s</code>&nbsp;的 <strong>完美</strong>&nbsp;分割数目。由于答案可能很大，请返回答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后的结果。</p>

<p>一个 <strong>子字符串</strong>&nbsp;是字符串中一段连续字符串序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "23542185131", k = 3, minLength = 2
<b>输出：</b>3
<b>解释：</b>存在 3 种完美分割方案：
"2354 | 218 | 5131"
"2354 | 21851 | 31"
"2354218 | 51 | 31"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "23542185131", k = 3, minLength = 3
<b>输出：</b>1
<b>解释：</b>存在一种完美分割方案："2354 | 218 | 5131" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "3312958", k = 3, minLength = 1
<b>输出：</b>1
<b>解释：</b>存在一种完美分割方案："331 | 29 | 58" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k, minLength &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;每个字符都为数字&nbsp;<code>'1'</code>&nbsp;到&nbsp;<code>'9'</code> 之一。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $f[i][j]$ 表示前 $i$ 个字符分割成 $j$ 段的方案数。初始化 $f[0][0] = 1$，其余 $f[i][j] = 0$。

首先，我们需要判断第 $i$ 个字符是否能成为第 $j$ 段的最后一个字符，它需要同时满足以下条件：

1. 第 $i$ 个字符是一个非质数；
1. 第 $i+1$ 个字符是一个质数，或者第 $i$ 个字符是整个字符串的最后一个字符。

如果第 $i$ 个字符不能成为第 $j$ 段的最后一个字符，那么 $f[i][j]=0$。否则有：

$$
f[i][j]=\sum_{t=0}^{i-minLength}f[t][j-1]
$$

也就是说，我们要枚举上一段的结尾是哪个字符。这里我们用前缀和数组 $g[i][j] = \sum_{t=0}^{i}f[t][j]$ 来优化枚举的时间复杂度。

那么有：

$$
f[i][j]=g[i-minLength][j-1]
$$

时间复杂度 $O(n \times k)$，空间复杂度 $O(n \times k)$。其中 $n$ 和 $k$ 分别是字符串 $s$ 的长度和分割的段数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def beautifulPartitions(self, s: str, k: int, minLength: int) -> int:
        primes = '2357'
        if s[0] not in primes or s[-1] in primes:
            return 0
        mod = 10**9 + 7
        n = len(s)
        f = [[0] * (k + 1) for _ in range(n + 1)]
        g = [[0] * (k + 1) for _ in range(n + 1)]
        f[0][0] = g[0][0] = 1
        for i, c in enumerate(s, 1):
            if i >= minLength and c not in primes and (i == n or s[i] in primes):
                for j in range(1, k + 1):
                    f[i][j] = g[i - minLength][j - 1]
            for j in range(k + 1):
                g[i][j] = (g[i - 1][j] + f[i][j]) % mod
        return f[n][k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        if (!prime(s.charAt(0)) || prime(s.charAt(n - 1))) {
            return 0;
        }
        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];
        f[0][0] = 1;
        g[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (i >= minLength && !prime(s.charAt(i - 1)) && (i == n || prime(s.charAt(i)))) {
                for (int j = 1; j <= k; ++j) {
                    f[i][j] = g[i - minLength][j - 1];
                }
            }
            for (int j = 0; j <= k; ++j) {
                g[i][j] = (g[i - 1][j] + f[i][j]) % MOD;
            }
        }
        return f[n][k];
    }

    private boolean prime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int beautifulPartitions(string s, int k, int minLength) {
        int n = s.size();
        auto prime = [](char c) {
            return c == '2' || c == '3' || c == '5' || c == '7';
        };
        if (!prime(s[0]) || prime(s[n - 1])) return 0;
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        vector<vector<int>> g(n + 1, vector<int>(k + 1));
        f[0][0] = g[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (i >= minLength && !prime(s[i - 1]) && (i == n || prime(s[i]))) {
                for (int j = 1; j <= k; ++j) {
                    f[i][j] = g[i - minLength][j - 1];
                }
            }
            for (int j = 0; j <= k; ++j) {
                g[i][j] = (g[i - 1][j] + f[i][j]) % mod;
            }
        }
        return f[n][k];
    }
};
```

### **Go**

```go
func beautifulPartitions(s string, k int, minLength int) int {
	prime := func(c byte) bool {
		return c == '2' || c == '3' || c == '5' || c == '7'
	}
	n := len(s)
	if !prime(s[0]) || prime(s[n-1]) {
		return 0
	}
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	g := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
		g[i] = make([]int, k+1)
	}
	f[0][0], g[0][0] = 1, 1
	for i := 1; i <= n; i++ {
		if i >= minLength && !prime(s[i-1]) && (i == n || prime(s[i])) {
			for j := 1; j <= k; j++ {
				f[i][j] = g[i-minLength][j-1]
			}
		}
		for j := 0; j <= k; j++ {
			g[i][j] = (g[i-1][j] + f[i][j]) % mod
		}
	}
	return f[n][k]
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
