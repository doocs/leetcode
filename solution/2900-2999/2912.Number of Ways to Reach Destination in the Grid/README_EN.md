# [2912. Number of Ways to Reach Destination in the Grid](https://leetcode.com/problems/number-of-ways-to-reach-destination-in-the-grid)

[中文文档](/solution/2900-2999/2912.Number%20of%20Ways%20to%20Reach%20Destination%20in%20the%20Grid/README.md)

<!-- tags:Math,Dynamic Programming,Combinatorics -->

## Description

<p>You are given two integers <code>n</code> and <code>m</code> which represent the size of a <strong>1-indexed </strong>grid. You are also given an integer <code>k</code>, a <strong>1-indexed</strong> integer array <code>source</code> and a <strong>1-indexed</strong> integer array <code>dest</code>, where <code>source</code> and <code>dest</code> are in the form <code>[x, y]</code> representing a cell on the given grid.</p>

<p>You can move through the grid in the following way:</p>

<ul>
	<li>You can go from cell <code>[x<sub>1</sub>, y<sub>1</sub>]</code> to cell <code>[x<sub>2</sub>, y<sub>2</sub>]</code> if either <code>x<sub>1</sub> == x<sub>2</sub></code> or <code>y<sub>1</sub> == y<sub>2</sub></code>.</li>
	<li>Note that you <strong>can&#39;t</strong> move to the cell you are already in e.g. <code>x<sub>1</sub> == x<sub>2</sub></code> and <code>y<sub>1</sub> == y<sub>2</sub></code>.</li>
</ul>

<p>Return <em>the number of ways you can reach</em> <code>dest</code> <em>from</em> <code>source</code> <em>by moving through the grid</em> <strong>exactly</strong> <code>k</code> <em>times.</em></p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, m = 2, k = 2, source = [1,1], dest = [2,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 possible sequences of reaching [2,2] from [1,1]:
- [1,1] -&gt; [1,2] -&gt; [2,2]
- [1,1] -&gt; [2,1] -&gt; [2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, m = 4, k = 3, source = [1,2], dest = [2,3]
<strong>Output:</strong> 9
<strong>Explanation:</strong> There are 9 possible sequences of reaching [2,3] from [1,2]:
- [1,2] -&gt; [1,1] -&gt; [1,3] -&gt; [2,3]
- [1,2] -&gt; [1,1] -&gt; [2,1] -&gt; [2,3]
- [1,2] -&gt; [1,3] -&gt; [3,3] -&gt; [2,3]
- [1,2] -&gt; [1,4] -&gt; [1,3] -&gt; [2,3]
- [1,2] -&gt; [1,4] -&gt; [2,4] -&gt; [2,3]
- [1,2] -&gt; [2,2] -&gt; [2,1] -&gt; [2,3]
- [1,2] -&gt; [2,2] -&gt; [2,4] -&gt; [2,3]
- [1,2] -&gt; [3,2] -&gt; [2,2] -&gt; [2,3]
- [1,2] -&gt; [3,2] -&gt; [3,3] -&gt; [2,3]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n, m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>source.length == dest.length == 2</code></li>
	<li><code>1 &lt;= source[1], dest[1] &lt;= n</code></li>
	<li><code>1 &lt;= source[2], dest[2] &lt;= m</code></li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define the following states:

-   $f[0]$ represents the number of ways to move from `source` to `source` itself;
-   $f[1]$ represents the number of ways to move from `source` to another row in the same column;
-   $f[2]$ represents the number of ways to move from `source` to another column in the same row;
-   $f[3]$ represents the number of ways to move from `source` to another row and another column.

Initially, $f[0] = 1$, and the other states are all $0$.

For each state, we can calculate the current state based on the previous state, as follows:

$$
\begin{aligned}
g[0] &= (n - 1) \times f[1] + (m - 1) \times f[2] \\
g[1] &= f[0] + (n - 2) \times f[1] + (m - 1) \times f[3] \\
g[2] &= f[0] + (m - 2) \times f[2] + (n - 1) \times f[3] \\
g[3] &= f[1] + f[2] + (n - 2) \times f[3] + (m - 2) \times f[3]
\end{aligned}
$$

We loop $k$ times, and finally check whether `source` and `dest` are in the same row or column, and return the corresponding state.

The time complexity is $O(k)$, where $k$ is the number of moves. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numberOfWays(
        self, n: int, m: int, k: int, source: List[int], dest: List[int]
    ) -> int:
        mod = 10**9 + 7
        a, b, c, d = 1, 0, 0, 0
        for _ in range(k):
            aa = ((n - 1) * b + (m - 1) * c) % mod
            bb = (a + (n - 2) * b + (m - 1) * d) % mod
            cc = (a + (m - 2) * c + (n - 1) * d) % mod
            dd = (b + c + (n - 2) * d + (m - 2) * d) % mod
            a, b, c, d = aa, bb, cc, dd
        if source[0] == dest[0]:
            return a if source[1] == dest[1] else c
        return b if source[1] == dest[1] else d
```

```python
class Solution:
    def numberOfWays(
        self, n: int, m: int, k: int, source: List[int], dest: List[int]
    ) -> int:
        mod = 10**9 + 7
        f = [1, 0, 0, 0]
        for _ in range(k):
            g = [0] * 4
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod
            f = g
        if source[0] == dest[0]:
            return f[0] if source[1] == dest[1] else f[2]
        return f[1] if source[1] == dest[1] else f[3]
```

```java
class Solution {
    public int numberOfWays(int n, int m, int k, int[] source, int[] dest) {
        final int mod = 1000000007;
        long[] f = new long[4];
        f[0] = 1;
        while (k-- > 0) {
            long[] g = new long[4];
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod;
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod;
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod;
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod;
            f = g;
        }
        if (source[0] == dest[0]) {
            return source[1] == dest[1] ? (int) f[0] : (int) f[2];
        }
        return source[1] == dest[1] ? (int) f[1] : (int) f[3];
    }
}
```

```cpp
class Solution {
public:
    int numberOfWays(int n, int m, int k, vector<int>& source, vector<int>& dest) {
        const int mod = 1e9 + 7;
        vector<long long> f(4);
        f[0] = 1;
        while (k--) {
            vector<long long> g(4);
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod;
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod;
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod;
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod;
            f = move(g);
        }
        if (source[0] == dest[0]) {
            return source[1] == dest[1] ? f[0] : f[2];
        }
        return source[1] == dest[1] ? f[1] : f[3];
    }
};
```

```go
func numberOfWays(n int, m int, k int, source []int, dest []int) int {
	const mod int = 1e9 + 7
	f := []int{1, 0, 0, 0}
	for i := 0; i < k; i++ {
		g := make([]int, 4)
		g[0] = ((n-1)*f[1] + (m-1)*f[2]) % mod
		g[1] = (f[0] + (n-2)*f[1] + (m-1)*f[3]) % mod
		g[2] = (f[0] + (m-2)*f[2] + (n-1)*f[3]) % mod
		g[3] = (f[1] + f[2] + (n-2)*f[3] + (m-2)*f[3]) % mod
		f = g
	}

	if source[0] == dest[0] {
		if source[1] == dest[1] {
			return f[0]
		}
		return f[2]
	}

	if source[1] == dest[1] {
		return f[1]
	}
	return f[3]
}
```

<!-- tabs:end -->

<!-- end -->
