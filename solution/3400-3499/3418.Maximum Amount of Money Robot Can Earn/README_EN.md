---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3418.Maximum%20Amount%20of%20Money%20Robot%20Can%20Earn/README_EN.md
rating: 1798
source: Weekly Contest 432 Q2
tags:
    - Array
    - Dynamic Programming
    - Matrix
---

<!-- problem:start -->

# [3418. Maximum Amount of Money Robot Can Earn](https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn)

[中文文档](/solution/3400-3499/3418.Maximum%20Amount%20of%20Money%20Robot%20Can%20Earn/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> grid. A robot starts at the top-left corner of the grid <code>(0, 0)</code> and wants to reach the bottom-right corner <code>(m - 1, n - 1)</code>. The robot can move either right or down at any point in time.</p>

<p>The grid contains a value <code>coins[i][j]</code> in each cell:</p>

<ul>
	<li>If <code>coins[i][j] &gt;= 0</code>, the robot gains that many coins.</li>
	<li>If <code>coins[i][j] &lt; 0</code>, the robot encounters a robber, and the robber steals the <strong>absolute</strong> value of <code>coins[i][j]</code> coins.</li>
</ul>

<p>The robot has a special ability to <strong>neutralize robbers</strong> in at most <strong>2 cells</strong> on its path, preventing them from stealing coins in those cells.</p>

<p><strong>Note:</strong> The robot&#39;s total coins can be negative.</p>

<p>Return the <strong>maximum</strong> profit the robot can gain on the route.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coins = [[0,1,-1],[1,-2,3],[2,-3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>An optimal path for maximum coins is:</p>

<ol>
	<li>Start at <code>(0, 0)</code> with <code>0</code> coins (total coins = <code>0</code>).</li>
	<li>Move to <code>(0, 1)</code>, gaining <code>1</code> coin (total coins = <code>0 + 1 = 1</code>).</li>
	<li>Move to <code>(1, 1)</code>, where there&#39;s a robber stealing <code>2</code> coins. The robot uses one neutralization here, avoiding the robbery (total coins = <code>1</code>).</li>
	<li>Move to <code>(1, 2)</code>, gaining <code>3</code> coins (total coins = <code>1 + 3 = 4</code>).</li>
	<li>Move to <code>(2, 2)</code>, gaining <code>4</code> coins (total coins = <code>4 + 4 = 8</code>).</li>
</ol>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">coins = [[10,10,10],[10,10,10]]</span></p>

<p><strong>Output:</strong> <span class="example-io">40</span></p>

<p><strong>Explanation:</strong></p>

<p>An optimal path for maximum coins is:</p>

<ol>
	<li>Start at <code>(0, 0)</code> with <code>10</code> coins (total coins = <code>10</code>).</li>
	<li>Move to <code>(0, 1)</code>, gaining <code>10</code> coins (total coins = <code>10 + 10 = 20</code>).</li>
	<li>Move to <code>(0, 2)</code>, gaining another <code>10</code> coins (total coins = <code>20 + 10 = 30</code>).</li>
	<li>Move to <code>(1, 2)</code>, gaining the final <code>10</code> coins (total coins = <code>30 + 10 = 40</code>).</li>
</ol>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == coins.length</code></li>
	<li><code>n == coins[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>-1000 &lt;= coins[i][j] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

We design a function $\textit{dfs}(i, j, k)$, which represents the maximum amount of coins the robot can collect starting from $(i, j)$ with $k$ conversion opportunities left. The robot can only move right or down, so the value of $\textit{dfs}(i, j, k)$ depends only on $\textit{dfs}(i + 1, j, k)$ and $\textit{dfs}(i, j + 1, k)$.

-   If $i \geq m$ or $j \geq n$, it means the robot has moved out of the grid, so we return a very small value.
-   If $i = m - 1$ and $j = n - 1$, it means the robot has reached the bottom-right corner of the grid. If $k > 0$, the robot can choose to convert the bandit at the current position, so we return $\max(0, \textit{coins}[i][j])$. If $k = 0$, the robot cannot convert the bandit at the current position, so we return $\textit{coins}[i][j]$.
-   If $\textit{coins}[i][j] < 0$, it means there is a bandit at the current position. If $k > 0$, the robot can choose to convert the bandit at the current position, so we return $\textit{coins}[i][j] + \max(\textit{dfs}(i + 1, j, k), \textit{dfs}(i, j + 1, k))$. If $k = 0$, the robot cannot convert the bandit at the current position, so we return $\textit{coins}[i][j] + \max(\textit{dfs}(i + 1, j, k), \textit{dfs}(i, j + 1, k))$.

Based on the above analysis, we can write the code for memoized search.

The time complexity is $O(m \times n \times k)$, and the space complexity is $O(m \times n \times k)$. Here, $m$ and $n$ are the number of rows and columns of the 2D array $\textit{coins}$, and $k$ is the number of conversion opportunities, which is $3$ in this problem.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumAmount(self, coins: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= m or j >= n:
                return -inf
            if i == m - 1 and j == n - 1:
                return max(coins[i][j], 0) if k else coins[i][j]
            ans = coins[i][j] + max(dfs(i + 1, j, k), dfs(i, j + 1, k))
            if coins[i][j] < 0 and k:
                ans = max(ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1))
            return ans

        m, n = len(coins), len(coins[0])
        return dfs(0, 0, 2)
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private int[][] coins;
    private int m;
    private int n;

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        this.coins = coins;
        f = new Integer[m][n][3];
        return dfs(0, 0, 2);
    }

    private int dfs(int i, int j, int k) {
        if (i >= m || j >= n) {
            return Integer.MIN_VALUE / 2;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (i == m - 1 && j == n - 1) {
            return k > 0 ? Math.max(0, coins[i][j]) : coins[i][j];
        }
        int ans = coins[i][j] + Math.max(dfs(i + 1, j, k), dfs(i, j + 1, k));
        if (coins[i][j] < 0 && k > 0) {
            ans = Math.max(ans, Math.max(dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)));
        }
        return f[i][j][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size(), n = coins[0].size();
        vector<vector<vector<int>>> f(m, vector<vector<int>>(n, vector<int>(3, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i >= m || j >= n) {
                return INT_MIN / 2;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            if (i == m - 1 && j == n - 1) {
                return k > 0 ? max(0, coins[i][j]) : coins[i][j];
            }
            int ans = coins[i][j] + max(dfs(i + 1, j, k), dfs(i, j + 1, k));
            if (coins[i][j] < 0 && k > 0) {
                ans = max({ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)});
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, 0, 2);
    }
};
```

#### Go

```go
func maximumAmount(coins [][]int) int {
	m, n := len(coins), len(coins[0])
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, 3)
			for k := range f[i][j] {
				f[i][j][k] = math.MinInt32
			}
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i >= m || j >= n {
			return math.MinInt32 / 2
		}
		if f[i][j][k] != math.MinInt32 {
			return f[i][j][k]
		}
		if i == m-1 && j == n-1 {
			if k > 0 {
				return max(0, coins[i][j])
			}
			return coins[i][j]
		}
		ans := coins[i][j] + max(dfs(i+1, j, k), dfs(i, j+1, k))
		if coins[i][j] < 0 && k > 0 {
			ans = max(ans, max(dfs(i+1, j, k-1), dfs(i, j+1, k-1)))
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, 0, 2)
}
```

#### TypeScript

```ts
function maximumAmount(coins: number[][]): number {
    const [m, n] = [coins.length, coins[0].length];
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(3).fill(-Infinity)),
    );
    const dfs = (i: number, j: number, k: number): number => {
        if (i >= m || j >= n) {
            return -Infinity;
        }
        if (f[i][j][k] !== -Infinity) {
            return f[i][j][k];
        }
        if (i === m - 1 && j === n - 1) {
            return k > 0 ? Math.max(0, coins[i][j]) : coins[i][j];
        }
        let ans = coins[i][j] + Math.max(dfs(i + 1, j, k), dfs(i, j + 1, k));
        if (coins[i][j] < 0 && k > 0) {
            ans = Math.max(ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1));
        }
        return (f[i][j][k] = ans);
    };
    return dfs(0, 0, 2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
