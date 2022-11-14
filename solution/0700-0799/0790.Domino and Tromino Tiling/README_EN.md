# [790. Domino and Tromino Tiling](https://leetcode.com/problems/domino-and-tromino-tiling)

[中文文档](/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/README.md)

## Description

<p>You have two types of tiles: a <code>2 x 1</code> domino shape and a tromino shape. You may rotate these shapes.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/images/lc-domino.jpg" style="width: 362px; height: 195px;" />
<p>Given an integer n, return <em>the number of ways to tile an</em> <code>2 x n</code> <em>board</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/images/lc-domino1.jpg" style="width: 500px; height: 226px;" />
<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> The five different ways are show above.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numTilings(self, n: int) -> int:
        @cache
        def dfs(i, j):
            if i > n or j > n:
                return 0
            if i == n and j == n:
                return 1
            ans = 0
            if i == j:
                ans = dfs(i + 2, j + 2) + dfs(i + 1, j + 1) + dfs(i + 2, j + 1) + dfs(i + 1, j + 2)
            elif i > j:
                ans = dfs(i, j + 2) + dfs(i + 1, j + 2)
            else:
                ans = dfs(i + 2, j) + dfs(i + 2, j + 1)
            return ans % mod

        mod = 10**9 + 7
        return dfs(0, 0)
```

```python
class Solution:
    def numTilings(self, n: int) -> int:
        f = [1, 0, 0, 0]
        mod = 10**9 + 7
        for i in range(1, n + 1):
            g = [0] * 4
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod
            g[1] = (f[2] + f[3]) % mod
            g[2] = (f[1] + f[3]) % mod
            g[3] = f[0]
            f = g
        return f[0]
```

### **Java**

```java
class Solution {
    public int numTilings(int n) {
        long[] f = {1, 0, 0, 0};
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long[] g = new long[4];
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
            g[1] = (f[2] + f[3]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[0];
            f = g;
        }
        return (int) f[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int numTilings(int n) {
        long f[4] = {1, 0, 0, 0};
        for (int i = 1; i <= n; ++i) {
            long g[4] = {0, 0, 0, 0};
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
            g[1] = (f[2] + f[3]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[0];
            memcpy(f, g, sizeof(g));
        }
        return f[0];
    }
};
```

### **Go**

```go
func numTilings(n int) int {
	f := [4]int{}
	f[0] = 1
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		g := [4]int{}
		g[0] = (f[0] + f[1] + f[2] + f[3]) % mod
		g[1] = (f[2] + f[3]) % mod
		g[2] = (f[1] + f[3]) % mod
		g[3] = f[0]
		f = g
	}
	return f[0]
}
```

### **...**

```

```

<!-- tabs:end -->
