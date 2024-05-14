# [276. Paint Fence 🔒](https://leetcode.com/problems/paint-fence)

[中文文档](/solution/0200-0299/0276.Paint%20Fence/README.md)

<!-- tags:Dynamic Programming -->

<!-- difficulty:Medium -->

## Description

<p>You are painting a fence of <code>n</code> posts with <code>k</code> different colors. You must paint the posts following these rules:</p>

<ul>
	<li>Every post must be painted <strong>exactly one</strong> color.</li>
	<li>There <strong>cannot</strong> be three or more <strong>consecutive</strong> posts with the same color.</li>
</ul>

<p>Given the two integers <code>n</code> and <code>k</code>, return <em>the <strong>number of ways</strong> you can paint the fence</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0276.Paint%20Fence/images/paintfenceex1.png" style="width: 507px; height: 313px;" />
<pre>
<strong>Input:</strong> n = 3, k = 2
<strong>Output:</strong> 6
<strong>Explanation: </strong>All the possibilities are shown.
Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, k = 1
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 7, k = 2
<strong>Output:</strong> 42
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li>The testcases are generated such that the answer is in the range <code>[0, 2<sup>31</sup> - 1]</code> for the given <code>n</code> and <code>k</code>.</li>
</ul>

## Solutions

### Solution 1: Dynamic Programming

We define $f[i]$ to represent the number of ways to paint the fence posts from $[0..i]$ such that the last two posts have different colors, and $g[i]$ to represent the number of ways to paint the fence posts from $[0..i]$ such that the last two posts have the same color. Initially, $f[0] = k$ and $g[0] = 0$.

When $i > 0$, we have the following state transition equations:

$$
\begin{aligned}
f[i] & = (f[i - 1] + g[i - 1]) \times (k - 1) \\
g[i] & = f[i - 1]
\end{aligned}
$$

The final answer is $f[n - 1] + g[n - 1]$.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the number of fence posts.

<!-- tabs:start -->

```python
class Solution:
    def numWays(self, n: int, k: int) -> int:
        f = [0] * n
        g = [0] * n
        f[0] = k
        for i in range(1, n):
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1)
            g[i] = f[i - 1]
        return f[-1] + g[-1]
```

```java
class Solution {
    public int numWays(int n, int k) {
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = k;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
            g[i] = f[i - 1];
        }
        return f[n - 1] + g[n - 1];
    }
}
```

```cpp
class Solution {
public:
    int numWays(int n, int k) {
        vector<int> f(n);
        vector<int> g(n);
        f[0] = k;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
            g[i] = f[i - 1];
        }
        return f[n - 1] + g[n - 1];
    }
};
```

```go
func numWays(n int, k int) int {
	f := make([]int, n)
	g := make([]int, n)
	f[0] = k
	for i := 1; i < n; i++ {
		f[i] = (f[i-1] + g[i-1]) * (k - 1)
		g[i] = f[i-1]
	}
	return f[n-1] + g[n-1]
}
```

```ts
function numWays(n: number, k: number): number {
    const f: number[] = Array(n).fill(0);
    const g: number[] = Array(n).fill(0);
    f[0] = k;
    for (let i = 1; i < n; ++i) {
        f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
        g[i] = f[i - 1];
    }
    return f[n - 1] + g[n - 1];
}
```

<!-- tabs:end -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that $f[i]$ and $g[i]$ are only related to $f[i - 1]$ and $g[i - 1]$. Therefore, we can use two variables $f$ and $g$ to record the values of $f[i - 1]$ and $g[i - 1]$ respectively, thus optimizing the space complexity to $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def numWays(self, n: int, k: int) -> int:
        f, g = k, 0
        for _ in range(n - 1):
            ff = (f + g) * (k - 1)
            g = f
            f = ff
        return f + g
```

```java
class Solution {
    public int numWays(int n, int k) {
        int f = k, g = 0;
        for (int i = 1; i < n; ++i) {
            int ff = (f + g) * (k - 1);
            g = f;
            f = ff;
        }
        return f + g;
    }
}
```

```cpp
class Solution {
public:
    int numWays(int n, int k) {
        int f = k, g = 0;
        for (int i = 1; i < n; ++i) {
            int ff = (f + g) * (k - 1);
            g = f;
            f = ff;
        }
        return f + g;
    }
};
```

```go
func numWays(n int, k int) int {
	f, g := k, 0
	for i := 1; i < n; i++ {
		f, g = (f+g)*(k-1), f
	}
	return f + g
}
```

```ts
function numWays(n: number, k: number): number {
    let [f, g] = [k, 0];
    for (let i = 1; i < n; ++i) {
        const ff = (f + g) * (k - 1);
        g = f;
        f = ff;
    }
    return f + g;
}
```

<!-- tabs:end -->

<!-- end -->
