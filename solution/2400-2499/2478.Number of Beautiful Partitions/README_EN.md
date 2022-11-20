# [2478. Number of Beautiful Partitions](https://leetcode.com/problems/number-of-beautiful-partitions)

[中文文档](/solution/2400-2499/2478.Number%20of%20Beautiful%20Partitions/README.md)

## Description

<p>You are given a string <code>s</code> that consists of the digits <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code> and two integers <code>k</code> and <code>minLength</code>.</p>

<p>A partition of <code>s</code> is called <strong>beautiful</strong> if:</p>

<ul>
	<li><code>s</code> is partitioned into <code>k</code> non-intersecting substrings.</li>
	<li>Each substring has a length of <strong>at least</strong> <code>minLength</code>.</li>
	<li>Each substring starts with a <strong>prime</strong> digit and ends with a <strong>non-prime</strong> digit. Prime digits are <code>&#39;2&#39;</code>, <code>&#39;3&#39;</code>, <code>&#39;5&#39;</code>, and <code>&#39;7&#39;</code>, and the rest of the digits are non-prime.</li>
</ul>

<p>Return<em> the number of <strong>beautiful</strong> partitions of </em><code>s</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;23542185131&quot;, k = 3, minLength = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> There exists three ways to create a beautiful partition:
&quot;2354 | 218 | 5131&quot;
&quot;2354 | 21851 | 31&quot;
&quot;2354218 | 51 | 31&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;23542185131&quot;, k = 3, minLength = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> There exists one way to create a beautiful partition: &quot;2354 | 218 | 5131&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;3312958&quot;, k = 3, minLength = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> There exists one way to create a beautiful partition: &quot;331 | 29 | 58&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k, minLength &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists of the digits <code>&#39;1&#39;</code> to <code>&#39;9&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
