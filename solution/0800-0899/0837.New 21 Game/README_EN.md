---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0837.New%2021%20Game/README_EN.md
tags:
    - Math
    - Dynamic Programming
    - Sliding Window
    - Probability and Statistics
---

<!-- problem:start -->

# [837. New 21 Game](https://leetcode.com/problems/new-21-game)

[中文文档](/solution/0800-0899/0837.New%2021%20Game/README.md)

## Description

<!-- description:start -->

<p>Alice plays the following game, loosely based on the card game <strong>&quot;21&quot;</strong>.</p>

<p>Alice starts with <code>0</code> points and draws numbers while she has less than <code>k</code> points. During each draw, she gains an integer number of points randomly from the range <code>[1, maxPts]</code>, where <code>maxPts</code> is an integer. Each draw is independent and the outcomes have equal probabilities.</p>

<p>Alice stops drawing numbers when she gets <code>k</code> <strong>or more points</strong>.</p>

<p>Return the probability that Alice has <code>n</code> or fewer points.</p>

<p>Answers within <code>10<sup>-5</sup></code> of the actual answer are considered accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10, k = 1, maxPts = 10
<strong>Output:</strong> 1.00000
<strong>Explanation:</strong> Alice gets a single card, then stops.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 6, k = 1, maxPts = 10
<strong>Output:</strong> 0.60000
<strong>Explanation:</strong> Alice gets a single card, then stops.
In 6 out of 10 possibilities, she is at or below 6 points.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 21, k = 17, maxPts = 10
<strong>Output:</strong> 0.73278
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxPts &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

We design a function $dfs(i)$, which represents the probability that when the current score is $i$, the final score does not exceed $n$ when we stop drawing numbers. The answer is $dfs(0)$.

The calculation method of function $dfs(i)$ is as follows:

-   If $i \ge k$, then we stop drawing numbers. If $i \le n$, return $1$, otherwise return $0$;
-   Otherwise, we can draw the next number $j$ in the range $[1,..\textit{maxPts}]$, then $dfs(i) = \frac{1}{maxPts} \sum_{j=1}^{maxPts} dfs(i+j)$.

Here we can use memoized search to accelerate the calculation.

The time complexity of the above method is $O(k \times \textit{maxPts})$, which will exceed the time limit, so we need to optimize it.

When $i \lt k$, the following equation holds:

$$
\begin{aligned}
dfs(i) &= (dfs(i + 1) + dfs(i + 2) + \cdots + dfs(i + \textit{maxPts})) / \textit{maxPts} & (1)
\end{aligned}
$$

When $i \lt k - 1$, the following equation holds:

$$
\begin{aligned}
dfs(i+1) &= (dfs(i + 2) + dfs(i + 3) + \cdots + dfs(i + \textit{maxPts} + 1)) / \textit{maxPts} & (2)
\end{aligned}
$$

Therefore, when $i \lt k-1$, we subtract equation $(2)$ from equation $(1)$ to get:

$$
\begin{aligned}
dfs(i) - dfs(i+1) &= (dfs(i + 1) - dfs(i + \textit{maxPts} + 1)) / \textit{maxPts}
\end{aligned}
$$

That is:

$$
\begin{aligned}
dfs(i) &= dfs(i + 1) + (dfs(i + 1) - dfs(i + \textit{maxPts} + 1)) / \textit{maxPts}
\end{aligned}
$$

If $i=k-1$, we have:

$$
\begin{aligned}
dfs(i) &= dfs(k - 1) = (dfs(k) + dfs(k + 1) + \cdots + dfs(k + \textit{maxPts} - 1)) / \textit{maxPts} & (3)
\end{aligned}
$$

We assume there are $i$ numbers not exceeding $n$, then $k+i-1 \leq n$, and since $i\leq \textit{maxPts}$, we have $i \leq \min(n-k+1, \textit{maxPts})$, so equation $(3)$ can be written as:

$$
\begin{aligned}
dfs(k-1) &= \min(n-k+1, \textit{maxPts}) / \textit{maxPts}
\end{aligned}
$$

In summary, we have the following state transition equation:

$$
\begin{aligned}
dfs(i) &= \begin{cases}
1, & i \geq k, i \leq n \\
0, & i \geq k, i \gt n \\
\min(n-k+1, \textit{maxPts}) / \textit{maxPts}, & i = k - 1 \\
dfs(i + 1) + (dfs(i + 1) - dfs(i + \textit{maxPts} + 1)) / \textit{maxPts}, & i < k - 1
\end{cases}
\end{aligned}
$$

