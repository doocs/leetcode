---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3336.Find%20the%20Number%20of%20Subsequences%20With%20Equal%20GCD/README_EN.md
rating: 2402
source: Weekly Contest 421 Q3
tags:
    - Array
    - Math
    - Dynamic Programming
    - Number Theory
---

<!-- problem:start -->

# [3336. Find the Number of Subsequences With Equal GCD](https://leetcode.com/problems/find-the-number-of-subsequences-with-equal-gcd)

[中文文档](/solution/3300-3399/3336.Find%20the%20Number%20of%20Subsequences%20With%20Equal%20GCD/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Your task is to find the number of pairs of <strong>non-empty</strong> <span data-keyword="subsequence-array">subsequences</span> <code>(seq1, seq2)</code> of <code>nums</code> that satisfy the following conditions:</p>

<ul>
	<li>The subsequences <code>seq1</code> and <code>seq2</code> are <strong>disjoint</strong>, meaning <strong>no index</strong> of <code>nums</code> is common between them.</li>
	<li>The <span data-keyword="gcd-function">GCD</span> of the elements of <code>seq1</code> is equal to the GCD of the elements of <code>seq2</code>.</li>
</ul>

<p>Return the total number of such pairs.</p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence pairs which have the GCD of their elements equal to 1 are:</p>

<ul>
	<li><code>([<strong><u>1</u></strong>, 2, 3, 4], [1, <strong><u>2</u></strong>, <strong><u>3</u></strong>, 4])</code></li>
	<li><code>([<strong><u>1</u></strong>, 2, 3, 4], [1, <strong><u>2</u></strong>, <strong><u>3</u></strong>, <strong><u>4</u></strong>])</code></li>
	<li><code>([<strong><u>1</u></strong>, 2, 3, 4], [1, 2, <strong><u>3</u></strong>, <strong><u>4</u></strong>])</code></li>
	<li><code>([<strong><u>1</u></strong>, <strong><u>2</u></strong>, 3, 4], [1, 2, <strong><u>3</u></strong>, <strong><u>4</u></strong>])</code></li>
	<li><code>([<strong><u>1</u></strong>, 2, 3, <strong><u>4</u></strong>], [1, <strong><u>2</u></strong>, <strong><u>3</u></strong>, 4])</code></li>
	<li><code>([1, <strong><u>2</u></strong>, <strong><u>3</u></strong>, 4], [<strong><u>1</u></strong>, 2, 3, 4])</code></li>
	<li><code>([1, <strong><u>2</u></strong>, <strong><u>3</u></strong>, 4], [<strong><u>1</u></strong>, 2, 3, <strong><u>4</u></strong>])</code></li>
	<li><code>([1, <strong><u>2</u></strong>, <strong><u>3</u></strong>, <strong><u>4</u></strong>], [<strong><u>1</u></strong>, 2, 3, 4])</code></li>
	<li><code>([1, 2, <strong><u>3</u></strong>, <strong><u>4</u></strong>], [<strong><u>1</u></strong>, 2, 3, 4])</code></li>
	<li><code>([1, 2, <strong><u>3</u></strong>, <strong><u>4</u></strong>], [<strong><u>1</u></strong>, <strong><u>2</u></strong>, 3, 4])</code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,20,30]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The subsequence pairs which have the GCD of their elements equal to 10 are:</p>

<ul>
	<li><code>([<strong><u>10</u></strong>, 20, 30], [10, <strong><u>20</u></strong>, <strong><u>30</u></strong>])</code></li>
	<li><code>([10, <strong><u>20</u></strong>, <strong><u>30</u></strong>], [<strong><u>10</u></strong>, 20, 30])</code></li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">50</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 200</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization

We define a function $\textit{dfs}(i, j, k)$ as the number of ways when considering elements with indices $0 \sim i$, where the current GCD of the first subsequence is $j$ and that of the second subsequence is $k$. By convention, the GCD of an empty subsequence is $0$, and $\gcd(x, 0) = x$.

For the element at index $i$, there are three choices:

1. Skip it: transition to $\textit{dfs}(i - 1, j, k)$;
2. Put it into the first subsequence: transition to $\textit{dfs}(i - 1, \gcd(\textit{nums}[i], j), k)$;
3. Put it into the second subsequence: transition to $\textit{dfs}(i - 1, j, \gcd(\textit{nums}[i], k))$.

Base case: when $i \lt 0$, return $1$ if $j = k$, otherwise return $0$.

The initial call $\textit{dfs}(n - 1, 0, 0)$ counts all pairs with equal GCDs, including the case where both subsequences are empty. Therefore, we subtract $1$ and take the result modulo $10^9 + 7$.

