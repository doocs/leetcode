---
comments: true
difficulty: Medium
rating: 1692
source: Weekly Contest 415 Q2
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3290. Maximum Multiplication Score](https://leetcode.com/problems/maximum-multiplication-score)

[中文文档](/solution/3200-3299/3290.Maximum%20Multiplication%20Score/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>a</code> of size 4 and another integer array <code>b</code> of size <strong>at least</strong> 4.</p>

<p>You need to choose 4 indices <code>i<sub>0</sub></code>, <code>i<sub>1</sub></code>, <code>i<sub>2</sub></code>, and <code>i<sub>3</sub></code> from the array <code>b</code> such that <code>i<sub>0</sub> &lt; i<sub>1</sub> &lt; i<sub>2</sub> &lt; i<sub>3</sub></code>. Your score will be equal to the value <code>a[0] * b[i<sub>0</sub>] + a[1] * b[i<sub>1</sub>] + a[2] * b[i<sub>2</sub>] + a[3] * b[i<sub>3</sub>]</code>.</p>

<p>Return the <strong>maximum</strong> score you can achieve.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = [3,2,5,6], b = [2,-6,4,-5,-3,2,-7]</span></p>

<p><strong>Output:</strong> <span class="example-io">26</span></p>

<p><strong>Explanation:</strong><br />
We can choose the indices 0, 1, 2, and 5. The score will be <code>3 * 2 + 2 * (-6) + 5 * 4 + 6 * 2 = 26</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">a = [-1,4,5,-2], b = [-5,-1,-3,-2,-4]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong><br />
We can choose the indices 0, 1, 3, and 4. The score will be <code>(-1) * (-5) + 4 * (-1) + 5 * (-2) + (-2) * (-4) = -1</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>a.length == 4</code></li>
	<li><code>4 &lt;= b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= a[i], b[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization

<!-- thinking:start -->

> **Thinking**
>
> $a$ has four entries; we pick four increasing indices in $b$ and maximize the dot product. $|b|\le 10^5$ forbids $O(n^4)$ index tuples. There are only four stages, so we DP on the position in $b$.
>
> $\textit{dfs}(i,j)$: $i$ of $a$ used, at $b[j]$. Skip $b[j]$, or take $a[i]\times b[j]$ and advance both. Memoized states are $4\times n$.

<!-- thinking:end -->

We design a function $\textit{dfs}(i, j)$, which represents the maximum score that can be obtained starting from the $i$-th element of array $a$ and the $j$-th element of array $b$. Then the answer is $\textit{dfs}(0, 0)$.

The function $\textit{dfs}(i, j)$ is calculated as follows:

- If $j \geq \text{len}(b)$, it means array $b$ has been completely traversed. At this point, if array $a$ has also been completely traversed, return $0$; otherwise, return negative infinity.
- If $i \geq \text{len}(a)$, it means array $a$ has been completely traversed. Return $0$.
- Otherwise, we can either skip the $j$-th element of array $b$ and move to the next element, i.e., $\textit{dfs}(i, j + 1)$; or we can choose the $j$-th element of array $b$, in which case the score is $a[i] \times b[j]$ plus $\textit{dfs}(i + 1, j + 1)$. We take the maximum of these two values as the return value of $\textit{dfs}(i, j)$.

We can use memoization to avoid redundant calculations.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of arrays $a$ and $b$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, a: List[int], b: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if j >= len(b):
                return 0 if i >= len(a) else -inf
            if i >= len(a):
                return 0
            return max(dfs(i, j + 1), a[i] * b[j] + dfs(i + 1, j + 1))

        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Long[][] f;
    private int[] a;
    private int[] b;

    public long maxScore(int[] a, int[] b) {
        f = new Long[a.length][b.length];
        this.a = a;
        this.b = b;
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (j >= b.length) {
            return i >= a.length ? 0 : Long.MIN_VALUE / 2;
        }
        if (i >= a.length) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        return f[i][j] = Math.max(dfs(i, j + 1), 1L * a[i] * b[j] + dfs(i + 1, j + 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& a, vector<int>& b) {
        int m = a.size(), n = b.size();
        long long f[m][n];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int j) -> long long {
            if (j >= n) {
                return i >= m ? 0 : LLONG_MIN / 2;
            }
            if (i >= m) {
                return 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            return f[i][j] = max(dfs(i, j + 1), 1LL * a[i] * b[j] + dfs(i + 1, j + 1));
        };
        return dfs(0, 0);
    }
};
```

#### Go

```go
func maxScore(a []int, b []int) int64 {
	m, n := len(a), len(b)
	f := make([][]int64, m)
	for i := range f {
		f[i] = make([]int64, n)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int64
	dfs = func(i, j int) int64 {
		if j >= n {
			if i >= m {
				return 0
			}
			return math.MinInt64 / 2
		}
		if i >= m {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = max(dfs(i, j+1), int64(a[i])*int64(b[j])+dfs(i+1, j+1))
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function maxScore(a: number[], b: number[]): number {
    const [m, n] = [a.length, b.length];
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (j >= n) {
            return i >= m ? 0 : -Infinity;
        }
        if (i >= m) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        return (f[i][j] = Math.max(dfs(i, j + 1), a[i] * b[j] + dfs(i + 1, j + 1)));
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> $a$ has four entries; we pick four increasing indices in $b$ and maximize the dot product. $|b|\le 10^5$ forbids $O(n^4)$ index tuples. There are only four stages, so the position in $b$ is the other axis.
>
> From stage $i$ at $b[j]$, the next choice is either $b[j+1]$ with the same stage, or $b[j+1]$ at stage $i+1$ after adding $a[i]\times b[j]$. A search of that recurrence still calls $j+1$ before it returns, so the chain has depth $n$ and overflows the stack.
>
> Column $j+1$ is known if we walk from the end of $b$. Let $f[i][j]$ be the best score, with $f[i][n]$ equal to $0$ once every entry of $a$ is used and negative infinity otherwise.
>
> Fill $j$ from $n-1$ down to $0$ and return $f[0][0]$.

<!-- thinking:end -->

Let $f[i][j]$ be the maximum score starting from the $i$-th element of array $a$ and the $j$-th element of array $b$. The answer is $f[0][0]$.

If $j = n$, array $b$ is finished. The value is $0$ when array $a$ is also finished, and negative infinity otherwise. If $i = m$, array $a$ is finished, so the remaining suffix of $b$ scores $0$.

Otherwise we either skip $b[j]$, which scores $f[i][j + 1]$, or take it, which scores $a[i] \times b[j] + f[i + 1][j + 1]$. $f[i][j]$ is the larger of the two.

We calculate $f$ from $j = n - 1$ down to $0$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of arrays $a$ and $b$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScore(self, a: List[int], b: List[int]) -> int:
        m, n = len(a), len(b)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(m):
            f[i][n] = -inf
        for j in range(n - 1, -1, -1):
            for i in range(m - 1, -1, -1):
                f[i][j] = max(f[i][j + 1], a[i] * b[j] + f[i + 1][j + 1])
        return f[0][0]
```

#### Java

```java
class Solution {
    public long maxScore(int[] a, int[] b) {
        int m = a.length, n = b.length;
        long[][] f = new long[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            f[i][n] = Long.MIN_VALUE / 2;
        }
        for (int j = n - 1; j >= 0; --j) {
            for (int i = m - 1; i >= 0; --i) {
                f[i][j] = Math.max(f[i][j + 1], 1L * a[i] * b[j] + f[i + 1][j + 1]);
            }
        }
        return f[0][0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxScore(vector<int>& a, vector<int>& b) {
        int m = a.size(), n = b.size();
        vector<vector<long long>> f(m + 1, vector<long long>(n + 1));
        for (int i = 0; i < m; ++i) {
            f[i][n] = LLONG_MIN / 2;
        }
        for (int j = n - 1; j >= 0; --j) {
            for (int i = m - 1; i >= 0; --i) {
                f[i][j] = max(f[i][j + 1], 1LL * a[i] * b[j] + f[i + 1][j + 1]);
            }
        }
        return f[0][0];
    }
};
```

#### Go

```go
func maxScore(a []int, b []int) int64 {
	m, n := len(a), len(b)
	f := make([][]int64, m+1)
	for i := range f {
		f[i] = make([]int64, n+1)
	}
	for i := 0; i < m; i++ {
		f[i][n] = math.MinInt64 / 2
	}
	for j := n - 1; j >= 0; j-- {
		for i := m - 1; i >= 0; i-- {
			f[i][j] = max(f[i][j+1], int64(a[i])*int64(b[j])+f[i+1][j+1])
		}
	}
	return f[0][0]
}
```

#### TypeScript

```ts
function maxScore(a: number[], b: number[]): number {
    const m = a.length;
    const n = b.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        f[i][n] = -Infinity;
    }
    for (let j = n - 1; j >= 0; --j) {
        for (let i = m - 1; i >= 0; --i) {
            f[i][j] = Math.max(f[i][j + 1], a[i] * b[j] + f[i + 1][j + 1]);
        }
    }
    return f[0][0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