Time complexity $O(k + \textit{maxPts})$, space complexity $O(k + \textit{maxPts})$. Where $k$ is the maximum score.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def new21Game(self, n: int, k: int, maxPts: int) -> float:
        @cache
        def dfs(i: int) -> float:
            if i >= k:
                return int(i <= n)
            if i == k - 1:
                return min(n - k + 1, maxPts) / maxPts
            return dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts

        return dfs(0)
```

#### Java

```java
class Solution {
    private double[] f;
    private int n, k, maxPts;

    public double new21Game(int n, int k, int maxPts) {
        f = new double[k];
        this.n = n;
        this.k = k;
        this.maxPts = maxPts;
        return dfs(0);
    }

    private double dfs(int i) {
        if (i >= k) {
            return i <= n ? 1 : 0;
        }
        if (i == k - 1) {
            return Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        }
        if (f[i] != 0) {
            return f[i];
        }
        return f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double new21Game(int n, int k, int maxPts) {
        vector<double> f(k);
        auto dfs = [&](this auto&& dfs, int i) -> double {
            if (i >= k) {
                return i <= n ? 1 : 0;
            }
            if (i == k - 1) {
                return min(n - k + 1, maxPts) * 1.0 / maxPts;
            }
            if (f[i]) {
                return f[i];
            }
            return f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func new21Game(n int, k int, maxPts int) float64 {
	f := make([]float64, k)
	var dfs func(int) float64
	dfs = func(i int) float64 {
		if i >= k {
			if i <= n {
				return 1
			}
			return 0
		}
		if i == k-1 {
			return float64(min(n-k+1, maxPts)) / float64(maxPts)
		}
		if f[i] > 0 {
			return f[i]
		}
		f[i] = dfs(i+1) + (dfs(i+1)-dfs(i+maxPts+1))/float64(maxPts)
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function new21Game(n: number, k: number, maxPts: number): number {
    const f: number[] = Array(k).fill(0);
    const dfs = (i: number): number => {
        if (i >= k) {
            return i <= n ? 1 : 0;
        }
        if (i === k - 1) {
            return Math.min(n - k + 1, maxPts) / maxPts;
        }
        if (f[i] !== 0) {
            return f[i];
        }
        return (f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts);
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can convert the memoized search in Solution 1 into dynamic programming.

Define $f[i]$ to represent the probability that when the current score is $i$, the final score does not exceed $n$ when we stop drawing numbers. The answer is $f[0]$.

When $k \leq i \leq \min(n, k + \textit{maxPts} - 1)$, we have $f[i] = 1$.

When $i = k - 1$, we have $f[i] = \min(n-k+1, \textit{maxPts}) / \textit{maxPts}$.

When $i \lt k - 1$, we have $f[i] = f[i + 1] + (f[i + 1] - f[i + \textit{maxPts} + 1]) / \textit{maxPts}$.

Time complexity $O(k + \textit{maxPts})$, space complexity $O(k + \textit{maxPts})$. Where $k$ is the maximum score.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def new21Game(self, n: int, k: int, maxPts: int) -> float:
        f = [0] * (k + maxPts)
        for i in range(k, min(n + 1, k + maxPts)):
            f[i] = 1
        f[k - 1] = min(n - k + 1, maxPts) / maxPts
        for i in range(k - 2, -1, -1):
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts
        return f[0]
```

#### Java

```java
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double[] f = new double[k + maxPts];
        for (int i = k; i < Math.min(n + 1, k + maxPts); ++i) {
            f[i] = 1;
        }
        f[k - 1] = Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double f[k + maxPts];
        memset(f, 0, sizeof(f));
        for (int i = k; i < min(n + 1, k + maxPts); ++i) {
            f[i] = 1;
        }
        f[k - 1] = min(n - k + 1, maxPts) * 1.0 / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        }
        return f[0];
    }
};
```

#### Go

```go
func new21Game(n int, k int, maxPts int) float64 {
	if k == 0 {
		return 1
	}
	f := make([]float64, k+maxPts)
	for i := k; i < min(n+1, k+maxPts); i++ {
		f[i] = 1
	}
	f[k-1] = float64(min(n-k+1, maxPts)) / float64(maxPts)
	for i := k - 2; i >= 0; i-- {
		f[i] = f[i+1] + (f[i+1]-f[i+maxPts+1])/float64(maxPts)
	}
	return f[0]
}
```

#### TypeScript

```ts
function new21Game(n: number, k: number, maxPts: number): number {
    if (k === 0) {
        return 1;
    }
    const f: number[] = Array(k + maxPts).fill(0);
    for (let i = k; i < Math.min(n + 1, k + maxPts); ++i) {
        f[i] = 1;
    }
    f[k - 1] = Math.min(n - k + 1, maxPts) / maxPts;
    for (let i = k - 2; i >= 0; --i) {
        f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
