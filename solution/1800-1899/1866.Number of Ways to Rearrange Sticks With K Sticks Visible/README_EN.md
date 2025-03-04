---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1866.Number%20of%20Ways%20to%20Rearrange%20Sticks%20With%20K%20Sticks%20Visible/README_EN.md
rating: 2333
source: Weekly Contest 241 Q4
tags:
    - Math
    - Dynamic Programming
    - Combinatorics
---

<!-- problem:start -->

# [1866. Number of Ways to Rearrange Sticks With K Sticks Visible](https://leetcode.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible)

[中文文档](/solution/1800-1899/1866.Number%20of%20Ways%20to%20Rearrange%20Sticks%20With%20K%20Sticks%20Visible/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> uniquely-sized sticks whose lengths are integers from <code>1</code> to <code>n</code>. You want to arrange the sticks such that <strong>exactly</strong> <code>k</code>&nbsp;sticks are <strong>visible</strong> from the left. A stick&nbsp;is <strong>visible</strong> from the left if there are no <strong>longer</strong>&nbsp;sticks to the <strong>left</strong> of it.</p>

<ul>
	<li>For example, if the sticks are arranged <code>[<u>1</u>,<u>3</u>,2,<u>5</u>,4]</code>, then the sticks with lengths <code>1</code>, <code>3</code>, and <code>5</code> are visible from the left.</li>
</ul>

<p>Given <code>n</code> and <code>k</code>, return <em>the <strong>number</strong> of such arrangements</em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, k = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> [<u>1</u>,<u>3</u>,2], [<u>2</u>,<u>3</u>,1], and [<u>2</u>,1,<u>3</u>] are the only arrangements such that exactly 2 sticks are visible.
The visible sticks are underlined.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 5, k = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong> [<u>1</u>,<u>2</u>,<u>3</u>,<u>4</u>,<u>5</u>] is the only arrangement such that all 5 sticks are visible.
The visible sticks are underlined.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 20, k = 11
<strong>Output:</strong> 647427950
<strong>Explanation:</strong> There are 647427950 (mod 10<sup>9 </sup>+ 7) ways to rearrange the sticks such that exactly 11 sticks are visible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the number of permutations of length $i$ in which exactly $j$ sticks can be seen. Initially, $f[0][0]=1$ and the rest $f[i][j]=0$. The answer is $f[n][k]$.

Consider whether the last stick can be seen. If it can be seen, it must be the longest. Then there are $i - 1$ sticks in front of it, and exactly $j - 1$ sticks can be seen, which is $f[i - 1][j - 1]$. If the last stick cannot be seen, it can be any one except the longest stick. Then there are $i - 1$ sticks in front of it, and exactly $j$ sticks can be seen, which is $f[i - 1][j] \times (i - 1)$.

Therefore, the state transition equation is:

$$
f[i][j] = f[i - 1][j - 1] + f[i - 1][j] \times (i - 1)
$$

The final answer is $f[n][k]$.

The time complexity is $O(n \times k)$, and the space complexity is $O(n \times k)$. Where $n$ and $k$ are the two integers given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeSticks(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                f[i][j] = (f[i - 1][j - 1] + f[i - 1][j] * (i - 1)) % mod
        return f[n][k]
```

#### Java

```java
class Solution {
    public int rearrangeSticks(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (int) ((f[i - 1][j - 1] + f[i - 1][j] * (long) (i - 1)) % mod);
            }
        }
        return f[n][k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rearrangeSticks(int n, int k) {
        const int mod = 1e9 + 7;
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (f[i - 1][j - 1] + (i - 1LL) * f[i - 1][j]) % mod;
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func rearrangeSticks(n int, k int) int {
	const mod = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			f[i][j] = (f[i-1][j-1] + (i-1)*f[i-1][j]) % mod
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function rearrangeSticks(n: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 1 }, () => 0),
    );
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= k; ++j) {
            f[i][j] = (f[i - 1][j - 1] + (i - 1) * f[i - 1][j]) % mod;
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that $f[i][j]$ is only related to $f[i - 1][j - 1]$ and $f[i - 1][j]$, so we can use a one-dimensional array to optimize the space complexity.

The time complexity is $O(n \times k)$, and the space complexity is $O(k)$. Here, $n$ and $k$ are the two integers given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeSticks(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * k
        for i in range(1, n + 1):
            for j in range(k, 0, -1):
                f[j] = (f[j] * (i - 1) + f[j - 1]) % mod
            f[0] = 0
        return f[k]
```

#### Java

```java
class Solution {
    public int rearrangeSticks(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j > 0; --j) {
                f[j] = (int) ((f[j] * (i - 1L) + f[j - 1]) % mod);
            }
            f[0] = 0;
        }
        return f[k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int rearrangeSticks(int n, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j; --j) {
                f[j] = (f[j - 1] + f[j] * (i - 1LL)) % mod;
            }
            f[0] = 0;
        }
        return f[k];
    }
};
```

#### Go

```go
func rearrangeSticks(n int, k int) int {
	const mod = 1e9 + 7
	f := make([]int, k+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		for j := k; j > 0; j-- {
			f[j] = (f[j-1] + f[j]*(i-1)) % mod
		}
		f[0] = 0
	}
	return f[k]
}
```

#### TypeScript

```ts
function rearrangeSticks(n: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = k; j; --j) {
            f[j] = (f[j] * (i - 1) + f[j - 1]) % mod;
        }
        f[0] = 0;
    }
    return f[k];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