The time complexity is $O(n \times m^2 \times \log m)$, and the space complexity is $O(n \times m^2)$, where $n$ is the length of the array and $m$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsequencePairCount(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i < 0:
                return int(j == k)
            return (
                dfs(i - 1, j, k)
                + dfs(i - 1, gcd(nums[i], j), k)
                + dfs(i - 1, j, gcd(nums[i], k))
            ) % mod

        mod = 10**9 + 7
        return (dfs(len(nums) - 1, 0, 0) - 1) % mod
```

#### Java

```java
class Solution {
    private int[] nums;
    private Integer[][][] f;
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        int m = 0;
        for (int x : nums) {
            if (x > m) m = x;
        }
        this.f = new Integer[n + 1][m + 1][m + 1];
        return (dfs(n, 0, 0) - 1 + MOD) % MOD;
    }

    private int dfs(int i, int j, int k) {
        if (i == 0) {
            return j == k ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int x = nums[i - 1];
        int res = ((dfs(i - 1, j, k) + dfs(i - 1, gcd(x, j), k)) % MOD + dfs(i - 1, j, gcd(x, k)))
            % MOD;
        f[i][j][k] = res;
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int n = nums.size();
        int m = ranges::max(nums);
        vector f(n, vector(m + 1, vector<int>(m + 1, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i < 0) {
                return j == k;
            }
            int& res = f[i][j][k];
            if (res < 0) {
                res = ((dfs(i - 1, j, k)
                           + dfs(i - 1, gcd(nums[i], j), k))
                              % MOD
                          + dfs(i - 1, j, gcd(nums[i], k)))
                    % MOD;
            }
            return res;
        };
        return (dfs(n - 1, 0, 0) - 1 + MOD) % MOD;
    }
};
```

#### Go

```go
func subsequencePairCount(nums []int) int {
	const mod = 1_000_000_007
	n := len(nums)
	m := slices.Max(nums)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, m+1)
		for j := range f[i] {
			f[i][j] = make([]int, m+1)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	var gcd func(int, int) int
	gcd = func(a, b int) int {
		if b == 0 {
			return a
		}
		return gcd(b, a%b)
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i < 0 {
			if j == k {
				return 1
			}
			return 0
		}
		res := &f[i][j][k]
		if *res < 0 {
			x := nums[i]
			*res = ((dfs(i-1, j, k)+
				dfs(i-1, gcd(x, j), k))%mod +
				dfs(i-1, j, gcd(x, k))) % mod
		}
		return *res
	}
	return (dfs(n-1, 0, 0) - 1 + mod) % mod
}
```

#### TypeScript

```ts
function subsequencePairCount(nums: number[]): number {
    const mod = 1_000_000_007;
    const n = nums.length;
    const m = Math.max(...nums);
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: m + 1 }, () => new Array(m + 1).fill(-1)),
    );
    const gcd = (a: number, b: number): number => {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return a;
    };
    const dfs = (i: number, j: number, k: number): number => {
        if (i < 0) {
            return j === k ? 1 : 0;
        }
        let res = f[i][j][k];
        if (res < 0) {
            const x = nums[i];
            res =
                (((dfs(i - 1, j, k) + dfs(i - 1, gcd(x, j), k)) % mod) + dfs(i - 1, j, gcd(x, k))) %
                mod;
            f[i][j][k] = res;
        }
        return res;
    };
    return (((dfs(n - 1, 0, 0) - 1) % mod) + mod) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can convert the memoization in Solution 1 into an iterative DP.

Define $f[j][k]$ as the number of ways after processing the current elements such that the GCD of the first subsequence is $j$ and that of the second is $k$. Initially, $f[0][0] = 1$.

For each element $x$, transfer into a new array $g$:

$$
\begin{aligned}
g[j][k] &\mathrel{+}= f[j][k], \\
g[\gcd(j, x)][k] &\mathrel{+}= f[j][k], \\
g[j][\gcd(k, x)] &\mathrel{+}= f[j][k].
\end{aligned}
$$

These correspond to skipping $x$, putting $x$ into the first subsequence, and putting $x$ into the second subsequence, respectively. After processing all elements, the answer is $\sum_{i=0}^{m} f[i][i] - 1$, taken modulo $10^9 + 7$.

