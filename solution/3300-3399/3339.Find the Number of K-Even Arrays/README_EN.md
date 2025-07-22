---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3339.Find%20the%20Number%20of%20K-Even%20Arrays/README_EN.md
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [3339. Find the Number of K-Even Arrays 🔒](https://leetcode.com/problems/find-the-number-of-k-even-arrays)

[中文文档](/solution/3300-3399/3339.Find%20the%20Number%20of%20K-Even%20Arrays/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>m</code>, and <code>k</code>.</p>

<p>An array <code>arr</code> is called <strong>k-even</strong> if there are <strong>exactly</strong> <code>k</code> indices such that, for each of these indices <code>i</code> (<code>0 &lt;= i &lt; n - 1</code>):</p>

<ul>
	<li><code>(arr[i] * arr[i + 1]) - arr[i] - arr[i + 1]</code> is <em>even</em>.</li>
</ul>

<p>Return the number of possible <strong>k-even</strong> arrays of size <code>n</code> where all elements are in the range <code>[1, m]</code>.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, m = 4, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The 8 possible 2-even arrays are:</p>

<ul>
	<li><code>[2, 2, 2]</code></li>
	<li><code>[2, 2, 4]</code></li>
	<li><code>[2, 4, 2]</code></li>
	<li><code>[2, 4, 4]</code></li>
	<li><code>[4, 2, 2]</code></li>
	<li><code>[4, 2, 4]</code></li>
	<li><code>[4, 4, 2]</code></li>
	<li><code>[4, 4, 4]</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, m = 1, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only 0-even array is <code>[1, 1, 1, 1, 1]</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 7, m = 7, k = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">5832</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 750</code></li>
	<li><code>0 &lt;= k &lt;= n - 1</code></li>
	<li><code>1 &lt;= m &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

Given the numbers $[1, m]$, there are $\textit{cnt0} = \lfloor \frac{m}{2} \rfloor$ even numbers and $\textit{cnt1} = m - \textit{cnt0}$ odd numbers.

We design a function $\textit{dfs}(i, j, k)$, which represents the number of ways to fill up to the $i$-th position, with $j$ remaining positions needing to satisfy the condition, and the parity of the last position being $k$, where $k = 0$ indicates the last position is even, and $k = 1$ indicates the last position is odd. The answer is $\textit{dfs}(0, k, 1)$.

The execution logic of the function $\textit{dfs}(i, j, k)$ is as follows:

-   If $j < 0$, it means the remaining positions are less than $0$, so return $0$;
-   If $i \ge n$, it means all positions are filled. If $j = 0$, it means the condition is satisfied, so return $1$, otherwise return $0$;
-   Otherwise, we can choose to fill with an odd or even number, calculate the number of ways for both, and return their sum.

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$. Here, $n$ and $k$ are the parameters given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if j < 0:
                return 0
            if i >= n:
                return int(j == 0)
            return (
                cnt1 * dfs(i + 1, j, 1) + cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0)
            ) % mod

        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        ans = dfs(0, k, 1)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private long cnt0, cnt1;
    private final int mod = (int) 1e9 + 7;

    public int countOfArrays(int n, int m, int k) {
        f = new Integer[n][k + 1][2];
        cnt0 = m / 2;
        cnt1 = m - cnt0;
        return dfs(0, k, 1);
    }

    private int dfs(int i, int j, int k) {
        if (j < 0) {
            return 0;
        }
        if (i >= f.length) {
            return j == 0 ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int a = (int) (cnt1 * dfs(i + 1, j, 1) % mod);
        int b = (int) (cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0) % mod);
        return f[i][j][k] = (a + b) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        int f[n][k + 1][2];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (j < 0) {
                return 0;
            }
            if (i >= n) {
                return j == 0 ? 1 : 0;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int a = 1LL * cnt1 * dfs(i + 1, j, 1) % mod;
            int b = 1LL * cnt0 * dfs(i + 1, j - (k & 1 ^ 1), 0) % mod;
            return f[i][j][k] = (a + b) % mod;
        };
        return dfs(0, k, 1);
    }
};
```

#### Go

```go
func countOfArrays(n int, m int, k int) int {
	f := make([][][2]int, n)
	for i := range f {
		f[i] = make([][2]int, k+1)
		for j := range f[i] {
			f[i][j] = [2]int{-1, -1}
		}
	}
	const mod int = 1e9 + 7
	cnt0 := m / 2
	cnt1 := m - cnt0
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if j < 0 {
			return 0
		}
		if i >= n {
			if j == 0 {
				return 1
			}
			return 0
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		a := cnt1 * dfs(i+1, j, 1) % mod
		b := cnt0 * dfs(i+1, j-(k&1^1), 0) % mod
		f[i][j][k] = (a + b) % mod
		return f[i][j][k]
	}
	return dfs(0, k, 1)
}
```

#### TypeScript

```ts
function countOfArrays(n: number, m: number, k: number): number {
    const f = Array.from({ length: n }, () =>
        Array.from({ length: k + 1 }, () => Array(2).fill(-1)),
    );
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    const dfs = (i: number, j: number, k: number): number => {
        if (j < 0) {
            return 0;
        }
        if (i >= n) {
            return j === 0 ? 1 : 0;
        }
        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }
        const a = (cnt1 * dfs(i + 1, j, 1)) % mod;
        const b = (cnt0 * dfs(i + 1, j - ((k & 1) ^ 1), 0)) % mod;
        return (f[i][j][k] = (a + b) % mod);
    };
    return dfs(0, k, 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can convert the memoized search from Solution 1 into dynamic programming.

Define $f[i][j][k]$ to represent the number of ways to fill the $i$-th position, with $j$ positions satisfying the condition, and the parity of the previous position being $k$. The answer will be $\sum_{k = 0}^{1} f[n][k]$.

Initially, we set $f[0][0][1] = 1$, indicating that after filling the $0$-th position, there are $0$ positions satisfying the condition, and the parity of the previous position is odd. All other $f[i][j][k]$ are initialized to $0$.

The state transition equations are as follows:

$$
\begin{aligned}
f[i][j][0] &= \left( f[i - 1][j][1] + \left( f[i - 1][j - 1][0] \text{ if } j > 0 \right) \right) \times \textit{cnt0} \bmod \textit{mod}, \\
f[i][j][1] &= \left( f[i - 1][j][0] + f[i - 1][j][1] \right) \times \textit{cnt1} \bmod \textit{mod}.
\end{aligned}
$$

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$, where $n$ and $k$ are the parameters given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        f = [[[0] * 2 for _ in range(k + 1)] for _ in range(n + 1)]
        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        f[0][0][1] = 1
        for i in range(1, n + 1):
            for j in range(k + 1):
                f[i][j][0] = (
                    (f[i - 1][j][1] + (f[i - 1][j - 1][0] if j else 0)) * cnt0 % mod
                )
                f[i][j][1] = (f[i - 1][j][0] + f[i - 1][j][1]) * cnt1 % mod
        return sum(f[n][k]) % mod
```

#### Java

```java
class Solution {
    public int countOfArrays(int n, int m, int k) {
        int[][][] f = new int[n + 1][k + 1][2];
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        final int mod = (int) 1e9 + 7;
        f[0][0][1] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j][0]
                    = (int) (1L * cnt0 * (f[i - 1][j][1] + (j > 0 ? f[i - 1][j - 1][0] : 0)) % mod);
                f[i][j][1] = (int) (1L * cnt1 * (f[i - 1][j][0] + f[i - 1][j][1]) % mod);
            }
        }
        return (f[n][k][0] + f[n][k][1]) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        int f[n + 1][k + 1][2];
        memset(f, 0, sizeof(f));
        f[0][0][1] = 1;
        const int mod = 1e9 + 7;
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                f[i][j][0] = 1LL * (f[i - 1][j][1] + (j ? f[i - 1][j - 1][0] : 0)) * cnt0 % mod;
                f[i][j][1] = 1LL * (f[i - 1][j][0] + f[i - 1][j][1]) * cnt1 % mod;
            }
        }
        return (f[n][k][0] + f[n][k][1]) % mod;
    }
};
```

#### Go

```go
func countOfArrays(n int, m int, k int) int {
	f := make([][][2]int, n+1)
	for i := range f {
		f[i] = make([][2]int, k+1)
	}
	f[0][0][1] = 1
	cnt0 := m / 2
	cnt1 := m - cnt0
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 0; j <= k; j++ {
			f[i][j][0] = cnt0 * f[i-1][j][1] % mod
			if j > 0 {
				f[i][j][0] = (f[i][j][0] + cnt0*f[i-1][j-1][0]%mod) % mod
			}
			f[i][j][1] = cnt1 * (f[i-1][j][0] + f[i-1][j][1]) % mod
		}
	}
	return (f[n][k][0] + f[n][k][1]) % mod
}
```

#### TypeScript

```ts
function countOfArrays(n: number, m: number, k: number): number {
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => Array(2).fill(0)),
    );
    f[0][0][1] = 1;
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= k; ++j) {
            f[i][j][0] = (cnt0 * (f[i - 1][j][1] + (j ? f[i - 1][j - 1][0] : 0))) % mod;
            f[i][j][1] = (cnt1 * (f[i - 1][j][0] + f[i - 1][j][1])) % mod;
        }
    }
    return (f[n][k][0] + f[n][k][1]) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 3: Dynamic Programming (Space Optimization)

