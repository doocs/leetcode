---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2400.Number%20of%20Ways%20to%20Reach%20a%20Position%20After%20Exactly%20k%20Steps/README_EN.md
rating: 1751
source: Weekly Contest 309 Q2
tags:
    - Math
    - Dynamic Programming
    - Combinatorics
---

<!-- problem:start -->

# [2400. Number of Ways to Reach a Position After Exactly k Steps](https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps)

[中文文档](/solution/2400-2499/2400.Number%20of%20Ways%20to%20Reach%20a%20Position%20After%20Exactly%20k%20Steps/README.md)

## Description

<!-- description:start -->

<p>You are given two <strong>positive</strong> integers <code>startPos</code> and <code>endPos</code>. Initially, you are standing at position <code>startPos</code> on an <strong>infinite</strong> number line. With one step, you can move either one position to the left, or one position to the right.</p>

<p>Given a positive integer <code>k</code>, return <em>the number of <strong>different</strong> ways to reach the position </em><code>endPos</code><em> starting from </em><code>startPos</code><em>, such that you perform <strong>exactly</strong> </em><code>k</code><em> steps</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two ways are considered different if the order of the steps made is not exactly the same.</p>

<p><strong>Note</strong> that the number line includes negative integers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> startPos = 1, endPos = 2, k = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can reach position 2 from 1 in exactly 3 steps in three ways:
- 1 -&gt; 2 -&gt; 3 -&gt; 2.
- 1 -&gt; 2 -&gt; 1 -&gt; 2.
- 1 -&gt; 0 -&gt; 1 -&gt; 2.
It can be proven that no other way is possible, so we return 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> startPos = 2, endPos = 5, k = 10
<strong>Output:</strong> 0
<strong>Explanation:</strong> It is impossible to reach position 5 from position 2 in exactly 10 steps.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= startPos, endPos, k &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memorization Search

We design a function $dfs(i, j)$, which represents the number of ways to reach the target position when the current position is $i$ distance from the target position and there are $j$ steps left. The answer is $dfs(abs(startPos - endPos), k)$.

The calculation method of the function $dfs(i, j)$ is as follows:

- If $i \gt j$ or $j \lt 0$, it means that the current distance from the target position is greater than the remaining steps, or the remaining steps are negative. In this case, it is impossible to reach the target position, so return $0$;
- If $j = 0$, it means that there are no steps left. At this time, only when the current distance from the target position is $0$ can the target position be reached, otherwise it is impossible to reach the target position. Return $1$ or $0$;
- Otherwise, the current distance from the target position is $i$, and there are $j$ steps left. There are two ways to reach the target position:
    - Move one step to the left, the current distance from the target position is $i + 1$, and there are $j - 1$ steps left. The number of methods is $dfs(i + 1, j - 1)$;
    - Move one step to the right, the current distance from the target position is $abs(i - 1)$, and there are $j - 1$ steps left. The number of methods is $dfs(abs(i - 1), j - 1)$;
- Finally, return the result of the sum of the two methods modulo $10^9 + 7$.

To avoid repeated calculations, we use memorization search, that is, we use a two-dimensional array $f$ to record the result of the function $dfs(i, j)$. When the function $dfs(i, j)$ is called, if $f[i][j]$ is not $-1$, return $f[i][j]$ directly, otherwise calculate the value of $f[i][j]$, and return $f[i][j]$.

The time complexity is $O(k^2)$, and the space complexity is $O(k^2)$. Here, $k$ is the number of steps given in the problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j or j < 0:
                return 0
            if j == 0:
                return 1 if i == 0 else 0
            return (dfs(i + 1, j - 1) + dfs(abs(i - 1), j - 1)) % mod

        mod = 10**9 + 7
        return dfs(abs(startPos - endPos), k)
```

#### Java

```java
class Solution {
    private Integer[][] f;
    private final int mod = (int) 1e9 + 7;

    public int numberOfWays(int startPos, int endPos, int k) {
        f = new Integer[k + 1][k + 1];
        return dfs(Math.abs(startPos - endPos), k);
    }

    private int dfs(int i, int j) {
        if (i > j || j < 0) {
            return 0;
        }
        if (j == 0) {
            return i == 0 ? 1 : 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = dfs(i + 1, j - 1) + dfs(Math.abs(i - 1), j - 1);
        ans %= mod;
        return f[i][j] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfWays(int startPos, int endPos, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1][k + 1];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int j) -> int {
            if (i > j || j < 0) {
                return 0;
            }
            if (j == 0) {
                return i == 0 ? 1 : 0;
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            f[i][j] = (dfs(i + 1, j - 1) + dfs(abs(i - 1), j - 1)) % mod;
            return f[i][j];
        };
        return dfs(abs(startPos - endPos), k);
    }
};
```

#### Go

```go
func numberOfWays(startPos int, endPos int, k int) int {
	const mod = 1e9 + 7
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j || j < 0 {
			return 0
		}
		if j == 0 {
			if i == 0 {
				return 1
			}
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		f[i][j] = (dfs(i+1, j-1) + dfs(abs(i-1), j-1)) % mod
		return f[i][j]
	}
	return dfs(abs(startPos-endPos), k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function numberOfWays(startPos: number, endPos: number, k: number): number {
    const mod = 10 ** 9 + 7;
    const f = new Array(k + 1).fill(0).map(() => new Array(k + 1).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i > j || j < 0) {
            return 0;
        }
        if (j === 0) {
            return i === 0 ? 1 : 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + dfs(Math.abs(i - 1), j - 1);
        f[i][j] %= mod;
        return f[i][j];
    };
    return dfs(Math.abs(startPos - endPos), k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