The time complexity is $O(n \times m^2 \times \log m)$, and the space complexity is $O(m^2)$, where $n$ is the length of the array and $m$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsequencePairCount(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        m = max(nums)
        f = [[0] * (m + 1) for _ in range(m + 1)]
        f[0][0] = 1
        for x in nums:
            g = [[0] * (m + 1) for _ in range(m + 1)]
            for j in range(m + 1):
                for k in range(m + 1):
                    if f[j][k] == 0:
                        continue
                    v = f[j][k]
                    g[j][k] = (g[j][k] + v) % mod
                    gj, gk = gcd(j, x), gcd(k, x)
                    g[gj][k] = (g[gj][k] + v) % mod
                    g[j][gk] = (g[j][gk] + v) % mod
            f = g
        return (sum(f[i][i] for i in range(m + 1)) - 1) % mod
```

#### Java

```java
class Solution {
    public int subsequencePairCount(int[] nums) {
        final int MOD = 1_000_000_007;
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[][] f = new int[m + 1][m + 1];
        f[0][0] = 1;
        for (int x : nums) {
            int[][] g = new int[m + 1][m + 1];
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (f[j][k] == 0) {
                        continue;
                    }
                    int v = f[j][k];
                    g[j][k] = (g[j][k] + v) % MOD;
                    int gj = gcd(j, x), gk = gcd(k, x);
                    g[gj][k] = (g[gj][k] + v) % MOD;
                    g[j][gk] = (g[j][gk] + v) % MOD;
                }
            }
            f = g;
        }
        long ans = 0;
        for (int i = 0; i <= m; ++i) {
            ans += f[i][i];
        }
        return (int) ((ans - 1 + MOD) % MOD);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int m = ranges::max(nums);
        vector f(m + 1, vector<int>(m + 1));
        f[0][0] = 1;
        for (int x : nums) {
            vector g(m + 1, vector<int>(m + 1));
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (f[j][k] == 0) {
                        continue;
                    }
                    int v = f[j][k];
                    g[j][k] = (g[j][k] + v) % MOD;
                    int gj = gcd(j, x), gk = gcd(k, x);
                    g[gj][k] = (g[gj][k] + v) % MOD;
                    g[j][gk] = (g[j][gk] + v) % MOD;
                }
            }
            f.swap(g);
        }
        long long ans = 0;
        for (int i = 0; i <= m; ++i) {
            ans += f[i][i];
        }
        return (ans - 1 + MOD) % MOD;
    }
};
```

#### Go

```go
func subsequencePairCount(nums []int) int {
	const mod = 1_000_000_007
	m := slices.Max(nums)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	f[0][0] = 1
	gcd := func(a, b int) int {
		for b != 0 {
			a, b = b, a%b
		}
		return a
	}
	for _, x := range nums {
		g := make([][]int, m+1)
		for i := range g {
			g[i] = make([]int, m+1)
		}
		for j := 0; j <= m; j++ {
			for k := 0; k <= m; k++ {
				if f[j][k] == 0 {
					continue
				}
				v := f[j][k]
				g[j][k] = (g[j][k] + v) % mod
				gj, gk := gcd(j, x), gcd(k, x)
				g[gj][k] = (g[gj][k] + v) % mod
				g[j][gk] = (g[j][gk] + v) % mod
			}
		}
		f = g
	}
	ans := 0
	for i := 0; i <= m; i++ {
		ans += f[i][i]
	}
	return (ans - 1 + mod) % mod
}
```

#### TypeScript

```ts
function subsequencePairCount(nums: number[]): number {
    const mod = 1_000_000_007;
    const m = Math.max(...nums);
    let f: number[][] = Array.from({ length: m + 1 }, () => Array(m + 1).fill(0));
    f[0][0] = 1;
    const gcd = (a: number, b: number): number => {
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return a;
    };
    for (const x of nums) {
        const g: number[][] = Array.from({ length: m + 1 }, () => Array(m + 1).fill(0));
        for (let j = 0; j <= m; ++j) {
            for (let k = 0; k <= m; ++k) {
                if (f[j][k] === 0) {
                    continue;
                }
                const v = f[j][k];
                g[j][k] = (g[j][k] + v) % mod;
                const gj = gcd(j, x);
                const gk = gcd(k, x);
                g[gj][k] = (g[gj][k] + v) % mod;
                g[j][gk] = (g[j][gk] + v) % mod;
            }
        }
        f = g;
    }
    let ans = 0;
    for (let i = 0; i <= m; ++i) {
        ans += f[i][i];
    }
    return (((ans - 1) % mod) + mod) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can convert the memoization in Solution 1 into iterative dynamic programming.

Define $f[j][k]$ as the number of ways where the current GCD of the first subsequence is $j$ and that of the second subsequence is $k$. Initially, $f[0][0] = 1$.

Enumerate each element $x$ in the array in order, and transfer using a new array $g$:

- Skip: $g[j][k] \mathrel{+}= f[j][k]$;
- Put into the first subsequence: $g[\gcd(x, j)][k] \mathrel{+}= f[j][k]$;
- Put into the second subsequence: $g[j][\gcd(x, k)] \mathrel{+}= f[j][k]$.

After processing all elements, the answer is $\sum_{i = 0}^{m} f[i][i] - 1$, taken modulo $10^9 + 7$.

The time complexity is $O(n \times m^2 \times \log m)$, and the space complexity is $O(m^2)$, where $n$ is the length of the array and $m$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subsequencePairCount(self, nums: List[int]) -> int:
        mod = 10**9 + 7
        m = max(nums)
        f = [[0] * (m + 1) for _ in range(m + 1)]
        f[0][0] = 1
        for x in nums:
            g = [[0] * (m + 1) for _ in range(m + 1)]
            for j in range(m + 1):
                for k in range(m + 1):
                    if f[j][k] == 0:
                        continue
                    v = f[j][k]
                    g[j][k] = (g[j][k] + v) % mod
                    g[gcd(x, j)][k] = (g[gcd(x, j)][k] + v) % mod
                    g[j][gcd(x, k)] = (g[j][gcd(x, k)] + v) % mod
            f = g
        return (sum(f[i][i] for i in range(m + 1)) - 1) % mod
```

#### Java

```java
class Solution {
    public int subsequencePairCount(int[] nums) {
        final int MOD = 1_000_000_007;
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[][] f = new int[m + 1][m + 1];
        f[0][0] = 1;
        for (int x : nums) {
            int[][] g = new int[m + 1][m + 1];
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (f[j][k] == 0) {
                        continue;
                    }
                    int v = f[j][k];
                    g[j][k] = (g[j][k] + v) % MOD;
                    int nj = gcd(x, j);
                    g[nj][k] = (g[nj][k] + v) % MOD;
                    int nk = gcd(x, k);
                    g[j][nk] = (g[j][nk] + v) % MOD;
                }
            }
            f = g;
        }
        long ans = 0;
        for (int i = 0; i <= m; ++i) {
            ans += f[i][i];
        }
        return (int) ((ans - 1 + MOD) % MOD);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subsequencePairCount(vector<int>& nums) {
        const int MOD = 1e9 + 7;
        int m = ranges::max(nums);
        vector f(m + 1, vector<int>(m + 1));
        f[0][0] = 1;
        for (int x : nums) {
            vector g(m + 1, vector<int>(m + 1));
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= m; ++k) {
                    if (f[j][k] == 0) {
                        continue;
                    }
                    int v = f[j][k];
                    g[j][k] = (g[j][k] + v) % MOD;
                    g[gcd(x, j)][k] = (g[gcd(x, j)][k] + v) % MOD;
                    g[j][gcd(x, k)] = (g[j][gcd(x, k)] + v) % MOD;
                }
            }
            f.swap(g);
        }
        long long ans = 0;
        for (int i = 0; i <= m; ++i) {
            ans += f[i][i];
        }
        return (ans - 1 + MOD) % MOD;
    }
};
```

#### Go

```go
func subsequencePairCount(nums []int) int {
	const mod = 1_000_000_007
	m := slices.Max(nums)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	f[0][0] = 1
	gcd := func(a, b int) int {
		for b != 0 {
			a, b = b, a%b
		}
		return a
	}
	for _, x := range nums {
		g := make([][]int, m+1)
		for i := range g {
			g[i] = make([]int, m+1)
		}
		for j := 0; j <= m; j++ {
			for k := 0; k <= m; k++ {
				if f[j][k] == 0 {
					continue
				}
				v := f[j][k]
				g[j][k] = (g[j][k] + v) % mod
				nj := gcd(x, j)
				g[nj][k] = (g[nj][k] + v) % mod
				nk := gcd(x, k)
				g[j][nk] = (g[j][nk] + v) % mod
			}
		}
		f = g
	}
	ans := 0
	for i := 0; i <= m; i++ {
		ans += f[i][i]
	}
	return (ans - 1 + mod) % mod
}
```

#### TypeScript

```ts
function subsequencePairCount(nums: number[]): number {
    const mod = 1_000_000_007;
    const m = Math.max(...nums);
    let f: number[][] = Array.from({ length: m + 1 }, () => Array(m + 1).fill(0));
    f[0][0] = 1;
    const gcd = (a: number, b: number): number => {
        while (b !== 0) {
            [a, b] = [b, a % b];
        }
        return a;
    };
    for (const x of nums) {
        const g: number[][] = Array.from({ length: m + 1 }, () => Array(m + 1).fill(0));
        for (let j = 0; j <= m; ++j) {
            for (let k = 0; k <= m; ++k) {
                if (f[j][k] === 0) {
                    continue;
                }
                const v = f[j][k];
                g[j][k] = (g[j][k] + v) % mod;
                const nj = gcd(x, j);
                g[nj][k] = (g[nj][k] + v) % mod;
                const nk = gcd(x, k);
                g[j][nk] = (g[j][nk] + v) % mod;
            }
        }
        f = g;
    }
    let ans = 0;
    for (let i = 0; i <= m; ++i) {
        ans += f[i][i];
    }
    return (((ans - 1) % mod) + mod) % mod;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