We observe that the computation of $f[i]$ only depends on $f[i - 1]$, allowing us to optimize the space usage with a rolling array.

The time complexity is $O(n \times k)$, and the space complexity is $O(k)$, where $n$ and $k$ are the parameters given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countOfArrays(self, n: int, m: int, k: int) -> int:
        f = [[0] * 2 for _ in range(k + 1)]
        cnt0 = m // 2
        cnt1 = m - cnt0
        mod = 10**9 + 7
        f[0][1] = 1
        for _ in range(n):
            g = [[0] * 2 for _ in range(k + 1)]
            for j in range(k + 1):
                g[j][0] = (f[j][1] + (f[j - 1][0] if j else 0)) * cnt0 % mod
                g[j][1] = (f[j][0] + f[j][1]) * cnt1 % mod
            f = g
        return sum(f[k]) % mod
```

#### Java

```java
class Solution {
    public int countOfArrays(int n, int m, int k) {
        int[][] f = new int[k + 1][2];
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        final int mod = (int) 1e9 + 7;
        f[0][1] = 1;
        for (int i = 0; i < n; ++i) {
            int[][] g = new int[k + 1][2];
            for (int j = 0; j <= k; ++j) {
                g[j][0] = (int) (1L * cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0)) % mod);
                g[j][1] = (int) (1L * cnt1 * (f[j][0] + f[j][1]) % mod);
            }
            f = g;
        }
        return (f[k][0] + f[k][1]) % mod;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countOfArrays(int n, int m, int k) {
        vector<vector<int>> f(k + 1, vector<int>(2));
        int cnt0 = m / 2;
        int cnt1 = m - cnt0;
        const int mod = 1e9 + 7;
        f[0][1] = 1;

        for (int i = 0; i < n; ++i) {
            vector<vector<int>> g(k + 1, vector<int>(2));
            for (int j = 0; j <= k; ++j) {
                g[j][0] = (1LL * cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0)) % mod) % mod;
                g[j][1] = (1LL * cnt1 * (f[j][0] + f[j][1]) % mod) % mod;
            }
            f = g;
        }
        return (f[k][0] + f[k][1]) % mod;
    }
};
```

#### Go

```go
func countOfArrays(n int, m int, k int) int {
	const mod = 1e9 + 7
	cnt0 := m / 2
	cnt1 := m - cnt0
	f := make([][2]int, k+1)
	f[0][1] = 1

	for i := 0; i < n; i++ {
		g := make([][2]int, k+1)
		for j := 0; j <= k; j++ {
			g[j][0] = (cnt0 * (f[j][1] + func() int {
				if j > 0 {
					return f[j-1][0]
				}
				return 0
			}()) % mod) % mod
			g[j][1] = (cnt1 * (f[j][0] + f[j][1]) % mod) % mod
		}
		f = g
	}

	return (f[k][0] + f[k][1]) % mod
}
```

#### TypeScript

```ts
function countOfArrays(n: number, m: number, k: number): number {
    const mod = 1e9 + 7;
    const cnt0 = Math.floor(m / 2);
    const cnt1 = m - cnt0;
    const f: number[][] = Array.from({ length: k + 1 }, () => [0, 0]);
    f[0][1] = 1;
    for (let i = 0; i < n; i++) {
        const g: number[][] = Array.from({ length: k + 1 }, () => [0, 0]);
        for (let j = 0; j <= k; j++) {
            g[j][0] = ((cnt0 * (f[j][1] + (j > 0 ? f[j - 1][0] : 0))) % mod) % mod;
            g[j][1] = ((cnt1 * (f[j][0] + f[j][1])) % mod) % mod;
        }
        f.splice(0, f.length, ...g);
    }
    return (f[k][0] + f[k][1]) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
