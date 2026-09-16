---
comments: true
difficulty: Medium
rating: 2198
source: Biweekly Contest 37 Q3
tags:
    - Math
    - Dynamic Programming
    - Combinatorics
    - Prefix Sum
---

<!-- problem:start -->

# [1621. Number of Sets of K Non-Overlapping Line Segments](https://leetcode.com/problems/number-of-sets-of-k-non-overlapping-line-segments)

[中文文档](/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/README.md)

## Description

<!-- description:start -->

<p>Given <code>n</code> points on a 1-D plane, where the <code>i<sup>th</sup></code> point (from <code>0</code> to <code>n-1</code>) is at <code>x = i</code>, find the number of ways we can draw <strong>exactly</strong> <code>k</code> <strong>non-overlapping</strong> line segments such that each segment covers two or more points. The endpoints of each segment must have <strong>integral coordinates</strong>. The <code>k</code> line segments <strong>do not</strong> have to cover all <code>n</code> points, and they are <strong>allowed</strong> to share endpoints.</p>

<p>Return <em>the number of ways we can draw </em><code>k</code><em> non-overlapping line segments</em><em>.</em> Since this number can be huge, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1621.Number%20of%20Sets%20of%20K%20Non-Overlapping%20Line%20Segments/images/ex1.png" style="width: 179px; height: 222px;" />
<pre>
<strong>Input:</strong> n = 4, k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The two line segments are shown in red and blue.
The image above shows the 5 different ways {(0,2),(2,3)}, {(0,1),(1,3)}, {(0,1),(2,3)}, {(1,2),(2,3)}, {(0,1),(1,2)}.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 ways are {(0,1)}, {(0,2)}, {(1,2)}.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 30, k = 7
<strong>Output:</strong> 796297179
<strong>Explanation:</strong> The total number of possible ways to draw 7 line segments is 3796297200. Taking this number modulo 10<sup>9</sup> + 7 gives us 796297179.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n-1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> We need exactly $k$ non-overlapping segments on $n$ points, and adjacent segments may share an endpoint. Enumerating both endpoints of every segment blows up even for $n,k\le 1000$, and we would still have to keep the segments ordered and disjoint.
>
> Segments lie on a line, so we can process points from left to right and split the state by whether the current point is the right endpoint of some segment. Let $f[i][j]$ be the ways to place $j$ segments on the first $i$ points without ending at $i$, and $g[i][j]$ the ways that do end at $i$.
>
> Transitions then use only the two kinds of state at $i-1$: if we do not end at $i$, we inherit every placement of $j$ segments; if we do, we either extend a segment that already ended at $i-1$, or start a new length-$1$ segment covering $i-1$ and $i$.

<!-- thinking:end -->

Let $f[i][j]$ be the number of ways to build $j$ segments using the first $i$ points such that the last segment does not end at $i$, and let $g[i][j]$ be the number of ways where the last segment does end at $i$. Initially $f[1][0]=1$.

For $f[i][j]$, the $j$-th segment does not end at $i$, so the first $i-1$ points already contain $j$ segments:

$$
f[i][j] = f[i-1][j] + g[i - 1][j]
$$

For $g[i][j]$, the $j$-th segment ends at $i$. There are two sources, which we add together: extend a $j$-th segment that already ended at $i-1$ (length greater than $1$), or start a new segment covering $i-1$ and $i$ after placing $j-1$ segments on the first $i-1$ points (length $1$). When $j=0$ there is no right endpoint, so the second source is omitted. Thus for $j \ge 1$:

$$
g[i][j] = g[i - 1][j] + f[i - 1][j - 1] + g[i - 1][j - 1]
$$

The answer is $f[n][k]+g[n][k]$.

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSets(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (k + 1) for _ in range(n + 1)]
        g = [[0] * (k + 1) for _ in range(n + 1)]
        f[1][0] = 1
        for i in range(2, n + 1):
            for j in range(k + 1):
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod
                g[i][j] = g[i - 1][j]
                if j:
                    g[i][j] += f[i - 1][j - 1] + g[i - 1][j - 1]
                    g[i][j] %= mod
        return (f[n][k] + g[n][k]) % mod
```

#### Java

```java
class Solution {
    public int numberOfSets(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        int[][] g = new int[n + 1][k + 1];
        f[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
                g[i][j] = g[i - 1][j];
                if (j > 0) {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
                }
            }
        }
        return (f[n][k] + g[n][k]) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfSets(int n, int k) {
        const int mod = 1e9 + 7;
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        vector<vector<int>> g(n + 1, vector<int>(k + 1));
        f[1][0] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
                g[i][j] = g[i - 1][j];
                if (j) {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
                }
            }
        }
        return (f[n][k] + g[n][k]) % mod;
    }
};
```

#### Go

```go
func numberOfSets(n int, k int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	g := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
		g[i] = make([]int, k+1)
	}
	f[1][0] = 1
	for i := 2; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j] = (f[i-1][j] + g[i-1][j]) % mod
			g[i][j] = g[i-1][j]
			if j > 0 {
				g[i][j] = (g[i][j] + f[i-1][j-1]) % mod
				g[i][j] = (g[i][j] + g[i-1][j-1]) % mod
			}
		}
	}
	return (f[n][k] + g[n][k]) % mod
}
```

#### TypeScript

```ts
function numberOfSets(n: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    const g: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    f[1][0] = 1;
    for (let i = 2; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j] = (f[i - 1][j] + g[i - 1][j]) % mod;
            g[i][j] = g[i - 1][j];
            if (j) {
                g[i][j] = (g[i][j] + f[i - 1][j - 1]) % mod;
                g[i][j] = (g[i][j] + g[i - 1][j - 1]) % mod;
            }
        }
    }
    return (f[n][k] + g[n][k]) % mod;
}
```

#### Rust

```rust
impl Solution {
    pub fn number_of_sets(n: i32, k: i32) -> i32 {
        const MOD: i64 = 1_000_000_007;
        let n = n as usize;
        let k = k as usize;
        let mut f = vec![vec![0i64; k + 1]; n + 1];
        let mut g = vec![vec![0i64; k + 1]; n + 1];
        f[1][0] = 1;
        for i in 2..=n {
            for j in 0..=k {
                f[i][j] = (f[i - 1][j] + g[i - 1][j]) % MOD;
                g[i][j] = g[i - 1][j];
                if j > 0 {
                    g[i][j] = (g[i][j] + f[i - 1][j - 1]) % MOD;
                    g[i][j] = (g[i][j] + g[i - 1][j - 1]) % MOD;
                }
            }
        }
        ((f[n][k] + g[n][k]) % MOD) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
